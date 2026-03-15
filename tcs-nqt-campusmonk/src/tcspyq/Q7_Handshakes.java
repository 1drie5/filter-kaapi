package tcspyq;
import java.util.Scanner;

public class Q7_Handshakes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            long n = sc.nextLong(); // read as long to avoid overflow
            // compute n*(n-1)/2 safely in long
            long result = (n * (n - 1)) / 2;
            System.out.println(result);
        }
        sc.close();
    }
}