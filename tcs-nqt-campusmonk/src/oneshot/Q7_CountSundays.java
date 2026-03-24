package oneshot;
/*

Jack is always excited about Sunday — it is his favourite day, when he gets to play
all day and go cycling with his friends. Every time a month starts, he counts the
number of Sundays he will get to enjoy. Considering the month can start with any
day (Sunday, Monday, and so on), count the number of Sundays Jack will get within
N number of days.
Example:
Input:
mon String denoting the start of the month
13 Integer denoting the number of days from the start of the month
Output:
1 Number of Sundays within 13 days
Explanation:
The month starts on Monday (mon). Counting forward: Mon(1), Tue(2),
Wed(3), Thu(4), Fri(5), Sat(6), Sun(7), Mon(8), Tue(9), Wed(10), Thu(11),
Fri(12), Sat(13). The first Sunday falls on day 7, and the next Sunday would
fall on day 14 — which exceeds the 13-day limit. Total: 1 Sunday.

*/
import java.util.*;

public class Q7_CountSundays {
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