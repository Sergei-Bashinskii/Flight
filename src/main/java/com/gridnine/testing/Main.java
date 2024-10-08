package com.gridnine.testing;

import com.gridnine.testing.constant.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FilterService;
import com.gridnine.testing.service.Impl.FilterServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Все перелеты.");
        flights.forEach(System.out::println);

        FilterService filter = new FilterServiceImpl();

        List<Flight> departureCurrentTime = filter.departureCurrentTime(flights);
        System.out.println("Вылет до текущего момента времени.");
        departureCurrentTime.forEach(System.out::println);

        List<Flight> arrivalBeforeDeparture = filter.arrivalBeforeDeparture(flights);
        System.out.println("Прилет не может быть раньше вылета");
        arrivalBeforeDeparture.forEach(System.out::println);

        List<Flight> flightWithInterval = filter.flightWithInterval(flights);
        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа");
        flightWithInterval.forEach(System.out::println);
    }
}