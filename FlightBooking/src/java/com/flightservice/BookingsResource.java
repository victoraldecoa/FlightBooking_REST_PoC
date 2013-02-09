package com.flightservice;

import com.flightbean.BookedFlight;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

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
    public List<BookedFlight> getXml(@QueryParam("token") String token) {
        if (!AuthResource.isTokenValid(token)) {
            return null;
        }
        
        List<BookedFlight> bookings = new ArrayList<BookedFlight>();
        bookings.addAll(BookingDBMock.getInstance().getModel().values());

        return bookings;
    }

    // Defines that the next path parameter after bookings is
    // treated as a parameter and passed to the BookingsResource
    // Allows to type http://localhost:8080/FlightBooking/webresources/bookings/564231
    // 564231 will be treaded as parameter booking and passed to BookingResource
    @Path("{booking}")
    public BookingResource getBooking(@PathParam("booking") String id,
                                      @QueryParam("token") String token) {
        if (!AuthResource.isTokenValid(token)) {
            return null;
        }
        return new BookingResource(uriInfo, request, id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response postBooking(@QueryParam("token") String token,
                                JAXBElement<BookedFlight> booking) {
        if (!AuthResource.isTokenValid(token)) {
            return null;
        }
        BookedFlight c = booking.getValue();
        return postAndGetResponse(c);
    }

    private Response postAndGetResponse(BookedFlight booking) {        
        booking = BookingDBMock.getInstance().createBooking(booking);
        
        Response res = Response.ok(booking).build();

        return res;
    }
}
