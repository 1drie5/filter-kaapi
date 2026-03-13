package tcspyq;
import java.util.*;

public class Q5_DigitSum_DigitalRoot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int r = sc.nextInt();

        sc.close();
        
        if (r == 0) {
            System.out.println(0);
            return;
        }

        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        sum = sum * r;
        /*
        if (sum == 0)
            System.out.println(0);
        else if (sum % 9 == 0)
            System.out.println(9);
        else
            System.out.println(sum % 9);
        */
        int result = (sum == 0) ? 0 : 1 + (sum - 1) % 9;
        System.out.println(result);

    }
}