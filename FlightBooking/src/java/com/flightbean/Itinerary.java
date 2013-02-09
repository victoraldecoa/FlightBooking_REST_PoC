/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightbean;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aldecoa
 */
@XmlRootElement
public class Itinerary {
    private String id;
    
    @XmlElementWrapper(name = "flights")
    @XmlElement(name = "flight")
    public ArrayList<Flight> flights;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public Itinerary() {
        
    }
}
