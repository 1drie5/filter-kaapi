/*
Question:
You are given a string "number" representing a positive integer
and a character "digit".

Return the resulting string after removing exactly one occurrence
of "digit" from "number" such that the value of the resulting
string is maximized.

Example:
Input:
1231
1

Output:
231
*/

import java.util.Scanner;
public class Q1_RemoveDigitToMaximize {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        char d = sc.nextLine().charAt(0);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == d) {
                String temp = n.substring(0, i) + n.substring(i + 1);
                int val = Integer.parseInt(temp);
                if (val > max) {
                    max = val;
                }
            }
        }
        System.out.print(String.valueOf(max));
        sc.close();
    }
}