package com.taxipark.model;

import com.taxipark.exception.InvalidCarDataException;

// Наследник Car — Легковой автомобиль. ДОБАВЛЯЕТ новое свойство: объём багажника.
public class Sedan extends Car {

    private int trunkVolume; // литры

    public Sedan(String model, double price, double fuelConsumption, int maxSpeed, int trunkVolume)
            throws InvalidCarDataException {
        super(model, price, fuelConsumption, maxSpeed);

        if (trunkVolume < 0) {
            throw new InvalidCarDataException("Объём багажника не может быть отрицательным: " + trunkVolume);
        }
        this.trunkVolume = trunkVolume;
    }

    public int getTrunkVolume() { return trunkVolume; }

    @Override
    public String getExtraInfo() {
        return "Объём багажника: " + trunkVolume + " л";
    }
}