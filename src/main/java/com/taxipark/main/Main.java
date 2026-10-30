package com.taxipark.main;

import com.taxipark.model.Car;
import com.taxipark.service.TaxiPark;
import com.taxipark.util.FileReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        TaxiPark park = new TaxiPark();
        String filePath = "C:/уник/джава/Laba 4/src/cars.txt";

        logger.info("=== Запуск приложения Таксопарк ===");
        FileReaderUtil.loadCarsFromFile(filePath, park);

        logger.info("--- Все машины в парке ---");
        for (Car c : park.getCars()) {
            logger.info(c.toString());
        }

        logger.info("--- Общая стоимость ---");
        logger.info("Стоимость парка: " + park.calculateTotalPrice());

        logger.info("--- Сортировка по расходу топлива ---");
        park.sortByFuelConsumption();
        for (Car c : park.getCars()) {
            logger.info(c.toString());
        }

        logger.info("--- Поиск машин со скоростью 190-220 км/ч ---");
        List<Car> found = park.findBySpeedRange(190, 220);
        if (found.isEmpty()) {
            logger.info("Машины не найдены.");
        } else {
            for (Car c : found) {
                logger.info(c.toString());
            }
        }

        logger.info("=== Работа приложения завершена ===");
    }
}