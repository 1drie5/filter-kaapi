package oneshot;
// Remember — this only works correctly when there is exactly one odd-occurring element. For multiple odd elements, use the HashMap version.
import java.util.Scanner;

public class Q3_OddBalloon_XOR {
    public static String fun(char[] arr, int n) {
        char xor = 0;
        for (char c : arr) xor ^= Character.toLowerCase(c);
        return xor == 0 ? "All are even" : String.valueOf(xor);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n <= 0) {
                System.out.println(0);
                sc.close();
                return;
            }
            char[] arr = new char[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.next().charAt(0);
            }
            System.out.println(fun(arr, n));
        }
        sc.close();
    }
}