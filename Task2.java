import java.util.Scanner;
//задача 20
public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Количество элементов в последовательности: ");
        int size = sc.nextInt();

        int[] numbers = new int[size];

        System.out.println("Введите " + size + " чисел:");
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = sc.nextInt();
        }

        int count = 0;

        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];

            if (isOnly357(num) && isPrime(num)) {
                System.out.println("Найдено простое число: " + num);
                count++;
            }
        }
        System.out.println("Количество простых чисел: " + count);
    }

    public static boolean isOnly357(int num) {
        String sNum = String.valueOf(num);
        for (int i = 0; i < sNum.length(); i++) {
            char c = sNum.charAt(i);
            if (c != '3' && c != '5' && c != '7') {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
