package com.taxipark.util;

import com.taxipark.exception.InvalidCarDataException;
import com.taxipark.factory.CarFactory;
import com.taxipark.model.Car;
import com.taxipark.service.TaxiPark;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Утилита для чтения данных из текстового файла.
public class FileReaderUtil {

    private static final Logger logger = LogManager.getLogger(FileReaderUtil.class);

    public static void loadCarsFromFile(String filePath, TaxiPark taxiPark) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(";");

                if (parts.length != 5) {
                    logger.warn("Строка " + lineNumber + ": неверное количество полей -> " + line);
                    continue;
                }

                try {
                    String type = parts[0].trim();
                    String model = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    double fuel = Double.parseDouble(parts[3].trim());
                    int speed = Integer.parseInt(parts[4].trim());

                    Car car = CarFactory.createCar(type, model, price, fuel, speed);
                    taxiPark.addCar(car);

                } catch (NumberFormatException e) {
                    logger.error("Строка " + lineNumber + ": ошибка формата числа -> " + line);

                } catch (InvalidCarDataException e) {
                    logger.error("Строка " + lineNumber + ": " + e.getMessage() + " -> " + line);
                }
            }
        } catch (IOException e) {
            logger.error("Не удалось прочитать файл: " + e.getMessage());
        }
    }
}