/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flightbookingclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.net.URI;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author aldecoa
 */
public class FlightBookingAPI {

    private static FlightBookingAPI instance;
    private WebResource service;

    private FlightBookingAPI() {
        Client client = Client.create();
        service = client.resource(getBaseURI());
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/FlightBooking").build();
    }

    public static FlightBookingAPI getInstance() {
        if (instance == null) {
            instance = new FlightBookingAPI();
        }
        return instance;
    }

    public BookedFlight getBookingById(String id) {
        return service.path("webresources").path("bookings/" + id).accept(MediaType.APPLICATION_XML).get(BookedFlight.class);
    }

    public List<BookedFlight> getBookings() {
        return service.path("webresources").path("bookings")
                .accept(MediaType.APPLICATION_XML).get(new GenericType<List<BookedFlight>>() {
        });
    }

    public void putBooking(BookedFlight b) {
        service.path("webresources").path("bookings")
                .path(b.getBookingId()).accept(MediaType.APPLICATION_XML)
                .put(ClientResponse.class, b);
    }

    /**
     *
     * @param b the flight to be booked (bookingId will be ignored)
     * @return the booked flight, with a new id
     */
    public BookedFlight postBooking(BookedFlight b) {
        return service.path("webresources").path("bookings")
                .accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, b).getEntity(BookedFlight.class);
    }

    public void deleteBooking(BookedFlight b) {
        service.path("webresources").path("bookings/" + b.getBookingId()).delete();
    }
}
