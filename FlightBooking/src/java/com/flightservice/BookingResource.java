/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightservice;

import com.flightbean.BookedFlight;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author aldecoa & alegeo
 */
public class BookingResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;

    public BookingResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application integration     
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public BookedFlight getBooking(@QueryParam("token") String token) {
        if (!AuthResource.isTokenValid(token)) {
            return null;
        }
        BookedFlight booking = BookingDBMock.getInstance().getModel().get(id);
        if (booking == null) {
            throw new RuntimeException("Get: Booking with " + id + " not found");
        }
        return booking;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putBooking(@QueryParam("token") String token,
                               JAXBElement<BookedFlight> booking) {
        if (!AuthResource.isTokenValid(token)) {
            return null;
        }
        BookedFlight c = booking.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deleteBooking(@QueryParam("token") String token) {
        if (!AuthResource.isTokenValid(token)) {
            return;
        }
        
        BookedFlight c = BookingDBMock.getInstance().getModel().remove(id);
        if (c == null) {
            throw new RuntimeException("Delete: Booking with " + id + " not found");
        }
    }

    private Response putAndGetResponse(BookedFlight booking) {
        Response res;
        if (BookingDBMock.getInstance().getModel().containsKey(booking.getBookingId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        BookingDBMock.getInstance().getModel().put(booking.getBookingId(), booking);
        return res;
    }
}
