package com.example.library_mgmt.api;

import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION = "key";
    private static final String AUTH_TOKEN = "abcd123";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> auth = requestContext.getHeaders().get(AUTHORIZATION);

        if (auth != null && auth.size() > 0) {
            String authToken = auth.get(0);
            System.out.println(authToken);

            if (authToken.equals(AUTH_TOKEN)) {
                return;
            }
        }
        Response unauthorized = Response.status(Response.Status.UNAUTHORIZED).entity("Error, Invalid authentication").build();
        requestContext.abortWith(unauthorized);

    }

}
