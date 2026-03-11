package tcspyq;
import java.util.Scanner;

public class Q3_CountGreaterThanPrevious_BF{
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
        for (int i = 0; i < N; i++) {
            boolean greaterThanAllPrior = true;
            for (int j = 0; j < i; j++) {
                if (arr[i] <= arr[j]) {
                    greaterThanAllPrior = false;
                    break;
                }
            }
            if (greaterThanAllPrior) count++;
        }

        System.out.println(count);
        sc.close();
    }
}