package com.taxipark.service;

import com.taxipark.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

// Класс Таксопарк (паттерн Creator — он создаёт/хранит объекты машин).
public class TaxiPark {

    private static final Logger logger = LogManager.getLogger(TaxiPark.class);

    private List<Car> cars;

    public TaxiPark() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        if (car == null) {
            logger.error("Попытка добавить null в таксопарк");
            return;
        }
        cars.add(car);
        logger.info("Добавлена машина: " + car.getModel());
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Car car : cars) {
            total = total + car.getPrice();
        }
        logger.info("Общая стоимость таксопарка: " + total);
        return total;
    }

    public void sortByFuelConsumption() {
        cars.sort(new FuelConsumptionComparator());
        logger.info("Машины отсортированы по расходу топлива");
    }

    public List<Car> findBySpeedRange(int minSpeed, int maxSpeed) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMaxSpeed() >= minSpeed && car.getMaxSpeed() <= maxSpeed) {
                result.add(car);
            }
        }
        logger.info("Найдено машин в диапазоне " + minSpeed + "-" + maxSpeed + ": " + result.size());
        return result;
    }

    public List<Car> getCars() {
        return cars;
    }
}