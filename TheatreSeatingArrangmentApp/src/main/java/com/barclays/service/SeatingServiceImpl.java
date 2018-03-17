package com.barclays.service;

import java.util.ArrayList;
import java.util.List;

import com.barclays.dao.Constants;
import com.barclays.dao.SeatingRequest;
import com.barclays.dao.SeatingResponse;
import com.barclays.dao.TheatreLayout;
import com.barclays.dao.TheatreRow;
import com.barclays.dao.TheatreSection;

/**
 * Class to process the requests and build responses
 * 
 * @author Neelima
 *
 */
public class SeatingServiceImpl implements SeatingService {

	/**
	 * Threshold value to check for exact match with a buffer of 1 row
	 */
	private final int ROW_THRESHOLD = 1;

	/**
	 * Method to process requests
	 * 
	 * @param layout
	 *            theatre layout
	 * @param seatingRequests
	 *            sorted set of seating requests
	 * @return List of seating responses
	 */
	@Override
	public List<SeatingResponse> processRequests(TheatreLayout layout, List<SeatingRequest> seatingRequests) {
		if (layout == null || seatingRequests == null) {
			throw new IllegalArgumentException("Layout or seatingRequest Can't be null");
		}
		List<SeatingResponse> seatingResponses = new ArrayList<SeatingResponse>();
		// iterate through list of seating requests
		for (SeatingRequest seatingRequest : seatingRequests) {
			int foundRowNumber = 0;
			int foundSectionNumber = 0;
			SeatingResponse seatingResponse = new SeatingResponse();
			seatingResponse.setIsServiced(false);
			seatingResponse.setIsExactMatch(false);
			seatingResponse.setCustomerName(seatingRequest.getCustomerName());
			//if requested seats more than available seats
			if (seatingRequest.getRequestedSeats() > layout.getTotalAvailableSeats()) {
				seatingResponse.setStatusMessage(Constants.NO_SEATS);
			} else {
				//iterate through rows 
				for (int i = 0; i < layout.getTheatreRows().size(); i++) {
					// If seating response is serviced and row - foundRowNumber > 1
					if (seatingResponse.getIsServiced() && i - foundRowNumber > ROW_THRESHOLD) {
						break;
					} else {
						TheatreRow theatreRow = layout.getTheatreRows().get(i);
						//iterate through sections
						for (TheatreSection section : theatreRow.getTheatreSections()) {
							int requestedSeats = seatingRequest.getRequestedSeats();
							if (requestedSeats <= 0) {
								System.err.print("Invalid seating request - 0 or negative number of seats");
								return null;
							} else if (requestedSeats <= section.getNumberOfAvailableSeats()) {
								if (requestedSeats == section.getNumberOfAvailableSeats()) {
									foundRowNumber = theatreRow.getRowNumber();
									foundSectionNumber = section.getSectionNumber();
									seatingResponse.setIsServiced(true);
									seatingResponse.setIsExactMatch(true);
									break;
								} else if (!seatingResponse.getIsServiced()) {
									foundRowNumber = theatreRow.getRowNumber();
									foundSectionNumber = section.getSectionNumber();
									seatingResponse.setIsServiced(true);
								}
							}
						} // for{}
						if (seatingResponse.getIsExactMatch()) {
							break;
						}
					}
				} // for{}
				//Update response variables after request is serviced
				if (seatingResponse.getIsServiced()) {
					seatingResponse.setRowNumber(foundRowNumber);
					seatingResponse.setSectionNumber(foundSectionNumber);
					TheatreSection section = layout.getTheatreRows().get(foundRowNumber - 1).getTheatreSections()
							.get(foundSectionNumber - 1);
					section.setNumberOfAvailableSeats(
							section.getNumberOfAvailableSeats() - seatingRequest.getRequestedSeats());
					layout.setTotalAvailableSeats(layout.getTotalAvailableSeats() - seatingRequest.getRequestedSeats());
				} else {
					seatingResponse.setStatusMessage(Constants.SPLIT_PARTY);
					
					
				}
			}
			seatingResponses.add(seatingResponse);
		} // for{}
		return seatingResponses;
	} // processRequests(){}
}