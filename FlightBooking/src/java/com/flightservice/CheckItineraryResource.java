/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightservice;

import com.flightbean.Flight;
import com.flightbean.Itinerary;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
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
@Path("checkitinerary")
public class CheckItineraryResource {

    public static int itineraryCount = 562;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CheckItineraryResource
     */
    public CheckItineraryResource() {
    }
    private static final String[] possibleCities = {"Madrid", "Venice", "Lisbon", "Stockholm"};

    /**
     * Retrieves representation of an instance of
     * com.flightservice.CheckItineraryResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Itinerary getXml(@QueryParam("depCity") String depCity,
            @QueryParam("destCity") String destCity,
            @QueryParam("token") String token) {
        if (!AuthResource.isTokenValid(token)) {
            return null;
        }

        // checking if these cities exist
        boolean depPossible = false;
        boolean destPossible = false;
        for (String city : possibleCities) {
            if (depCity.equals(city)) {
                depPossible = true;
            }
            if (destCity.equals(city)) {
                destPossible = true;
            }
        }

        if (!depPossible || !destPossible) {
            return null;
        }

        Itinerary itinerary = new Itinerary();
        itinerary.flights = new ArrayList<Flight>();

        itinerary.flights.add(new Flight(depCity, "Berlin"));
        itinerary.flights.add(new Flight("Berlin", destCity));

        itinerary.setId(String.valueOf(itineraryCount));

        return itinerary;
    }

    /**
     * PUT method for updating or creating an instance of CheckItineraryResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
