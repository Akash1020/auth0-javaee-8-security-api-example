package com.auth0.examples.java.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApplicationScoped
public class Auth0AuthenticationMechanism implements HttpAuthenticationMechanism {
	
	private static final List<String> WHITELISTED_URLS = Arrays.asList(new String[] {"/authenticate"});
	
	@Inject
	private IdentityStore identityStore;

	@Override
	public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response,
			HttpMessageContext httpMessageContext) throws AuthenticationException {
		String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String token = authorizationHeader.substring("Bearer".length());
            Credential credential = new Auth0Credential("username", new HashSet<String>(Arrays.asList("MEMBER")));

    		// TODO: Integrate Auth0 for security access

    		return httpMessageContext.notifyContainerAboutLogin(identityStore.validate(credential));
        } else if (WHITELISTED_URLS.contains(request.getPathInfo())) {
        	return httpMessageContext.doNothing();
        } else {
        	return httpMessageContext.responseUnauthorized();
        }
	}
}
