/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightservice;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author aldecoa
 */
@Path("auth")
public class AuthResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AuthResource
     */
    public AuthResource() {
    }

    private String generateKey(String userName, String password) {
        // TODO generate a real unique key and add it on user's list
        return "jfdoKJgUOUG8SD89568gGDhjkgid";
    }
    // TODO remove mockup database
    private static String[] userNames = {"victor", "alex", "admin"};
    private static String[] passwords = {"123456", "654321", "StrongPassword"};
    
    public static boolean isTokenValid(String token) {
        // TODO check token in the users list
        return token != null && token.equals("jfdoKJgUOUG8SD89568gGDhjkgid");
    }

    /**
     * Retrieves representation of an instance of com.flightbean.AuthResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getToken(@QueryParam("user") String user,
                         @QueryParam("password") String password) {
        for (int i = 0; i < userNames.length; i++) {
            if (userNames[i].equals(user) && passwords[i].equals(password)) {
                return generateKey(user, password);
            }
        }

        // no user found, or password doesn't match
        return "";
    }
}
