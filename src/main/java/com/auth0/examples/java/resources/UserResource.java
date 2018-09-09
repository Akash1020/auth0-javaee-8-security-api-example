package com.auth0.examples.java.resources;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.auth0.examples.java.entities.User;

@Stateless
@Path("/")
public class UserResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
    	
        return new User("username");
    }
}
