/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightservice;

import com.flightbean.Itinerary;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aldecoa
 */
public class ItinerariesTableMock {
    private static ItinerariesTableMock instance;
    private int counter = 865;
    private Map<String, Itinerary> contentProvider = new HashMap<String, Itinerary>();
    
    public Itinerary createItinerary(Itinerary i) {
        i.setId(String.valueOf(counter++));

        contentProvider.put(i.getId(), i);

        return i;
    }
    
    public Itinerary getById(String id) {
        return contentProvider.get(id);
    }
    
    public static ItinerariesTableMock getInstance() {
        if (instance == null) {
            instance = new ItinerariesTableMock();
        }

        return instance;
    }
}
