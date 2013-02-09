package flightbookingclient;

import java.util.ArrayList;

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
        
        BookedFlight postedBooking = service.postBooking(booking);
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
