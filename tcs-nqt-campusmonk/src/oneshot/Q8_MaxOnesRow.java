package oneshot;
/*

A parking lot in a mall has R x C number of parking spaces. Each parking space will either be empty (0) or full (1). The status (0/1) of a parking space is represented as the element of the matrix. The task is to find the index of the row (R) in the parking lot that has the most of the parking spaces full (1).

Note:
R x C - Size of the matrix
Elements of the matrix M should be only 0 or 1.

Example 1:

Input:
3   -> Value of R (row)
3   -> Value of C (column)
0 1 0
1 1 0
1 1 1

Output:
3   -> Row 3 has maximum number of 1's

*/
import java.util.*;

public class Q8_MaxOnesRow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int R = sc.nextInt();
        int C = sc.nextInt();

        int[][] matrix = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        sc.close();

        int maxCount = 0;
        int rowIndex = -1;

        for (int i = 0; i < R; i++) {
            int count = 0;

            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxCount = count;
                rowIndex = i;
            }
        }

        System.out.println(rowIndex == -1 ? -1 : rowIndex + 1);
    }
}