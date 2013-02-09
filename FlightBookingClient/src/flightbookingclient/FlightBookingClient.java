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
import java.util.Scanner;
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

        System.out.println("Press 1 if you wish to create a new booking.");
        System.out.println("Press 2 if you wish to view your bookings.");
        System.out.println("Press 3 if you wish to update one of your bookings.");
        System.out.println("Press 4 if you wish to delete one of your bookings.");

        System.out.println("Please enter your choice: ");
        Scanner input = new Scanner(System.in);
        int option = Integer.parseInt(input.next());
        int bid;

        switch (option) {
            case 1:
                // Create a booking
                BookedFlight booking = new BookedFlight();
                booking.setDate("2013-02-08");
                booking.setItineraryId("654");

                System.out.println("POST a new booking:");
                ClientResponse res = service.postBooking(booking);

                BookedFlight postedBooking = res.getEntity(BookedFlight.class);
                System.out.println(postedBooking);
                break;
            case 2:
                // Get all bookings
                System.out.println("GET all bookings:");
                ArrayList<BookedFlight> allBookings = new ArrayList<>(
                        service.getBookings());
                for (BookedFlight b : allBookings) {
                    System.out.print("    ");
                    System.out.println(b);
                }
                break;
            case 3:
                // Update a booking
                System.out.println("PUT to update a booking (change of date)");
                System.out.println("Please enter the id of the booking you wish to update: ");
                bid = Integer.parseInt(input.next());
                BookedFlight bookingGet = service.getBookingById(String.valueOf(bid));
                service.putBooking(bookingGet);
                break;
            case 4:
                // Delete a booking depeding on the booking id (bid)
                System.out.println("DELETE one of your bookings booking");
                bid = Integer.parseInt(input.next());
                service.getBookingById(String.valueOf(bid));
                BookedFlight bookedFlight_temp = new BookedFlight();
                service.deleteBooking(bookedFlight_temp);
                break;
            default:
                    System.out.println("Invalid Entry!");
        }
        



    }
    // Get the BookedFlight with id 564231
//        System.out.println("GET booking by id:");
//       BookedFlight bookingGet = service.getBookingById("564231");
//
//        System.out.println(bookingGet);
//
//        bookingGet.setDate("2013-05-05");
}
