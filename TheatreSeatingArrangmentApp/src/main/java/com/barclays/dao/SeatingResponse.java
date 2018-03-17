package com.barclays.dao;

/**
 * 
 * Seating Response - either row and section number information or error message
 * @author Neelima
 *
 */
public class SeatingResponse {

	/**
	 * Error message to customer
	 */
	private String statusMessage;
	
	/**
	 * name of the customer
	 */
	private String customerName;
	
	/**
	 * Row Number allocated
	 */
	private int rowNumber;
	
	/**
	 * Section Number allocated
	 */
	private int sectionNumber;
	
	/**
	 * Flag to check if the request is processed successfully or resulted in an error
	 */
	private Boolean isServiced;
	
	/**
	 * Flag to check if number of seats exactly matched the section's available seats
	 */
	private Boolean isExactMatch;
	
	public SeatingResponse(String statusMessage, String customerName, int rowNumber, int sectionNumber,
			Boolean isServiced, Boolean isExactMatch) {
		super();
		this.statusMessage = statusMessage;
		this.customerName = customerName;
		this.rowNumber = rowNumber;
		this.sectionNumber = sectionNumber;
		this.isServiced = isServiced;
		this.isExactMatch = isExactMatch;
	}
	
	public SeatingResponse() {
		super();
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public Boolean getIsServiced() {
		return isServiced;
	}

	public void setIsServiced(Boolean isServiced) {
		this.isServiced = isServiced;
	}

	public Boolean getIsExactMatch() {
		return isExactMatch;
	}

	public void setIsExactMatch(Boolean isExactMatch) {
		this.isExactMatch = isExactMatch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((isExactMatch == null) ? 0 : isExactMatch.hashCode());
		result = prime * result + ((isServiced == null) ? 0 : isServiced.hashCode());
		result = prime * result + rowNumber;
		result = prime * result + sectionNumber;
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
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
		SeatingResponse other = (SeatingResponse) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (isExactMatch == null) {
			if (other.isExactMatch != null)
				return false;
		} else if (!isExactMatch.equals(other.isExactMatch))
			return false;
		if (isServiced == null) {
			if (other.isServiced != null)
				return false;
		} else if (!isServiced.equals(other.isServiced))
			return false;
		if (rowNumber != other.rowNumber)
			return false;
		if (sectionNumber != other.sectionNumber)
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		return true;
	}
	
	
	
}
