package Sorting;
import java.util.Scanner;

public class MergeSort {

    static int steps = 0;

    // Merge Sort method
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {

            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);      // Sort left half
            mergeSort(arr, mid + 1, right); // Sort right half

            merge(arr, left, mid, right);   // Merge sorted halves

            System.out.print("Merge Step " + steps + ": ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
            steps++;
        }
    }

    // Merge two sorted subarrays
    public static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
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

        mergeSort(arr, 0, n - 1);

        System.out.print("After Sorting: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        sc.close();
    }
}