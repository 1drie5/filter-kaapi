/*
You are given an array ARR which has N integers. You want to construct a new array RES using ARR by following the below algorithm:
1. Initially, RES is empty
2. Start at any index of ARR
3. Choose a direction (left or right) and iterate over the elements of ARR starting from the chosen index in the chosen direction
4. Add each iteration element to the end of the RES

Additionally, it is given that the array ARR is cyclic. This means that after the last element you will iterate to the first one and vice versa.
The value of RES is the sum of the bitwise XOR value of all the prefixes of it. That means that the value of RES can be defined as follows:
value(RES) = RES[0] + (RES[0]^RES[1]) + (RES[0]^RES[1]^RES[2]) + ...........+ (RES[0]^RES[1]^RES[2]......^RES[N-1])

Goal: Find the maximum possible value(RES) over all possible starting indices and directions.

Example:
I/P: N= 10, ARR = [7 8 5 5 9 2 2 0 1 6]
O/P: 99
considering RES = [5 8 7 6 1 0 2 2 9 5]
value(RES) = 5 + (5^8) + (5^8^7)+........
value(RES) = 5 + 13 + 10+ 12+ 13+ 13+ 15+ 13+4+1 = 99
*/
import java.util.Scanner;

public class Q3_MaximizeCyclicPrefixXOR {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the array
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        // Read the array elements
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        long maxResValue = -1;

        // Evaluate all possible starting positions
        for (int i = 0; i < n; i++) {

            // 1. Traverse Right (Forward)
            long currentSumRight = 0;
            long currentXorRight = 0;
            for (int step = 0; step < n; step++) {
                int idx = (i + step) % n;
                currentXorRight ^= arr[idx];
                currentSumRight += currentXorRight;
            }
            if (currentSumRight > maxResValue) {
                maxResValue = currentSumRight;
            }

            // 2. Traverse Left (Backward)
            long currentSumLeft = 0;
            long currentXorLeft = 0;
            for (int step = 0; step < n; step++) {
                // Add 'n' before modulo to handle negative indices safely
                int idx = (i - step + n) % n;
                currentXorLeft ^= arr[idx];
                currentSumLeft += currentXorLeft;
            }
            if (currentSumLeft > maxResValue) {
                maxResValue = currentSumLeft;
            }
        }

        System.out.println(maxResValue);
        scanner.close();
    }
}
