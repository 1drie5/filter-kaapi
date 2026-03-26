package mar26shift1;
import java.util.*;
/*

Q1: Maximum Product Subset
Given an array of integers with negative, zero and positive elements, find the maximum product that can be obtained by multiplying some or all of the elements. You can choose any subset of the array.

Input: -1, 2, 3, 4
Output: 24
Note: Comma separated input

*/
public class Q1_MaximumProductSubset {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(",");
        int n = input.length;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        // If only one element and it's negative
        if (n == 1) {
            System.out.println(arr[0]);
            return;
        }
        long product = 1;
        int countNeg = 0;
        int zeroCount = 0;
        int maxNeg = Integer.MIN_VALUE;

        for (int x : arr) {
            if (x == 0) {
                zeroCount++;
                continue;
            }

            if (x < 0) {
                countNeg++;
                maxNeg = Math.max(maxNeg, x); // closest to zero negative
            }

            product *= x;
        }

        // If all elements are zero
        if (zeroCount == n) {
            System.out.println(0);
            return;
        }

        // If only one negative and rest are zeros
        if (countNeg == 1 && zeroCount + countNeg == n) {
            System.out.println(0);
            return;
        }

        // If odd negatives, remove one
        if (countNeg % 2 == 1) {
            product /= maxNeg;
        }

        System.out.println(product);
        sc.close();
    }
}