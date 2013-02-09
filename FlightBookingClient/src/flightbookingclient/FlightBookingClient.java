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
        
        testAllMethods(args);
        
        /*
        Flight[] flights = new Flight[0];
        while (flights.length == 0) {
            System.out.print("Departure city: ");
            String depCity = input.next();

            System.out.print("Destination city: ");
            String destCity = input.next();
            
            flights = port.checkItinerary(depCity, destCity, token);
            
            if (flights.length > 0) {
                System.out.println("A possible itinerary is:");
                for (Flight flight : flights) {
                    System.out.println("    Dep: " + flight.departureCity + "    Dest: " + flight.destinationCity);
                }
            } else {
                System.out.println("No routes for these cities. Please try another.");
            }
        }

        float price = 0.0f;
        String date = null;
        
        while(price == 0.0f) {
            System.out.print("Select a date (aaaa-mm-dd): ");
            date = input.next();
            
            price = port.checkAvailable(flights, date, token);
            
            if (price == 0.0f) {
                System.out.println("No flights available in that date. Please try another.");
            } else {
                System.out.println("The price for this flight is " + price + " kr.");
            }
        }
        
        System.out.print("If you want to proceed with the booking, please insert the credit card. "
                         + "Otherwise just press enter: ");
        String creditCard = input.next();
        
        if (!creditCard.equals("")) {
            String bookingId;
            
            bookingId = port.bookTicket(flights, date, creditCard, token);
            
            System.out.println("Your booking id is " + bookingId + ".");
        }
        System.out.println("Thank you for using our services!");
        */
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
