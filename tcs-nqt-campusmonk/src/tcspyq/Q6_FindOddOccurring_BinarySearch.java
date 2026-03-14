package tcspyq;
import java.util.Scanner;

public class Q6_FindOddOccurring_BinarySearch {

    public static int findOddOccurring(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid % 2 == 1) mid--; // ensure mid is even
            if (arr[mid] == arr[mid + 1]) start = mid + 2;
            else end = mid;
        }
        return arr[start];
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