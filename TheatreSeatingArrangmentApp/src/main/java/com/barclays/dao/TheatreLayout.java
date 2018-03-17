package com.barclays.dao;

import java.util.List;

/**
 * Class to hold the whole theatre layout and other (global) parameters
 * 
 * @author Neelima
 *
 */
public class TheatreLayout {
	
	/**
	 * Total Number of available seats 
	 */
	private int totalAvailableSeats;
	
	/**
	 * Theatre Rows
	 */
	private List<TheatreRow> theatreRows;

	public TheatreLayout(int totalAvailableSeats, List<TheatreRow> theatreRows) {
		super();
		this.totalAvailableSeats = totalAvailableSeats;
		this.theatreRows = theatreRows;
	}

	public int getTotalAvailableSeats() {
		return totalAvailableSeats;
	}

	public void setTotalAvailableSeats(int totalAvailableSeats) {
		this.totalAvailableSeats = totalAvailableSeats;
	}

	public List<TheatreRow> getTheatreRows() {
		return theatreRows;
	}

	public void setTheatreRows(List<TheatreRow> theatreRows) {
		this.theatreRows = theatreRows;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((theatreRows == null) ? 0 : theatreRows.hashCode());
		result = prime * result + totalAvailableSeats;
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
		TheatreLayout other = (TheatreLayout) obj;
		if (theatreRows == null) {
			if (other.theatreRows != null)
				return false;
		} else if (!theatreRows.equals(other.theatreRows))
			return false;
		if (totalAvailableSeats != other.totalAvailableSeats)
			return false;
		return true;
	}

	
}
