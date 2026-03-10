package tcspyq;

import java.util.HashMap;
import java.util.Scanner;

public class Q2_CountSundays_HashMap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String startDay = sc.next().toLowerCase();
        int n = sc.nextInt();
        sc.close();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("sun",0);
        map.put("mon",1);
        map.put("tue",2);
        map.put("wed",3);
        map.put("thu",4);
        map.put("fri",5);
        map.put("sat",6);

        int startIndex = map.get(startDay.substring(0,3));
        int distanceToSunday = (7 - startIndex) % 7;
        int countSunday = 0;

        if (distanceToSunday < n) {
            countSunday = 1;
            int remainingDays = n - distanceToSunday - 1;
            countSunday += remainingDays / 7;
        }
        System.out.println(countSunday);
    }
}
