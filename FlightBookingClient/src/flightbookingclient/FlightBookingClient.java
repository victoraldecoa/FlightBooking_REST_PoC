package flightbookingclient;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

/**
 *
 * @author aldecoa & alegeo
 */
public class FlightBookingClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(getBaseURI());
        // Create one booking
        BookedFlight booking = new BookedFlight();
        booking.setBookingId("1");
        booking.setDate("2013-02-08");
        booking.setItineraryId("654");
        ClientResponse response = service.path("rest").path("bookings")
                .path(booking.getBookingId()).accept(MediaType.APPLICATION_XML)
                .put(ClientResponse.class, booking);
        // Return code should be 201 == created resource
        System.out.println(response.getStatus());
        
        // Get the all bookings
        System.out.println(service.path("webresources").path("bookings")
                .accept(MediaType.APPLICATION_XML).get(String.class));

        // Get the BookedFlight with id 564231
        System.out.println(service.path("webresources").path("bookings/564231")
                .accept(MediaType.APPLICATION_XML).get(String.class));
                
        // Delete BookedFlight with id 564231
        service.path("webresources").path("bookings/564231").delete();
        // Get the all todos, id 564231 should be deleted
        System.out.println(service.path("webresources").path("bookings")
                .accept(MediaType.APPLICATION_XML).get(String.class));


    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/FlightBooking").build();
    }
}
