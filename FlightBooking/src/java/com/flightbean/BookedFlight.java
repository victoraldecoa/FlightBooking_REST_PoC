package com.flightbean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookedFlight {
    
        private String bookingId;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
}
