package com.barclays.service;

import java.util.List;

import com.barclays.dao.SeatingRequest;
import com.barclays.dao.SeatingResponse;
import com.barclays.dao.TheatreLayout;

public interface SeatingService {
	
	/**
	 * Method to process requests
	 * 
	 * @param layout theatre layout
	 * @param seatingRequests sorted set of seating requests
	 * @return List of seating responses
	 */
    List<SeatingResponse> processRequests(TheatreLayout layout, List<SeatingRequest> seatingRequests);
}
