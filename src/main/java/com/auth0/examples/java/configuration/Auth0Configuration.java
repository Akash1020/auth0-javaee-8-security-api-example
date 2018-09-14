package com.auth0.examples.java.configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Auth0Configuration {
	
	@Inject
	@Property("auth0.endpoint")
	private String endpoint;
	
	@Inject
	@Property("auth0.endpoint.token")
	private String tokenURLPath;
	
	@Inject
	@Property("auth0.endpoint.jwks")
	private String jwksURLPath;

	@Inject
    @Property("auth0.client.id")
	private String clientId;
	
	@Inject
	@Property("auth0.client.secret")
	private String clientSecret;
	
	@Inject
	@Property("auth0.client.audience")
	private String audience;
	
	public String getEndpoint() {
		return endpoint;
	}
	
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getTokenURLPath() {
		return tokenURLPath;
	}

	public void setTokenURLPath(String tokenURLPath) {
		this.tokenURLPath = tokenURLPath;
	}

	public String getJwksURLPath() {
		return jwksURLPath;
	}

	public void setJwksURLPath(String jwksURLPath) {
		this.jwksURLPath = jwksURLPath;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}
}
