package tcspyq;
import java.util.Scanner;

public class Q7_Handshakes_SingleLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            long n = sc.nextLong(); // use long for safety even in brute force count

            long count = 0;
            for (long i = 1; i < n; i++) {
                count += i; // person i shakes hands with all previous i people
            }
            System.out.println(count);
        }
        sc.close();
    }
}