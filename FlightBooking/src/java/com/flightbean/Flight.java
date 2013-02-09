package com.flightbean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aldecoa & alegeo
 */

@XmlRootElement
public class Flight {
    private String departureCity;
    private String destinationCity;

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }
    
    
}
