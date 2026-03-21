package oneshot;
/*
Q4. MONKEYS ON A TREE (LOGIC)
Problem Statement –
There are total n number of Monkeys sitting on the branches of a huge Tree. As travelers offer Bananas and Peanuts, the Monkeys jump down the Tree. If every Monkey can eat k Bananas and j Peanuts. If total m number of Bananas and p number of Peanuts are offered by travelers, calculate how many Monkeys remain on the Tree after some of them jumped down to eat.

At a time one Monkeys gets down and finishes eating and go to the other side of the road. The Monkey who climbed down does not climb up again after eating until the other Monkeys finish eating. Monkey can either eat k Bananas or j Peanuts. If for last Monkey there are less than k Bananas left on the ground or less than j Peanuts left on the ground, only that Monkey can eat Bananas(<k) along with the Peanuts(<j).

Write code to take inputs as n, m, p, k, j and return the number of Monkeys left on the Tree.
Where, n= Total no of Monkeys
k= Number of eatable Bananas by Single Monkey
j= Number of eatable Peanuts by Single Monkey
m= Total number of Bananas
p= Total number of Peanuts
Remember that the Monkeys always eat Bananas and Peanuts, so there is no possibility of k and j having a value zero.

*/
import java.util.Scanner;

public class Q4_MonkeysOnTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int j = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();

        int banana_eat = m / k;
        int peanut_eat = p / j;

        int monkeysDown = banana_eat + peanut_eat;

        if ((m % k != 0 || p % j != 0)) {
            monkeysDown++;
        }

        monkeysDown = Math.min(n, monkeysDown);

        int left = n - monkeysDown;

        System.out.println(left);
    }
}