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
