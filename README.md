FlightBooking_REST_PoC
======================

Flight Booking system (both web service and client on this rep) implemented using REST as Proof of Concept.

Documentation for the REST service:
-----------------------------------
Itinerary: id, flights
Booking: id, itineraryId, date

| Method  | flightbooking/bookings   | flightbooking/bookings/1234 |
| ------- |:------------------------:| ---------------------------:|
| GET     | List all user's bookings | Retrieve a specific booking |
| PUT     |            -             | Update a booking            |
| POST    | Create a booking         |               -             |
| DELETE  |            -             | Cancel a booking            |