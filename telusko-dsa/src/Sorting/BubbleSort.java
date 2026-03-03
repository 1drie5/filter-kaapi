package Sorting;
import java.util.Scanner;

public class BubbleSort {

    // Basic Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int steps = 0;
        for (int i = 0; i < n - 1; i++) {           // Number of passes
            for (int j = 0; j < n - 1 - i; j++) {   // Compare adjacent elements
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

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
        int n = sc.nextInt();     // Size of array
        int[] arr = new int[n];

        System.out.print("Enter the elements in the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();  // Input elements
        }

        System.out.print("Before Sorting: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        bubbleSort(arr);

        System.out.print("After Sorting: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        sc.close();
    }
}