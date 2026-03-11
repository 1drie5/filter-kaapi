package tcspyq;
import java.util.Scanner;

public class Q3_CountGreaterThanPrevious{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if (N <= 0) {
            System.out.println(0);
            sc.close();
            return;
        }
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();

        int count = 0;
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (arr[i] > maxSoFar) {
                count++;
                maxSoFar = arr[i];
            }
        }

        System.out.println(count);
        sc.close();
    }
}