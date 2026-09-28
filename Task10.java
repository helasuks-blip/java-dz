import java.util.Scanner;

public class Task10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер квадратной матрицы (N): ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];

        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Элемент [" + (i+1) + "][" + (j+1) + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }

        int choiceElement = 0;
        while (choiceElement < 1 || choiceElement > 3) {
            System.out.println("\nВыберите, из какого элемента строки вычитать среднее арифметическое:");
            System.out.println("1 - из максимального");
            System.out.println("2 - из минимального");
            System.out.println("3 - из нулевого (если 0 нет — строка не меняется)");
            System.out.print("Ваш выбор: ");
            choiceElement = scanner.nextInt();

            if (choiceElement < 1 || choiceElement > 3) {
                System.out.println("Ошибка! Введите число от 1 до 3.");
            }
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum = sum + matrix[i][j];
            }
            int average = sum / n;

            int maxElement = matrix[i][0];
            int minElement = matrix[i][0];
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] > maxElement) {
                    maxElement = matrix[i][j];
                }
                if (matrix[i][j] < minElement) {
                    minElement = matrix[i][j];
                }
            }

            int targetValue = 0;
            boolean targetFound = false;

            if (choiceElement == 1) {
                targetValue = maxElement;
                targetFound = true;
            } else if (choiceElement == 2) {
                targetValue = minElement;
                targetFound = true;
            } else if (choiceElement == 3) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        targetValue = 0;
                        targetFound = true;
                        break;
                    }
                }
            }

            if (targetFound) {
                int newValue = targetValue - average;
                boolean replaced = false;
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == targetValue && !replaced) {
                        matrix[i][j] = newValue;
                        replaced = true;
                    }
                }
            }
        }

        int choiceSort = 0;
        while (choiceSort < 1 || choiceSort > 2) {
            System.out.println("\nВыберите направление сортировки строк по диагонали:");
            System.out.println("1 - по возрастанию");
            System.out.println("2 - по убыванию");
            System.out.print("Ваш выбор: ");
            choiceSort = scanner.nextInt();

            if (choiceSort < 1 || choiceSort > 2) {
                System.out.println("Ошибка! Введите число 1 или 2.");
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean needSwap = false;

                if (choiceSort == 1) {
                    if (matrix[j][j] > matrix[j + 1][j + 1]) {
                        needSwap = true;
                    }
                } else if (choiceSort == 2) {
                    if (matrix[j][j] < matrix[j + 1][j + 1]) {
                        needSwap = true;
                    }
                }

                if (needSwap) {
                    int[] tempRow = matrix[j];
                    matrix[j] = matrix[j + 1];
                    matrix[j + 1] = tempRow;
                }
            }
        }


        System.out.println("\nПолученная матрица:");
        printMatrix(matrix);

        scanner.close();
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}