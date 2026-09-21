import java.util.Scanner;
//задача 30
public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество чисел: ");
        int size = sc.nextInt();

        int[] numbers = new int[size];


        System.out.println("Введите " + size + " чисел:");
        for (int i = 0; i < size; i++) {
            numbers[i] = sc.nextInt();
        }

        System.out.println("Числа-палиндромы, квадрат которых тоже палиндром:");


        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            int square = num * num;


            if (isPalindrome(num) && isPalindrome(square)) {
                System.out.println(num + " (квадрат: " + square + ")");
            }
        }

        sc.close();
    }


    public static boolean isPalindrome(int num) {
        // Превращаем число в строку
        String s = String.valueOf(num);

        // левый (начало) и правый (конец)
        int left = 0;
        int right = s.length() - 1;


        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
