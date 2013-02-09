/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flightbookingclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
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
    
    private String userToken;

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
        if (userToken.equals("")) {
            return null;
        }
        return service.path("webresources").path("bookings/" + id).queryParam("token", userToken)
                      .accept(MediaType.APPLICATION_XML).get(BookedFlight.class);
    }

    public List<BookedFlight> getBookings() {
        if (userToken.equals("")) {
            return null;
        }
        return service.path("webresources").path("bookings").queryParam("token", userToken)
                .accept(MediaType.APPLICATION_XML).get(new GenericType<List<BookedFlight>>() {
        });
    }
    
    public Itinerary getItinerary(String depCity, String destCity) {
        if (userToken.equals("")) {
            return null;
        }
        try {
        return service.path("webresources").path("checkitinerary").queryParam("depCity", depCity).queryParam("destCity", destCity).queryParam("token", userToken)
                .accept(MediaType.APPLICATION_XML).get(Itinerary.class);
        } catch(UniformInterfaceException ex) {
            return null;
        }
    }
    
    public String checkAvailable(String itineraryId, String date) {
        if (userToken.equals("")) {
            return "";
        }
        try {
        return service.path("webresources").path("checkavailable").queryParam("itineraryId", itineraryId).queryParam("date", date).queryParam("token", userToken)
                              .accept(MediaType.TEXT_PLAIN).get(String.class);
        } catch(UniformInterfaceException ex) {
            return "";
        }
    }

    public void putBooking(BookedFlight b) {
        if (userToken.equals("")) {
            return;
        }
        service.path("webresources").path("bookings")
                .path(b.getBookingId()).queryParam("token", userToken).accept(MediaType.APPLICATION_XML)
                .put(ClientResponse.class, b);
    }

    /**
     *
     * @param b the flight to be booked (bookingId will be ignored)
     * @return the booked flight, with a new id
     */
    public BookedFlight postBooking(BookedFlight b, String creditCard) {
        if (userToken.equals("")) {
            return null;
        }
        
        return service.path("webresources").path("bookings")
                .queryParam("creditCard", creditCard)
                .queryParam("token", userToken)
                .accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, b).getEntity(BookedFlight.class);
    }

    public void deleteBooking(BookedFlight b) {
        if (userToken.equals("")) {
            return;
        }
        
        service.path("webresources").path("bookings/" + b.getBookingId()).queryParam("token", userToken).delete();
    }
        
    /**
     * Authenticate an user
     * @param user
     * @param password
     * @return true if user was authorized
     */
    public boolean authUser(String user, String password) {
        userToken = service.path("webresources").path("auth").queryParam("user", user).queryParam("password", password)
                .accept(MediaType.TEXT_PLAIN).get(String.class);
        return !userToken.equals("");
    }
}
