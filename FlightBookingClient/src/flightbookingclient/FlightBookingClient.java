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
import java.util.ArrayList;
import javax.ws.rs.core.Response;

/**
 *
 * @author aldecoa & alegeo
 */
public class FlightBookingClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // get web service instance
        FlightBookingAPI service = FlightBookingAPI.getInstance();
        
        // Create one booking
        BookedFlight booking = new BookedFlight();
        booking.setDate("2013-02-08");
        booking.setItineraryId("654");
        
        System.out.println("POST a new booking:");
        ClientResponse res = service.postBooking(booking);
        
        BookedFlight postedBooking = res.getEntity(BookedFlight.class);
        System.out.println(postedBooking);
        
        // Get the BookedFlight with id 564231
        System.out.println("GET booking by id:");
        BookedFlight bookingGet = service.getBookingById("564231");
        
        System.out.println(bookingGet);
        
        bookingGet.setDate("2013-05-05");
        
        System.out.println("PUT to update a booking (change of date)");
        service.putBooking(bookingGet);
        
        ArrayList<BookedFlight> allBookings = new ArrayList<>(
                                                   service.getBookings());
        
        System.out.println("GET all bookings:");
        for (BookedFlight b : allBookings) {
            System.out.print("    ");
            System.out.println(b);
        }
        
        // Delete recently created booking
        System.out.println("DELETE recently created booking");
        service.deleteBooking(postedBooking);
    }
}
