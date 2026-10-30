package com.taxipark.service;

import com.taxipark.model.Car;
import java.util.Comparator;

// Компаратор для сортировки машин по расходу топлива (по возрастанию).
public class FuelConsumptionComparator implements Comparator<Car> {

    @Override
    public int compare(Car c1, Car c2) {
        return Double.compare(c1.getFuelConsumption(), c2.getFuelConsumption());
    }
}