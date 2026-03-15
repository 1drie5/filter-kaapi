package tcspyq;
import java.util.Scanner;

public class Q8_BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            int p = price - minPrice;
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, p);
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