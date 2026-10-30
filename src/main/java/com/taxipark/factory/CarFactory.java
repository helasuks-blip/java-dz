package com.taxipark.factory;

import com.taxipark.exception.InvalidCarDataException;
import com.taxipark.model.Car;
import com.taxipark.model.Sedan;
import com.taxipark.model.SUV;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Фабрика — централизованное создание машин (паттерн Factory Method).
public class CarFactory {

    private static final Logger logger = LogManager.getLogger(CarFactory.class);

    public static Car createCar(String type, String model, double price,
                                double fuelConsumption, int maxSpeed)
            throws InvalidCarDataException {

        if (type == null) {
            throw new InvalidCarDataException("Тип машины равен null");
        }

        Car car;
        if (type.equalsIgnoreCase("Sedan")) {
            // Для седана — багажник 500 л по умолчанию
            car = new Sedan(model, price, fuelConsumption, maxSpeed, 500);
        } else if (type.equalsIgnoreCase("SUV")) {
            // Для внедорожника — полный привод включён по умолчанию
            car = new SUV(model, price, fuelConsumption, maxSpeed, true);
        } else {
            throw new InvalidCarDataException("Неизвестный тип машины: " + type);
        }

        logger.info("Фабрика создала машину: " + car.getModel());
        return car;
    }
}