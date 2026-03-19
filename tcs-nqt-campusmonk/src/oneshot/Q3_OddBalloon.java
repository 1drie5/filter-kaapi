package oneshot;
/*
Q3. ODD OCCURRING BALLOON COLOR
Problem Statement –
At a fun fair, a street vendor is selling different colours of balloons (B[]). The task is to find the colour (odd) of the balloon which is present odd number of times in the bunch of balloons.

Note: If there is more than one colour which is odd in number, then the first colour in the array which is present odd number of times is displayed. The colours of the balloons can all be either upper case or lower case in the array. If all the inputs are even in number, display the message "All are even".

Example 1:
Input:
7 -> Value of N
[r,g,b,b,g,y,y] -> B[] Elements B[0] to B[N-1], where each input element is separated by new line.
Output:
r -> [r,g,b,b,g,y,y] -> "r" colour balloon is present odd number of times in the bunch.

Explanation:
From the input array above: r: 1 balloon g: 2 balloons b: 2 balloons y: 2 balloons Hence , r is only the balloon which is odd in number.

*/

import java.util.HashMap;
import java.util.Scanner;

public class Q3_OddBalloon {
    // Changed return type to String to handle the "All are even" message
    public static String fun(char[] arr, int n) {
        HashMap<Character, Integer> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char c = Character.toLowerCase(arr[i]);
            mp.put(c, mp.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            char c = Character.toLowerCase(arr[i]);
            if (mp.get(c) % 2 != 0) {
                return String.valueOf(c);
            }
        }

        return "All are even";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n <= 0) {
                System.out.println(0);
                sc.close();
                return;
            }
            char[] arr = new char[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.next().charAt(0);
            }

            System.out.println(fun(arr, n));
        }
        sc.close();
    }
}
