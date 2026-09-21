import java.util.Scanner;

// задача 10
public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Количество элементов в последовательности: ");
        int size = sc.nextInt();

        int[] arr = new int[size];

        System.out.println("Введите " + size + " чисел последовательности:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Введите n: ");
        int n = sc.nextInt();

        // защита от деления на ноль
        if (n == 0) {
            System.out.println("n не может быть нулём.");
            sc.close();
            return;
        }

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            if (num >= 10000 && num <= 99999
                    && num % n == 0
                    && num / 10000 == 5
                    && hasUniqueDigits(num)) {

                System.out.println("Число: " + num);
                count++;
            }
        }

        System.out.println("Количество таких чисел: " + count);
        sc.close();
    }

    // Проверяет, что все цифры числа различны
    public static boolean hasUniqueDigits(int num) {
        String s = String.valueOf(num);
        for (int j = 0; j < s.length(); j++) {
            for (int k = j + 1; k < s.length(); k++) {
                if (s.charAt(j) == s.charAt(k)) {
                    return false;
                }
            }
        }
        return true;
    }
}