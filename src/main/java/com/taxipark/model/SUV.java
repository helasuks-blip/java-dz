package com.taxipark.model;

import com.taxipark.exception.InvalidCarDataException;

// Наследник Car — Внедорожник. ДОБАВЛЯЕТ новое свойство: полный привод.
public class SUV extends Car {

    private boolean allWheelDrive; // полный привод

    public SUV(String model, double price, double fuelConsumption, int maxSpeed, boolean allWheelDrive)
            throws InvalidCarDataException {
        super(model, price, fuelConsumption, maxSpeed);
        this.allWheelDrive = allWheelDrive;
    }

    public boolean isAllWheelDrive() { return allWheelDrive; }

    @Override
    public String getExtraInfo() {
        return "Полный привод: " + (allWheelDrive ? "да" : "нет");
    }
}