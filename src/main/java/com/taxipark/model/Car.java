package com.taxipark.model;

import com.taxipark.exception.InvalidCarDataException;

// Абстрактный класс-шаблон для всех машин.
public abstract class Car {

    private String model;
    private double price;
    private double fuelConsumption;
    private int maxSpeed;

    public Car(String model, double price, double fuelConsumption, int maxSpeed)
            throws InvalidCarDataException {

        if (model == null || model.isEmpty()) {
            throw new InvalidCarDataException("Модель не может быть пустой");
        }
        if (price < 0) {
            throw new InvalidCarDataException("Цена не может быть отрицательной: " + price);
        }
        if (fuelConsumption < 0) {
            throw new InvalidCarDataException("Расход топлива не может быть отрицательным: " + fuelConsumption);
        }
        if (maxSpeed <= 0) {
            throw new InvalidCarDataException("Скорость должна быть больше нуля: " + maxSpeed);
        }

        this.model = model;
        this.price = price;
        this.fuelConsumption = fuelConsumption;
        this.maxSpeed = maxSpeed;
    }

    public String getModel() { return model; }
    public double getPrice() { return price; }
    public double getFuelConsumption() { return fuelConsumption; }
    public int getMaxSpeed() { return maxSpeed; }

    // Абстрактный метод: каждая конкретная машина сама расскажет о своих доп. свойствах.
    public abstract String getExtraInfo();

    @Override
    public String toString() {
        return "Модель: " + model + " | Цена: " + price + " | Расход: " + fuelConsumption
                + " | Скорость: " + maxSpeed + " | " + getExtraInfo();
    }
}