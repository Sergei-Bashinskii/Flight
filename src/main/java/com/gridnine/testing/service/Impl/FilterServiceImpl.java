package com.gridnine.testing.service.Impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FilterService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FilterServiceImpl implements FilterService {

    public List<Flight> departureCurrentTime(List<Flight> flights) {
        LocalDateTime time = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(time)))
                .collect(Collectors.toList());
    }
    public List<Flight> arrivalBeforeDeparture(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getArrivalDate()
                                .isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    public List<Flight> flightWithInterval(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();

                    long timeInMinutes = 0;

                    for (int i = 1; i < segments.size(); i++) {
                        Segment prev = segments.get(i - 1);
                        Segment next = segments.get(i);
                        timeInMinutes += Duration.between(prev.getArrivalDate(),
                                next.getDepartureDate()).toMinutes();
                    }

                    return timeInMinutes <= 120;
                })
                .collect(Collectors.toList());
    }
}
