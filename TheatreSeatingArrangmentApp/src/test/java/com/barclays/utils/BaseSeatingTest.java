package com.barclays.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.barclays.dao.SeatingRequest;
import com.barclays.dao.SeatingResponse;
import com.barclays.dao.TheatreLayout;
import com.barclays.dao.TheatreRow;
import com.barclays.dao.TheatreSection;

public class BaseSeatingTest {
	protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	/**
	 * Random generation of seating requests
	 */
	public List<SeatingRequest> getSeatingRequests() {
		List<SeatingRequest> seatingRequests = new ArrayList<SeatingRequest>();
		seatingRequests.add(new SeatingRequest("Smith", 2));
		seatingRequests.add(new SeatingRequest("Jones", 5));
		seatingRequests.add(new SeatingRequest("Davis", 6));
		seatingRequests.add(new SeatingRequest("Wilson", 100));
		seatingRequests.add(new SeatingRequest("Johnson", 3));
		seatingRequests.add(new SeatingRequest("Williams", 4));
		seatingRequests.add(new SeatingRequest("Brown", 8));
		seatingRequests.add(new SeatingRequest("Miller", 12));

		return seatingRequests;
	}

	public TheatreLayout getTheatreLayout() {
		List<TheatreRow> rows = new ArrayList<TheatreRow>();
		List<TheatreSection> sections = new ArrayList<TheatreSection>();
		sections.add(new TheatreSection(1, 6, 6));
		sections.add(new TheatreSection(2, 6, 6));
		rows.add(new TheatreRow(1, sections));

		sections = new ArrayList<TheatreSection>();
		sections.add(new TheatreSection(1, 3, 3));
		sections.add(new TheatreSection(2, 5, 5));
		sections.add(new TheatreSection(3, 5, 5));
		sections.add(new TheatreSection(4, 3, 3));
		rows.add(new TheatreRow(2, sections));

		sections = new ArrayList<TheatreSection>();
		sections.add(new TheatreSection(1, 4, 4));
		sections.add(new TheatreSection(2, 6, 6));
		sections.add(new TheatreSection(3, 6, 6));
		sections.add(new TheatreSection(4, 4, 4));
		rows.add(new TheatreRow(3, sections));

		sections = new ArrayList<TheatreSection>();
		sections.add(new TheatreSection(1, 2, 2));
		sections.add(new TheatreSection(2, 8, 8));
		sections.add(new TheatreSection(3, 8, 8));
		sections.add(new TheatreSection(4, 2, 2));
		rows.add(new TheatreRow(4, sections));

		sections = new ArrayList<TheatreSection>();
		sections.add(new TheatreSection(1, 6, 6));
		sections.add(new TheatreSection(2, 6, 6));
		rows.add(new TheatreRow(5, sections));

		return new TheatreLayout(80, rows);
	}

	/**
	 * Get Expected responses
	 */
	public List<SeatingResponse> getExpectedResponses() {
		List<SeatingResponse> responses = new ArrayList<SeatingResponse>();

		responses.add(new SeatingResponse(null, "Smith", 1, 1, true, false));
		responses.add(new SeatingResponse(null, "Jones", 2, 2, true, true));
		responses.add(new SeatingResponse(null, "Davis", 1, 2, true, true));
		responses.add(new SeatingResponse("Sorry, we can't handle your party.", "Wilson", 0, 0, false, false));
		responses.add(new SeatingResponse(null, "Johnson", 2, 1, true, true));
		responses.add(new SeatingResponse(null, "Williams", 1, 1, true, true));
		responses.add(new SeatingResponse(null, "Brown", 4, 2, true, true));
		responses.add(new SeatingResponse("Call to split party.", "Miller", 0, 0, false, false));

		return responses;
	}

	/**
	 * Raw Seating Request
	 */
	public String Seatingrequest() {
		String seatingRequest = "Smith 2\nJones 5\nDavis 6\nWilson 100\nJohnson 3\nWilliams 4\nBrown 8\nMiller 12";

		return seatingRequest;
	}

	/**
	 * Raw Layout Request
	 */
	public String layoutRequest() {
		String layoutRequest = "6 6\n3 5 5 3\n4 6 6 4\n2 8 8 2\n6 6";

		return layoutRequest;
	}

	/**
	 * Response After Parsing
	 */
	public String responseParsed() {
		String responseParsed = "Smith Row 1 Section 1\nJones " + "Row 2 Section 2\nDavis Row 1 Section 2\nWilson "
				+ "Sorry, we can't handle your party.\nJohnson Row 2 Section 1\nWilliams "
				+ "Row 1 Section 1\nBrown Row 4 Section 2\nMiller Call to split party.";

		return responseParsed;
	}

	/**
	 * Raw Raw Request
	 */
	public String rawRequest() {
		String rawRequest = "6 6\n3 5 5 3\n4 6 6 4\n2 8 "
				+ "8 2\n6 6\n\nSmith 2\nJones 5\nDavis 6\nWilson 100\nJohnson 3\nWilliams 4\nBrown 8\nMiller 12";

		return rawRequest;
	}

}
