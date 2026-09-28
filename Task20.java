import java.util.Scanner;

public class Task20 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество строк: ");
        int n = sc.nextInt();

        System.out.print("Введите количество столбцов: ");
        int m = sc.nextInt();

        int[][] a = new int[n][m];

        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        int[] sums = new int[n];
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = 0; j < m; j++) {
                s = s + a[i][j];
            }
            sums[i] = s;
        }


        mergeSort(a, sums, 0, n - 1, m);

        System.out.println("Матрица после сортировки строк по возрастанию суммы:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }

    }

    static void mergeSort(int[][] a, int[] sums, int left, int right, int m) {

        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(a, sums, left, mid, m);

        mergeSort(a, sums, mid + 1, right, m);

        merge(a, sums, left, mid, right, m);
    }

    static void merge(int[][] a, int[] sums, int left, int mid, int right, int m) {

        int size = right - left + 1;

        int[] tempSums = new int[size];
        int[][] tempRows = new int[size][m];

        int i = left;
        int j = mid + 1;
        int k = 0;


        while (i <= mid && j <= right) {

            if (sums[i] <= sums[j]) {
                tempSums[k] = sums[i];
                copyRow(a[i], tempRows[k], m);
                i++;
            } else {
                tempSums[k] = sums[j];
                copyRow(a[j], tempRows[k], m);
                j++;                             
            }
            k++;
        }

        while (i <= mid) {
            tempSums[k] = sums[i];
            copyRow(a[i], tempRows[k], m);
            i++;
            k++;
        }

        while (j <= right) {
            tempSums[k] = sums[j];
            copyRow(a[j], tempRows[k], m);
            j++;
            k++;
        }


        for (int t = 0; t < size; t++) {
            sums[left + t] = tempSums[t];
            copyRow(tempRows[t], a[left + t], m);
        }
    }


    static void copyRow(int[] src, int[] dst, int m) {
        for (int j = 0; j < m; j++) {
            dst[j] = src[j];
        }
    }
}