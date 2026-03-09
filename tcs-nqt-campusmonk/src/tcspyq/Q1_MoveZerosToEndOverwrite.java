package tcspyq;
import java.util.Scanner;


public class Q1_MoveZerosToEndOverwrite {

    public static void moveZeroes(int[] arr) {
        if (arr == null) return;
        int n = arr.length;
        int insertPos = 0; // next position to place a non-zero

        // Move all non-zero elements forward
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                arr[insertPos++] = arr[i];
            }
        }

        // Fill the rest with zeros
        while (insertPos < n) {
            arr[insertPos++] = 0;
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

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(" ");
        }
        System.out.println();

        sc.close();
    }
}