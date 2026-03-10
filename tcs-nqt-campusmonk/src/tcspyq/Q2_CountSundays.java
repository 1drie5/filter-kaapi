package tcspyq;
import java.util.*;

public class Q2_CountSundays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String startDay = sc.next().toLowerCase();
        int n = sc.nextInt();
        sc.close();
        
        String[] days = {"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

        int startIndex = -1;

        for (int i = 0; i < 7; i++) {
            if (days[i].equals(startDay)) {
                startIndex = i;
                break;
            }
        }

        int distanceToSunday = (7 - startIndex) % 7;
        int countSunday = 0;

        if (distanceToSunday < n) {
            countSunday = 1;  // First Sunday found

            int remainingDays = n - distanceToSunday - 1;
            countSunday += remainingDays / 7;
        }

        System.out.println(countSunday);
    }
}