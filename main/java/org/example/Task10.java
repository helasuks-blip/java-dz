package org.example;

import java.util.Scanner;

public class Task10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Введите количество векторов (m): ");
        int m = scanner.nextInt();

        System.out.print("Введите размерность пространства: ");
        int dimension = scanner.nextInt();

        Vector[] vectors = new Vector[m];

        System.out.println("Введите координаты для каждого вектора:");
        for (int i = 0; i < m; i++) {
            System.out.println("Вектор №" + (i + 1) + ":");
            double[] coords = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                System.out.print("  Координата " + (j + 1) + ": ");
                coords[j] = scanner.nextDouble();
            }
            vectors[i] = new Vector(coords);
        }

        // --- ИНКРЕМЕНТ И ДЕКРЕМЕНТ ОТ ИСХОДНОГО ВЕКТОРА ---
        System.out.println("\n--- Инкремент и Декремент ---");

        for (int i = 0; i < vectors.length; i++) {
            Vector original = vectors[i].copy();

            Vector incremented = vectors[i].copy();
            incremented.increment();
            System.out.println("V" + (i+1) + incremented);

            Vector decremented = original.copy();
            decremented.decrement();
            System.out.println("V" + (i+2) + decremented);
            System.out.println("----------------------------------------");
        }

        System.out.println("\n--- Длины векторов ---");
        for (int i = 0; i < vectors.length; i++) {
            System.out.printf("Длина вектора V%d: %.4f%n", (i+1), vectors[i].length());
        }

        System.out.println("\n--- Вычисление углов между парами векторов ---");
        for (int i = 0; i < vectors.length - 1; i++) {
            for (int j = i + 1; j < vectors.length; j++) {
                double angleRadians = Vector.angleBetween(vectors[i], vectors[j]);
                double angleDegrees = Math.toDegrees(angleRadians);
                System.out.printf("Угол между V%d и V%d: %.4f радиан (%.2f градусов)%n",
                        i, j, angleRadians, angleDegrees);
            }
        }

        scanner.close();
    }
}