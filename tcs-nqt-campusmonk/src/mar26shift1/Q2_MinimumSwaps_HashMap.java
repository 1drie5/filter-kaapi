package mar26shift1;
import java.util.*;

public class Q2_MinimumSwaps_HashMap {

    public static int minSwaps(int[] S, int[] X, int n) {

        // Step 1: Map each value in X to its index
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(X[i], i);
        }

        // Step 2: Build permutation array P
        int[] P = new int[n];
        for (int i = 0; i < n; i++) {
            P[i] = map.get(S[i]);
        }

        // Step 3: Count inversions in P
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

        int n = sc.nextInt();

        int[] S = new int[n];
        int[] X = new int[n];

        for (int i = 0; i < n; i++) S[i] = sc.nextInt();
        for (int i = 0; i < n; i++) X[i] = sc.nextInt();

        System.out.println(minSwaps(S, X, n));
    }
}