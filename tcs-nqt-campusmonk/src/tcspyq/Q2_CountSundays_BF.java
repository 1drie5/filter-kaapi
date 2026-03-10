package tcspyq;
import java.util.*;

public class Q2_CountSundays_BF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String startDay = sc.next().toLowerCase();  // e.g., mon
        int n = sc.nextInt();                       // number of days
        sc.close();
        String[] days = {"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

        int startIndex = -1;

        // Find index of starting day
        for (int i = 0; i < 7; i++) {
            if (days[i].equals(startDay)) {
                startIndex = i;
                break;
            }
        }

        int countSunday = 0;

        // Traverse n days
        for (int i = 0; i < n; i++) {
            int currentDayIndex = (startIndex + i) % 7;

            if (days[currentDayIndex].equals("sun")) {
                countSunday++;
            }
        }

        System.out.println(countSunday);
    }
}