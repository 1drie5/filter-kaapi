/*
While playing an RPG game, you were assigned to complete one of the hardest quests in the game.
There are n monsters that you need to defeat. Each monster i is described by two integers:
power[i] – the minimum experience points required to defeat the monster.
bonus[i] – the experience points gained after defeating the monster.

To defeat a monster, your current experience must be at least power[i]. If your experience is less
than power[i], you lose immediately and cannot defeat that monster. After defeating monster i,
your experience increases by bonus[i]. You may choose to fight the monsters in any order.

Determine the maximum number of monsters that can be defeated.

Example:
Input:
2
123
78
130
10
0

Output:
2
*/

import java.util.*;

public class Q5_MaxMonsters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int lev = sc.nextInt();

        int[] p = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        List<int[]> a = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            a.add(new int[]{p[i], b[i]});
        }

        a.sort(Comparator.comparingInt(x -> x[0]));

        int ans = 0;

        for (int[] pair : a) {
            int power = pair[0];
            int bonus = pair[1];

            if (power > lev) {
                break;
            }

            lev += bonus;
            ans++;
        }

        System.out.println(ans);

        sc.close();
    }
}