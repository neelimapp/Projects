package com.barclays.dao;

/**
 * Class to identify theatre section
 * @author Neelima
 *
 */
public class TheatreSection {
	
	/**
	 * Section Number in layout - starts with 1
	 */
	private int sectionNumber;
	
	

	/**
	 * Number of seats in this section
	 */
	private int numberOfSeats;
	
	/**
	 * Number of available seats
	 */
	private int numberOfAvailableSeats;

	public TheatreSection(int sectionNumber, int numberOfSeats, int numberOfAvailableSeats) {
		super();
		this.sectionNumber = sectionNumber;
		this.numberOfSeats = numberOfSeats;
		this.numberOfAvailableSeats = numberOfAvailableSeats;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfAvailableSeats;
		result = prime * result + numberOfSeats;
		result = prime * result + sectionNumber;
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
		TheatreSection other = (TheatreSection) obj;
		if (numberOfAvailableSeats != other.numberOfAvailableSeats)
			return false;
		if (numberOfSeats != other.numberOfSeats)
			return false;
		if (sectionNumber != other.sectionNumber)
			return false;
		return true;
	}

	public int getNumberOfAvailableSeats() {
		return numberOfAvailableSeats;
	}

	public void setNumberOfAvailableSeats(int numberOfAvailableSeats) {
		this.numberOfAvailableSeats = numberOfAvailableSeats;
	}
}
