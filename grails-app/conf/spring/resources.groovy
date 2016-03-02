import grails.rest.render.json.JsonRenderer
import org.codehaus.groovy.grails.web.mime.MimeType
import org.springframework.validation.Errors
import user.v1.User

beans = {

    final MimeType[] DEFAULT_MIME_TYPES = [MimeType.HAL_JSON, MimeType.JSON, MimeType.TEXT_JSON] as MimeType[]
    final MimeType[] VERSION_1_MIME_TYPES = DEFAULT_MIME_TYPES + [new MimeType("application/vnd.configurations.org.configuration+json", "json",[v:"1.0"])] as MimeType[]

    userRenderer(JsonRenderer, User, DEFAULT_MIME_TYPES)

    halErrors(JsonRenderer, Errors, VERSION_1_MIME_TYPES)

}