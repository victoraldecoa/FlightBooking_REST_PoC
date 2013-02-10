FlightBooking_REST_PoC
======================

Flight Booking system (both web service and client on this rep) implemented using REST as Proof of Concept.

Documentation for the REST service:
-----------------------------------
- Booking: id, itineraryId, date

| Method  | flightbooking/bookings   | flightbooking/bookings/1234 |
| ------- |:------------------------:| ---------------------------:|
| GET     | List all user's bookings | Retrieve a specific booking |
| PUT     |            -             | Update a booking            |
| POST    | Create a new booking     |               -             |
| DELETE  |            -             | Cancel a booking            |

- User authorizing

| Method  | flightbooking/auth                                           |
| ------- |:------------------------------------------------------------:|
| GET     | Get a token in case the specified user and password matches  |

- Itinerary: id, flights

| Method  | flightbooking/checkitinerary                                 |
| ------- |:------------------------------------------------------------:|
| GET     | Get an itinerary for the specified departure and destination |

- Checking the price

| Method  | flightbooking/checkavailable                                                 |
| ------- |:----------------------------------------------------------------------------:|
| GET     | Get the price checking if the specified itinerary is available for that date |

Deploying web service:
----------------------
- Install Apache Tomcat 7.0.32 or upper and run it
- Import the project FlightBooking on NetBeans 7.2
- Run the project

Running client:
---------------
- Import the project FlightBookingClient on NetBeans 7.2
- Run the project (make sure the web service is running)

Run example:
---------------------------
    Username: adfh
    Password: ***
    Username or password are wrong. Try again.

    Username: victor
    Password: ******
    User authorized
    
    Press 1 if you wish to create a new booking.
    Press 2 if you wish to view your bookings.
    Press 3 if you wish to change a booking's date.
    Press 4 if you wish to cancel a booking.
    Press 5 if you wish to exit the system.
    Please enter your choice: 1
    Departure city: Stockholm
    Destination city: Madrid
    A possible itinerary is:
        Dep: Stockholm    Dest: Berlin
        Dep: Berlin    Dest: Madrid
    Select a date (aaaa-mm-dd): 2013-02-06
    The price for this flight is 1500 kr
    If you want to proceed with the booking, please insert the credit card. Otherwise insert 0: 1234-5678-9012-3456
    Your booking id is 564232.
    Your flight was booked with success!
    
    Press 1 if you wish to create a new booking.
    Press 2 if you wish to view your bookings.
    Press 3 if you wish to change a booking's date.
    Press 4 if you wish to cancel a booking.
    Press 5 if you wish to exit the system.
    Please enter your choice: 2
        Booking id: 564232, Itinerary id: 865, Date: 2013-02-06
        Booking id: 564231, Itinerary id: 123, Date: 2013-02-06
        
    Press 1 if you wish to create a new booking.
    Press 2 if you wish to view your bookings.
    Press 3 if you wish to change a booking's date.
    Press 4 if you wish to cancel a booking.
    Press 5 if you wish to exit the system.
    Please enter your choice: 4
    Please enter the ID of the booking to cancel: 564231
