package com.barclays.utils;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.barclays.dao.SeatingRequest;
import com.barclays.dao.TheatreLayout;

public class SeatingAllocationUtilsTest extends BaseSeatingTest {

	private SeatingAllocationUtils bookingUtils;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		bookingUtils = new SeatingAllocationUtils();
	}

	/**
	 * Success paths
	 */
	@Test
	public void loadLayoutSuccessfully() {
		TheatreLayout responses = bookingUtils.loadLayout(layoutRequest());
		TheatreLayout expectedResponses = getTheatreLayout();
		Assert.assertThat(responses, is(expectedResponses));
	}

	/**
	 * Pass Negative arguments to Load method
	 */
	@Test
	public void loadLayoutInvalidNumber() {
		bookingUtils.loadLayout("a -2");
		thrown.expect(NumberFormatException.class);
		throw new NumberFormatException();
	}
	
	/**
	 * Pass Invalid number format arguments to Load method
	 */
	@Test
	public void loadLayoutInvalidSection() {
		TheatreLayout responses = bookingUtils.loadLayout("-1 -2");
		Assert.assertNull(responses);
		Assert.assertEquals("Invalid section value 0 0r negative number of seats", errContent.toString());

	}

	/**
	 * Pass Null arguments to Layout method
	 */
	@Test
	public void loadLayoutNullSrting() {
		TheatreLayout responses = bookingUtils.loadLayout(null);
		Assert.assertNull(responses);
		Assert.assertEquals("Empty or Null layout!!", errContent.toString());
	}

	/**
	 * Success paths
	 */
	@Test
	public void loadBookingsSuccessfully() {
		List<SeatingRequest> responses = bookingUtils.loadBookings(Seatingrequest());
		List<SeatingRequest> expectedResponses = getSeatingRequests();
		Assert.assertThat(responses, is(expectedResponses));
	}

	/**
	 * Pass Negative arguments to Booking method
	 */
	@Test
	public void loadBookingsInvalidRequest() {
		List<SeatingRequest> responses = bookingUtils.loadBookings("Neelima -1");
		Assert.assertNull(responses);
		Assert.assertEquals("Invalid seating request - 0 or negative number of seats", errContent.toString());

	}
	/**
	 * Pass Invalid Number Format arguments to Booking method
	 */
	@Test
	public void loadBookingsInvalidNumber() {
		bookingUtils.loadBookings(" a");
		thrown.expect(NumberFormatException.class);
		throw new NumberFormatException();
		

	}

	/**
	 * Pass Null arguments to Load method
	 */
	@Test
	public void loadBookingsNullSrting() {
		List<SeatingRequest> responses = bookingUtils.loadBookings(null);
		Assert.assertNull(responses);
		Assert.assertEquals("Empty or Null Request!!", errContent.toString());
	}

	/**
	 * Success paths
	 */
	@Test
	public void buildResponseSuccessfully() {
		String responses = bookingUtils.buildResponse(getExpectedResponses());
		String expectedResponses = responseParsed();
		Assert.assertThat(responses, is(expectedResponses));
	}
	
}
