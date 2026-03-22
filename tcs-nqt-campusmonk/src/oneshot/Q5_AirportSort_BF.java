package oneshot;
/*
Q10. AIRPORT SECURITY RISK SORTING
Problem Statement –
Airport security officials have confiscated several item of the passengers at the security check point. All the items have been dumped into a huge box (array). Each item possesses a certain amount of risk[0,1,2]. Here, the risk severity of the items represent an array[] of N number of integer values. The task here is to sort the items based on their levels of risk in the array. The risk values range from 0 to 2.

Example:
Input:
7 -> Value of N
[1,0,2,0,1,0,2]-> Element of arr[0] to arr[N-1], while input each element is separated by new line.

Output :
0 0 0 1 1 2 2 -> Element after sorting based on risk severity
*/

import java.util.Scanner;
public class Q5_AirportSort_BF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        sc.close();
        int c0 = 0, c1 = 0, c2 = 0;
        for (int x : arr) { if (x == 0) c0++; else if (x == 1) c1++; else c2++; }
        int i = 0;
        while (c0-- > 0) arr[i++] = 0;
        while (c1-- > 0) arr[i++] = 1;
        while (c2-- > 0) arr[i++] = 2;
        for (int j = 0; j < n; j++) System.out.print(arr[j] + (j < n-1 ? " " : "\n"));
    }
}