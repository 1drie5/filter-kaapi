package Sorting;
import java.util.Scanner;

public class QuickSort {

    static int steps = 0;

    // Quick Sort method
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);  // Partition index

            System.out.print("Partition step " + steps + ": ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
            steps++;

            quickSort(arr, low, pi - 1);   // Sort left half
            quickSort(arr, pi + 1, high);  // Sort right half
        }
    }

    // Partition method
    public static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];  // Taking last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap pivot into correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
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

        quickSort(arr, 0, n - 1);

        System.out.print("After Sorting: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        sc.close();
    }
}