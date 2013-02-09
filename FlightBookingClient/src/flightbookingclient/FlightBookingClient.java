package flightbookingclient;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aldecoa & alegeo
 */
public class FlightBookingClient {
    
    private static FlightBookingAPI service;

        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // get web service instance
        service = FlightBookingAPI.getInstance();
        
        Scanner input = new Scanner(System.in);
        
        boolean userAuthorized = false;

        // Authenticating user
        while (!userAuthorized) {
            System.out.print("Username: ");
            String userName = input.next();

            System.out.print("Password: ");
            String password = input.next();

            userAuthorized = service.authUser(userName, password);
            
            if (userAuthorized) {
                System.out.println("User authorized");
            } else {
                System.out.println("Username or password are wrong. Try again.");
            }
            
            System.out.println();
        }
        
        Itinerary i = null;
        
        while (i == null) {
            System.out.print("Departure city: ");
            String depCity = input.next();

            System.out.print("Destination city: ");
            String destCity = input.next();
            
            i = service.getItinerary(depCity, destCity);
            
            if (i != null) {
                System.out.println("A possible itinerary is:");
                for (Flight flight : i.flights) {
                    System.out.println("    Dep: " + flight.getDepartureCity() + "    Dest: " + flight.getDestinationCity());
                }
            } else {
                System.out.println("No routes for these cities. Please try another.");
            }
        }
        
        String price = "";
        String date = null;
        
        while(price.equals("")) {
            System.out.print("Select a date (aaaa-mm-dd): ");
            date = input.next();
            
            price = service.checkAvailable(i.getId(), date);
            
            if (price.equals("")) {
                System.out.println("No flights available in that date. Please try another.");
            } else {
                System.out.println("The price for this flight is " + price);
            }
        }
        
        System.out.print("If you want to proceed with the booking, please insert the credit card. "
                         + "Otherwise just press enter: ");
        String creditCard = input.next();
        
        if (!creditCard.equals("")) {            
            BookedFlight booking = new BookedFlight();
            booking.setDate(date);
            booking.setItineraryId(i.getId());
            
            booking = service.postBooking(booking, creditCard);
            
            System.out.println("Your booking id is " + booking.getBookingId() + ".");
        }
        System.out.println("Thank you for using our services!");
        
        // testAllMethods(args);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void testAllMethods(String[] args) {        
        // Create one booking
        BookedFlight booking = new BookedFlight();
        booking.setDate("2013-02-08");
        booking.setItineraryId("654");
        
        System.out.println("POST a new booking:");
        
        BookedFlight postedBooking = service.postBooking(booking, "123456789456");
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
