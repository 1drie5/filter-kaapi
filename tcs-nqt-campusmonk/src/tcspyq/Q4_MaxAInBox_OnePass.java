package tcspyq;
import java.util.*;

public class Q4_MaxAInBox_OnePass {
    public static int maxAquaOnePass(String s, int l) {
        if (l <= 0) return 0;
        int n = s.length();
        if (n == 0) return 0;

        int maxSoFar = 0;
        int countInBox = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') countInBox++;

            // If end of a box (every l chars) or end of string, update and reset
            if ((i + 1) % l == 0 || i == n - 1) {
                if (countInBox > maxSoFar) maxSoFar = countInBox;
                countInBox = 0;
            }
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int l = sc.nextInt();
        sc.close();

        System.out.println(maxAquaOnePass(s, l));
    }
}