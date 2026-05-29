/*
You have an array you need to perform the given task:
1. If the array length is greater than 1 then take any two numbers which are X != Y and remove X and Y
2. Else take X and remove X
Count the minimum number of step to remove all element from list.

I/P : 1 2
O/P: 1

I/P : 2 2
O/P: 2

I/P: 2 2 3 3 1
O/P: 3 (2,3)(2,3)(1)
*/

.javaimport java.util.*;


public class Q4_MinimumRemovalSteps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }
        int ans = 0;
        Collections.sort(a);
        while (!a.isEmpty()) {
            if (a.get(0).equals(a.get(a.size() - 1))) {
                a.remove(a.size() - 1);
                if (!a.isEmpty()) {
                    a.remove(0);
                }
            } else {
                a.remove(0);
                ans++;
            }
        }
        System.out.println(ans);
    }
}
