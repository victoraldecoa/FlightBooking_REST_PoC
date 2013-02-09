/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightservice;

import com.flightbean.Itinerary;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author aldecoa
 */
@Path("checkavailable")
public class CheckAvailableResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CheckAvailable
     */
    public CheckAvailableResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.flightservice.CheckAvailable
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getXml(@QueryParam("itineraryId") String itineraryId,
            @QueryParam("date") String date,
            @QueryParam("token") String token) {
        if (!AuthResource.isTokenValid(token)) {
            return "";
        }
        
        Itinerary i = ItinerariesTableMock.getInstance().getById(itineraryId);
        if (i == null || !date.equals("2013-02-06")) {
            return "";
        } else {
            return "1500 kr";
        }
    }

    /**
     * PUT method for updating or creating an instance of CheckAvailable
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
