package com.barclays.service;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.barclays.dao.SeatingResponse;
import com.barclays.utils.BaseSeatingTest;

public class SeatingServiceImplTest extends BaseSeatingTest {
	private SeatingService service;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception{
		service = new SeatingServiceImpl();
	}
	
	/**
	 * Success paths
	 */
	@Test
	public void processRequestsSuccessfully() {
		List<SeatingResponse> responses = service.processRequests(getTheatreLayout(), getSeatingRequests());
		List<SeatingResponse> expectedResponses = getExpectedResponses();
		Assert.assertThat(responses, is(expectedResponses));
	}
	
	/**
	 * Error paths - when Layout is Null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void processRequestsWithoutLayoutData() {
		 service.processRequests(null, getSeatingRequests());
	}
	
	/**
	 * Error paths - when RequestData is Null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void processRequestsWithoutRequestData() {
		 service.processRequests(getTheatreLayout(), null);
	}
}
