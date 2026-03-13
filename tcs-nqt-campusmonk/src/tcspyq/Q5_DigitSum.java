package tcspyq;
import java.util.*;

public class Q5_DigitSum {
    public static void main(String[] args){
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

        while(sum > 9){
            int ans = 0;
            while(sum > 0){
                ans += (sum % 10);
                sum = sum / 10;
            }
            sum = ans;
        }

        System.out.println(sum);
    }
}