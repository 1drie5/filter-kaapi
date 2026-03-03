package Sorting;
import java.util.Scanner;

public class SelectionSort {

    // Basic Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int steps = 0;

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;   // Assume current index is minimum

            // Find the minimum element in remaining unsorted array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with first unsorted element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

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

        selectionSort(arr);

        System.out.print("After Sorting: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        sc.close();
    }
}