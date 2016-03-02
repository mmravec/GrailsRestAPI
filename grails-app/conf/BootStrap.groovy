import user.v1.Renderable
import grails.converters.JSON

class BootStrap {

    def grailsApplication
    def renderService

    def init = { servletContext ->
        JSON.registerObjectMarshaller(Renderable) { Renderable obj ->
            renderService.render(obj)
        }

        println "${grailsApplication.metadata['app.name']}: ${grailsApplication.metadata['app.version']}"
    }

    def destroy = {
    }
}
