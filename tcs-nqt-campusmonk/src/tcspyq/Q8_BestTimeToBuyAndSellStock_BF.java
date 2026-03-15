package tcspyq;
import java.util.Scanner;

public class Q8_BestTimeToBuyAndSellStock_BF {
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        int maxProfit = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) maxProfit = profit;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] prices = new int[Math.max(0, n)];
        for (int i = 0; i < n; i++) prices[i] = sc.nextInt();
        sc.close();
        System.out.println(maxProfit(prices));
    }
}