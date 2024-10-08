package com.gridnine.testing.service.Impl;

import com.gridnine.testing.constant.FlightBuilder;
import com.gridnine.testing.model.Flight;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class FilterServiceImplTest extends TestCase {

    private FilterServiceImpl filterService = new FilterServiceImpl();
    private List<Flight> flights;

    @Before
    public void setUp() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    public void testDepartureCurrentTime() {
        List<Flight> result = filterService.departureCurrentTime(flights);

        assertTrue(result.stream().allMatch(flight -> flight.getSegments().stream()
                .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()))));
    }

    @Test
    public void testArrivalBeforeDeparture() {
        List<Flight> result = filterService.arrivalBeforeDeparture(flights);

        assertTrue(result.stream().allMatch(flight -> flight.getSegments().stream()
                .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate()))));
    }

    @Test
    public void testflightWithInterval() {
        List<Flight> result = filterService.flightWithInterval(flights);

        assertEquals(4, result.size());
    }
}