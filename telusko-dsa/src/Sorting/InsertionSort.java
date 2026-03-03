package Sorting;
import java.util.Scanner;

public class InsertionSort {

    // Basic Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        int steps = 0;

        for (int i = 1; i < n; i++) {

            int key = arr[i];   // Element to be inserted
            int j = i - 1;

            // Move elements greater than key one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;

            System.out.print("Pass " + steps + ": ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
            steps++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter the elements in the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Before Sorting: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        insertionSort(arr);

        System.out.print("After Sorting: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        sc.close();
    }
}