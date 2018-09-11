package com.auth0.examples.java.resources;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Stateless
@Path("/authenticate")
public class AuthenticationResource {

	@POST
	public Response authenticate(JsonObject loginDetails) {
		String username = loginDetails.getString("username");
		String password = loginDetails.getString("password");
		
		// TODO: Authenticate with Auth0 API
		
		return Response.ok().header("Authorization", "Bearer " + username + ":" + "MEMBER").build();
	}
}
