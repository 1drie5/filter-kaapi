package tcspyq;
import java.util.Scanner;

public class Q6_FindOddOccurring_XOR {

    public static int findOddOccurring(int[] arr) {
        int xor = 0;
        for (int num : arr) {
            xor ^= num;   // xor = xor ^ num
        }
        return xor;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println(0);
            sc.close();
            return;
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        sc.close();

        int result = findOddOccurring(arr);
        System.out.println(result);
    }
}