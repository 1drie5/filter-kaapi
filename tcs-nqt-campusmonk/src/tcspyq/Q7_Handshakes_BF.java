package tcspyq;
import java.util.Scanner;

public class Q7_Handshakes_BF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            long n = sc.nextLong(); // use long for safety even in brute force count
            long count = 0;
            // simulate every pair (i, j) where 0 <= i < j < n
            for (long i = 0; i < n; i++) {
                for (long j = i + 1; j < n; j++) {
                    count++;
                }
            }
            System.out.println(count);
        }
        sc.close();
    }
}