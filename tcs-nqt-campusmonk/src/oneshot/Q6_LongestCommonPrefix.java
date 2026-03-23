package oneshot;
/*

Q6. Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:
Input: strs = ["flower", "flow", "flight"]
Output: "fl"

Example 2:
Input: strs = ["dog", "racecar", "car"]
Output: ""

Explanation:
There is no common prefix among the input strings.

*/
import java.util.*;

public class Q6_LongestCommonPrefix  {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        Arrays.sort(strs);

        String first = strs[0];
        String last = strs[strs.length - 1];

        int i = 0;
        while (i < first.length() && i < last.length() &&
                first.charAt(i) == last.charAt(i)) {
            i++;
        }

        return first.substring(0, i);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = sc.nextLine();
        }
        sc.close();
        System.out.println(longestCommonPrefix(strs));
    }
}