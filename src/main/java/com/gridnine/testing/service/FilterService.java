package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

/**
 * Класс для фильтрации полетов
 */
public interface FilterService {

    /**
     * Метод для фильтрации вылетов до текущего момента времени.
     * @param flights список полетов
     */
    List<Flight> departureCurrentTime(List<Flight> flights);
    /**
     * Метод для фильтрации сегментов с датой прилёта раньше даты вылета.
     * @param flights список полетов
     */
    List<Flight> arrivalBeforeDeparture(List<Flight> flights);
    /**
     * Метод для фильтрации перелетов, где общее время, проведённое на земле, превышает два часа
     * @param flights список полетов
     */
    List<Flight> flightWithInterval(List<Flight> flights);
}
