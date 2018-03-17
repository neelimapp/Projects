package com.barclays.dao;

import java.util.List;

/**
 * Class to identify theatre row
 * @author Neelima
 *
 */
public class TheatreRow {
	/**
	 * Row Number - starts with 1
	 */
	private int rowNumber;

	/**
	 * List of Theatre Sections in this row
	 */
	private List<TheatreSection> theatreSections;
	
	public TheatreRow(int rowNumber, List<TheatreSection> theatreSections) {
		super();
		this.rowNumber = rowNumber;
		this.theatreSections = theatreSections;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public List<TheatreSection> getTheatreSections() {
		return theatreSections;
	}

	public void setTheatreSections(List<TheatreSection> theatreSections) {
		this.theatreSections = theatreSections;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rowNumber;
		result = prime * result + ((theatreSections == null) ? 0 : theatreSections.hashCode());
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
		TheatreRow other = (TheatreRow) obj;
		if (rowNumber != other.rowNumber)
			return false;
		if (theatreSections == null) {
			if (other.theatreSections != null)
				return false;
		} else if (!theatreSections.equals(other.theatreSections))
			return false;
		return true;
	}
	
	
}