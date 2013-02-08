package com.flightbean;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FlightBookingBean {
    
    	private Customer customer;

	private BookedFlight[] bookedFlight;

	private String bookingId;

        @XmlElement(name="customer")
        public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

        @XmlElement(name="bookingId")
	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

        @XmlElement(name="bookedFlight")
	public BookedFlight[] getBookedFlight() {
		return bookedFlight;
	}

	public void setBookedFlight(BookedFlight[] bookedFlight) {
		this.bookedFlight = bookedFlight;
	}

    
}
