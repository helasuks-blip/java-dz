package org.example;

import java.util.Arrays;

public class Vector {
    private final double[] coordinates;

    public Vector(double... coordinates) {
        this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
    }

    public Vector copy() {
        return new Vector(this.coordinates);
    }

    public double get(int index) {
        return coordinates[index];
    }

    public void set(int index, double value) {
        coordinates[index] = value;
    }

    public void increment() {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i]++;
        }
    }

    public void decrement() {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i]--;
        }
    }

    public double dotProduct(Vector other) {
        if (this.coordinates.length != other.coordinates.length) {
            throw new IllegalArgumentException("Векторы должны быть одинаковой размерности!");
        }
        double sum = 0;
        for (int i = 0; i < coordinates.length; i++) {
            sum += this.coordinates[i] * other.coordinates[i];
        }
        return sum;
    }

    public double length() {
        double sumSq = 0;
        for (double coordinate : coordinates) {
            sumSq += coordinate * coordinate;
        }
        return Math.sqrt(sumSq);
    }

    public static double angleBetween(Vector v1, Vector v2) {
        double dot = v1.dotProduct(v2);
        double len1 = v1.length();
        double len2 = v2.length();

        // Формула: cos(a) = (v1 * v2) / (|v1| * |v2|)
        double cosAngle = dot / (len1 * len2);

        // Из-за погрешностей вычислений cosAngle может выйти за пределы [-1, 1],
        // что приведёт к NaN в Math.acos. Ограничиваем значение.
        cosAngle = Math.max(-1.0, Math.min(1.0, cosAngle));

        return Math.acos(cosAngle); // Результат в радианах
    }

    @Override
    public String toString() {
        return Arrays.toString(coordinates);
    }
}