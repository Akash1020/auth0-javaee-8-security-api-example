package com.auth0.examples.java.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import com.auth0.examples.java.configuration.Auth0Configuration;
import com.auth0.examples.java.util.Constants;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Stateless
@Path("/authenticate")
public class AuthenticationResource {
	
	@Inject
	private Auth0Configuration auth0Configuration;

	@POST
	public Response authenticate(JsonObject loginDetails) {
		String username = loginDetails.getString("username");
		String password = loginDetails.getString("password");
		
		// TODO: Check username / password credentials via mechanism desired
		
		try {
			JSONObject requestBody = new JSONObject();
			requestBody.put("client_id", auth0Configuration.getClientId());
			requestBody.put("client_secret", auth0Configuration.getClientSecret());
			requestBody.put("audience", auth0Configuration.getAudience());
			requestBody.put("grant_type", "client_credentials");
			
			HttpResponse<String> response = Unirest.post(auth0Configuration.getEndpoint() + auth0Configuration.getTokenURLPath())
					  .header("content-type", "application/json")
					  .body(requestBody)
					  .asString();
			JSONObject responseBody = new JSONObject(response.getBody());

			return Response.ok().header(Constants.AUTHORIZATION_HEADER, Constants.TOKEN_PEFIX + responseBody.get("access_token")).build();
		} catch (UnirestException e) {
			
			return Response.status(HttpStatus.SC_BAD_REQUEST).build();
		}
	}
}
