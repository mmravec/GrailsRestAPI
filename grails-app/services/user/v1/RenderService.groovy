package user.v1

import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class RenderService {

    static transactional = false

    def grailsApplication

    def getG() {
        grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib') as ApplicationTagLib
    }

    def render(obj) {
        def response = obj.render { linkData ->

            if (linkData.mapping) {
                obj.link rel: linkData.type, href: g.createLink(absolute: true, mapping: linkData.mapping, params: linkData.params), contentType: 'application/json'
            } else {
                obj.link rel: linkData.type, href: g.createLink(absolute: true, controller: linkData.target, action: linkData.action ?: 'show', params: linkData.params), contentType: 'application/json'
            }
        }

        if (response instanceof Map) {
            response.links = obj.links().groupBy { it.rel }.collectEntries { k, v ->
                [k, v.size() == 1 ? [href: v.first().href, type: v.first().contentType] :
                        v.collect { [href: it.href, type: it.contentType] }]
            }
        }

        response
    }
}

