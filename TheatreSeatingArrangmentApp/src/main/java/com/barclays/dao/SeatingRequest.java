package com.barclays.dao;

/**
 * Seating request : name of customer and number of seats requested
 * @author Neelima
 *
 */
public class SeatingRequest {
	/**
	 * Name of the customer
	 */
	private String customerName;
	
	/**
	 * Number of seats requested
	 */
	private int requestedSeats;

	public SeatingRequest(String customerName, int requestedSeats) {
		super();
		this.customerName = customerName;
		this.requestedSeats = requestedSeats;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getRequestedSeats() {
		return requestedSeats;
	}

	public void setRequestedSeats(int requestedSeats) {
		this.requestedSeats = requestedSeats;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + requestedSeats;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeatingRequest other = (SeatingRequest) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (requestedSeats != other.requestedSeats)
			return false;
		return true;
	}

	
	
}
