import java.util.Scanner;

public class Q2_MaximumInGeneratedArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 0) {
            System.out.print(0);
            scanner.close();
            return;
        }

        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 1; i <= n / 2; i++) {
            arr[i * 2] = arr[i];

            if ((i * 2) + 1 <= n) {
                arr[(i * 2) + 1] = arr[i] + arr[i + 1];
            }
        }

        int max = arr[0];
        for (int i = 0; i <= n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println(max);
        scanner.close();
    }
}