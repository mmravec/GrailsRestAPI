package grailsrestapi


import grails.rest.RestfulController
import user.v1.User
import org.codehaus.groovy.grails.web.servlet.HttpHeaders

class UserController extends RestfulController<User>{


    static responseFormats = ['json']

    UserController() {
        super(User)
    }


    @Override
    def delete() {
        def instance = queryForResource(params.id)
        if (instance == null) {
            notFound()
            return
        }
        instance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: "${resourceClassName}.label".toString(), default: resourceClassName), instance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT } // NO CONTENT STATUS CODE
        }
    }

    @Override
    def update() {
        if(handleReadOnly()) {
            return
        }

        User instance = queryForResource(params.id)
        if (instance == null) {
            notFound()
            return
        }

        instance.properties = getParametersToBind()

        if (!instance.validate()) {
            respond instance.details.errors.hasErrors() ? instance.details.errors : instance.errors, view:'edit' // STATUS CODE 422
            return
        }

        instance.save()

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: "${resourceClassName}.label".toString(), default: resourceClassName), instance.id])
                redirect instance
            }
            '*'{
                response.addHeader(HttpHeaders.LOCATION,
                        g.createLink(
                                resource: this.controllerName, action: 'show',id: instance.id, absolute: true,
                                namespace: hasProperty('namespace') ? this.namespace : null ))
                respond instance, [status: OK]
            }
        }
    }



}
