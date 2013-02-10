package flightbookingclient;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aldecoa & alegeo
 */
@XmlRootElement
public class BookedFlight {

    private String bookingId;
    private String itineraryId;
    private String date;

    public String getDate() {
        return date;
    }

    public String getItineraryId() {
        return itineraryId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Booking id: " + bookingId + ", "
                + "Itinerary id: " + itineraryId + ", "
                + "Date: " + date;

    }
}
