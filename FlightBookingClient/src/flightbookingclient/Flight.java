package flightbookingclient;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aldecoa & alegeo
 */
@XmlRootElement
public class Flight {

    private String departureCity;
    private String destinationCity;

    public Flight(String departureCity, String destinationCity) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
    }

    public Flight() {
    }

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
