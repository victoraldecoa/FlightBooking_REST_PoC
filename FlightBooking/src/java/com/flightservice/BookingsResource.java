package com.flightservice;

import com.flightbean.BookedFlight;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

/**
 * REST Web Service
 *
 * @author aldecoa & alegeo
 */
@Path("/bookings")
public class BookingsResource {

    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    /**
     * Retrieves representation of an instance of
     * com.flightservice.ServiceResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<BookedFlight> getXml() {
        List<BookedFlight> bookings = new ArrayList<BookedFlight>();
        bookings.addAll(BookingDBMock.getInstance().getModel().values());

        return bookings;
    }

    // Defines that the next path parameter after bookings is
    // treated as a parameter and passed to the BookingsResource
    // Allows to type http://localhost:8080/FlightBooking/webresources/bookings/564231
    // 564231 will be treaded as parameter booking and passed to BookingResource
    @Path("{booking}")
    public BookingResource getBooking(@PathParam("booking") String id) {
        return new BookingResource(uriInfo, request, id);
    }
}
