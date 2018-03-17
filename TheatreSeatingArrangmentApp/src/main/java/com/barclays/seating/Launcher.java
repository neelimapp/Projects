package com.barclays.seating;

import java.util.List;
import java.util.Scanner;

import com.barclays.dao.SeatingRequest;
import com.barclays.dao.SeatingResponse;
import com.barclays.dao.TheatreLayout;
import com.barclays.service.SeatingService;
import com.barclays.service.SeatingServiceImpl;
import com.barclays.utils.SeatingAllocationUtils;

public class Launcher {

	public static void main(String[] args) {
		String response = null;
		String request = getInput();
		System.out.println(request);
		SeatingAllocationUtils seatingAllocationUtils = new SeatingAllocationUtils();
		SeatingService seatingService = new SeatingServiceImpl();
		if (request == null || request.equals("") || request.indexOf("\n\n") < 0) {
			System.err.print("Empty or Null Input!!");
		} else {
			String[] requestElements = request.split("\n\n");
			String layoutString = requestElements[0];
			String customerRequestString = requestElements[1];

			TheatreLayout layout = seatingAllocationUtils.loadLayout(layoutString);
			List<SeatingRequest> seatingRequests = seatingAllocationUtils.loadBookings(customerRequestString);

			List<SeatingResponse> seatingResponses = seatingService.processRequests(layout, seatingRequests);
			response = seatingAllocationUtils.buildResponse(seatingResponses);
		}
		System.out.println(response);
	}

	/**
	 * Method to retrieve input from command line
	 */
	public static String getInput() {
		StringBuilder sb = new StringBuilder();
		try {
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			sb.append(s);
			while (!s.equals("")) {
				s = sc.nextLine();
				sb.append("\n" + s);
			}
			sb.append("\n");
			s = sc.nextLine();
			sb.append(s);
			while (!s.equals("")) {
				s = sc.nextLine();
				sb.append("\n" + s);
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Invalid input : " + e.getMessage());
			e.printStackTrace();
		}
		return sb.toString();
	}

}
