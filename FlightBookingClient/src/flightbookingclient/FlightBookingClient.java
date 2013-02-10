package flightbookingclient;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aldecoa & alegeo
 */
public class FlightBookingClient {

    private static FlightBookingAPI service;
    private static Scanner input;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // get web service instance
        service = FlightBookingAPI.getInstance();

        input = new Scanner(System.in);

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

        int option = 0;
        while (option != 5) {
            System.out.println();
            System.out.println("Press 1 if you wish to create a new booking.");
            System.out.println("Press 2 if you wish to view your bookings.");
            System.out.println("Press 3 if you wish to change a booking's date.");
            System.out.println("Press 4 if you wish to cancel a booking.");
            System.out.println("Press 5 if you wish to exit the system.");
            System.out.print("Please enter your choice: ");

            option = Integer.parseInt(input.next());
            switch (option) {
                case 1:
                    // POST to create a booking
                    getNewBooking();
                    break;
                case 2:
                    // GET all bookings
                    ArrayList<BookedFlight> allBookings = new ArrayList<>(
                            service.getBookings());
                    if (allBookings.isEmpty()) {
                        System.out.println("You don't have any bookings");
                    }
                    for (BookedFlight b : allBookings) {
                        System.out.print("    ");
                        System.out.println(b);
                    }
                    break;
                case 3:
                    // PUT to update a booking
                    System.out.print("Please enter the ID of the booking you wish to change the date: ");
                    BookedFlight bookingGet = service.getBookingById(input.next());
                    System.out.print("Please enter the new date: ");
                    bookingGet.setDate(input.next());
                    service.putBooking(bookingGet);
                    break;
                case 4:
                    // DELETE a booking by the id
                    System.out.print("Please enter the ID of the booking to cancel it: ");
                    service.deleteBookingById(input.next());
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Please choose a number between 1-5 only");
            }
        }
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

    private static void getNewBooking() {
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

        while (price.equals("")) {
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
                + "Otherwise insert 0: ");
        String creditCard = input.next();

        if (!creditCard.equals("0")) {
            BookedFlight booking = new BookedFlight();
            booking.setDate(date);
            booking.setItineraryId(i.getId());

            booking = service.postBooking(booking, creditCard);

            System.out.println("Your booking id is " + booking.getBookingId() + ".");
            System.out.println("Your flight was booked with success!");
        }
    }
}