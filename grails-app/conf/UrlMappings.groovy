class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/users"(resources: 'user')


        "/"(view:"/index")
        "500"(view:'/error')

        //"/abcd" (controller: "user", action: "index")
	}
}
