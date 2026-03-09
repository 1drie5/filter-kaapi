package tcspyq;
import java.util.Scanner;

public class Q1_MoveZerosToEndSwap {

    public static void moveZeroes(int[] arr) {
        int j = -1;

        // Find first zero
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                j = i;
                break;
            }
        }

        // If no zero found, nothing to do
        if (j == -1) return;

        // Swap non-zero elements after first zero
        for (int i = j + 1; i < arr.length; i++) {
            if (arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the array : ");
        int N = sc.nextInt();
        int[] arr = new int[N];

        System.out.print("Enter the elements of the array : ");
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        moveZeroes(arr);

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
        sc.close();
    }
}