package com.taxipark.service;

import com.taxipark.exception.InvalidCarDataException;
import com.taxipark.model.Sedan;
import com.taxipark.model.SUV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaxiParkTest {

    @Test
    public void testCalculateTotalPrice() throws InvalidCarDataException {
        TaxiPark park = new TaxiPark();
        park.addCar(new Sedan("A", 1000, 5, 100, 400));
        park.addCar(new Sedan("B", 2000, 5, 100, 500));

        Assertions.assertEquals(3000, park.calculateTotalPrice(), 0.001);
    }

    @Test
    public void testSortByFuelConsumption() throws InvalidCarDataException {
        TaxiPark park = new TaxiPark();
        park.addCar(new Sedan("Big", 1000, 10.0, 100, 500));
        park.addCar(new Sedan("Small", 1000, 3.0, 100, 400));
        park.addCar(new SUV("Medium", 2000, 7.0, 150, true));

        park.sortByFuelConsumption();

        Assertions.assertEquals(3.0, park.getCars().get(0).getFuelConsumption(), 0.001);
        Assertions.assertEquals(10.0, park.getCars().get(2).getFuelConsumption(), 0.001);
    }

    @Test
    public void testFindBySpeedRange() throws InvalidCarDataException {
        TaxiPark park = new TaxiPark();
        park.addCar(new Sedan("Fast", 1000, 5, 200, 400));
        park.addCar(new Sedan("Slow", 1000, 5, 100, 400));
        park.addCar(new SUV("Normal", 2000, 8, 150, true));

        // Ищем от 140 до 210. Должны найтись Fast (200) и Normal (150).
        Assertions.assertEquals(2, park.findBySpeedRange(140, 210).size());
    }

    @Test
    public void testInvalidPriceThrowsException() {
        Assertions.assertThrows(InvalidCarDataException.class, () -> {
            new Sedan("Bad", -100, 5, 100, 400);
        });
    }

    @Test
    public void testInvalidTrunkVolumeThrowsException() {
        Assertions.assertThrows(InvalidCarDataException.class, () -> {
            new Sedan("BadTrunk", 1000, 5, 100, -50);
        });
    }
}