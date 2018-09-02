package auth0.security.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class UserResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getHtml() {
    	
        return new User("username");
    }
}
