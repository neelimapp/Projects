package com.barclays.utils;

import java.util.ArrayList;
import java.util.List;

import com.barclays.dao.Constants;
import com.barclays.dao.SeatingRequest;
import com.barclays.dao.SeatingResponse;
import com.barclays.dao.TheatreLayout;
import com.barclays.dao.TheatreRow;
import com.barclays.dao.TheatreSection;

/**
 * Class to load and parse the seating requests and responses
 * 
 * @author Neelima
 *
 */
public class SeatingAllocationUtils {

	public TheatreLayout loadLayout(String layoutString) {
		TheatreLayout theatreLayout = null;
		int totalAvailableSeats = 0;
		List<TheatreRow> theatreRows = new ArrayList<TheatreRow>();
		if (layoutString == null || layoutString.equals("")) {
			System.err.print("Empty or Null layout!!");
			return null;
		} else {
			try {
				String[] layoutArray = layoutString.split("\n");
				int rowNumber = 1;
				for (String rowString : layoutArray) {
					List<TheatreSection> theatreSections = new ArrayList<TheatreSection>();
					String[] sections = rowString.split(" ");
					int sectionNumber = 1;
					for (String section : sections) {
						int sec = Integer.parseInt(section);
						if (!(sec > 0)) {
							System.err.print("Invalid section value 0 0r negative number of seats");
							return null;
						}
						theatreSections.add(new TheatreSection(sectionNumber++, sec, sec));
						totalAvailableSeats += sec;
					}
					theatreRows.add(new TheatreRow(rowNumber++, theatreSections));
				}
				theatreLayout = new TheatreLayout(totalAvailableSeats, theatreRows);
			} catch (Exception e) {
				System.err.print("Error while loading layout : " + e.getMessage());
				e.printStackTrace();
			}
		}

		return theatreLayout;
	}

	/**
	 * Method to load seating requests from string
	 * 
	 * @param request
	 * @return
	 */
	public List<SeatingRequest> loadBookings(String request) {
		List<SeatingRequest> seatingRequests = new ArrayList<SeatingRequest>();

		if (request == null || request.equals("")) {
			System.err.print("Empty or Null Request!!");
			return null;
		} else {
			try {
				String[] seatingRequestsString = request.split("\n");
				for (String seatingRequestString : seatingRequestsString) {
					String[] requestParameters = seatingRequestString.split(" ");
					int reqParam = Integer.parseInt(requestParameters[1]);
					if (!(reqParam >= 0)) {
						System.err.print("Invalid seating request - 0 or negative number of seats");
						return null;
					}
					SeatingRequest seatingRequest = new SeatingRequest(requestParameters[0], reqParam);
					seatingRequests.add(seatingRequest);
				}
			} catch (Exception e) {
				System.err.println("ERROR while loading customer request: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return seatingRequests;
	}

	/**
	 * Method to construct a string from the list of seating responses
	 * 
	 * @param seatingResponses
	 *            seating Responses after the requests are parsed
	 * @return String responses in a single string
	 */
	public String buildResponse(List<SeatingResponse> seatingResponses) {
		StringBuilder sb = new StringBuilder();
		int size = seatingResponses.size();
		for (SeatingResponse seatingResponse : seatingResponses) {
			try {
				if (seatingResponse.getIsServiced()) {
					sb.append(seatingResponse.getCustomerName() + " " + Constants.ROW + seatingResponse.getRowNumber()
							+ " " + Constants.SECTION + seatingResponse.getSectionNumber());
				} else {
					sb.append(seatingResponse.getCustomerName() + " " + seatingResponse.getStatusMessage());
				}
				if (size > 1) {
					sb.append("\n");
					size--;
				}
			} catch (Exception e) {
				System.err.println("ERROR while parsing response: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
