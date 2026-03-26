package mar26shift1;
import java.util.*;
/*

Q2: Minimum Swaps to Transform Array

Given two arrays of size n, the first array represents the current arrangement and the second array represents the desired arrangement. In one operation, you can swap only adjacent elements.

Find the minimum number of swaps required to transform the first array into the second array.

Input:
5
20 40 29 10 98
10 98 40 20 29

Output:
7

Step-by-step swaps:

Start:
20 40 29 10 98

Swap 29 and 10
20 40 10 29 98   (1)

Swap 40 and 10
20 10 40 29 98   (2)

Swap 20 and 10
10 20 40 29 98   (3)

Swap 29 and 98
10 20 40 98 29   (4)

Swap 40 and 98
10 20 98 40 29   (5)

Swap 20 and 98
10 98 20 40 29   (6)

Swap 20 and 40
10 98 40 20 29   (7)

Final array:
10 98 40 20 29


*/
public class Q2_MinimumSwaps {

    public static int minSwaps(int[] A, int[] B, int n) {

        // Step 1: Build permutation P without HashMap
        int[] P = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i] == B[j]) {
                    P[i] = j;
                    break;
                }
            }
        }

        // Step 2: Count inversions in P
        // An inversion = pair (i,j) where i < j but P[i] > P[j]
        // Each inversion = one adjacent swap needed
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (P[i] > P[j]) {
                    swaps++;
                }
            }
        }

        return swaps;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int[] A = new int[n];
        int[] B = new int[n];

        System.out.println("Enter current array (space-separated): ");
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();

        System.out.println("Enter desired array (space-separated): ");
        for (int i = 0; i < n; i++) B[i] = sc.nextInt();

        System.out.println("Minimum Swaps: " + minSwaps(A, B, n));
    }
}