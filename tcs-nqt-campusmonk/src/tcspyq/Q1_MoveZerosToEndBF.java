package tcspyq;
import java.util.Scanner;

public class Q1_MoveZerosToEndBF {

    public static int[] moveZeroes(int[] arr) {
        if (arr == null) return null;
        int n = arr.length;
        int[] result = new int[n];
        int index = 0;
        for (int value : arr) {
            if (value != 0) {
                result[index++] = value;
            }
        }
        return result;
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

        int[] output = moveZeroes(arr);

        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i]);
            if (i < output.length - 1) System.out.print(" ");
        }

        System.out.println();
        sc.close();
    }
}
