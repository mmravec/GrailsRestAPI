package user.v1
import grails.rest.Resource

@Resource(formats=['json'])
class User implements Renderable{
    String firstName
    String lastName
    String age
    String country

    Date dateCreated
    Date lastUpdated


    static mapping = {
        table "Pouzivatel"
        firstName column: "meno"
        lastName column: "prizvisko"
        age column: "vek"
        country column: "krajina"
    }

    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        age nullable: false, blank: false
        country nullable: false, blank: false
    }

    @Override
    def render(Closure linker) {
        if (id) {
            linker.call type: 'self', target: 'user', params: [id: id]
        }

        [
                id: id,
                firstName: firstName,
                lastName: lastName,
                age: age,
                country: country
        ]
    }
}
