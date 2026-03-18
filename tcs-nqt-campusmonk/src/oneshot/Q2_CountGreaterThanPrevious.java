package oneshot;

/*

Q2. COUNT ELEMENTS GREATER THAN PRIOR ELEMENTS
Problem Statement –
Given an integer array Arr of size N the task is to find the count of elements whose value is greater than all of its prior elements.

Note : 1st element of the array should be considered in the count of the result. For example, Arr[]={7,4,8,2,9} As 7 is the first element, it will consider in the result. 8 and 9 are also the elements that are greater than all of its previous elements. Since total of 3 elements is present in the array that meets the condition.
Hence, the output = 3.

Example 1:
Input 5 -> Value of N, represents size of Arr
7-> Value of Arr[0]
4-> Value of Arr[1]
8-> Value of Arr[2]
2-> Value of Arr[3]
9-> Value of Arr[4]
Output: 3

*/

import java.util.Scanner;

public class Q2_CountGreaterThanPrevious {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N <= 0) {
            System.out.println(0);
            sc.close();
            return;
        }

        int maxSoFar = sc.nextInt();
        int count = 1;

        for (int i = 1; i < N; i++) {
            int currentElement = sc.nextInt();
            if (currentElement > maxSoFar) {
                count++;
                maxSoFar = currentElement;
            }
        }

        System.out.println(count);
        sc.close();
    }
}
