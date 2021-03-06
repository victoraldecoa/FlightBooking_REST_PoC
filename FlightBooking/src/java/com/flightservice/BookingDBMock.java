/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightservice;

import com.flightbean.BookedFlight;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aldecoa & alegeo
 */
public class BookingDBMock {

    private static BookingDBMock instance;
    private Map<String, BookedFlight> contentProvider = new HashMap<String, BookedFlight>();
    
    private int counter = 564232;

    private BookingDBMock() {
        BookedFlight bf = new BookedFlight();
        bf.setItineraryId("123");
        bf.setBookingId("564231");
        bf.setDate("2013-02-06");
        contentProvider.put("564231", bf);
    }

    public Map<String, BookedFlight> getModel() {
        return contentProvider;
    }
    
    public BookedFlight createBooking(BookedFlight b) {
        b.setBookingId(String.valueOf(counter++));
        
        contentProvider.put(b.getBookingId(), b);
        
        return b;
    }

    public static BookingDBMock getInstance() {
        if (instance == null) {
            instance = new BookingDBMock();
        }

        return instance;
    }
}
