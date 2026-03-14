package tcspyq;
import java.util.Scanner;

public class Q6_FindOddOccurring_BF {

    public static int findOddOccurring(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i += 2) {
            if (arr[i] != arr[i + 1]) {
                return arr[i];
            }
        }

        return arr[n - 1];   // if odd element is at last
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