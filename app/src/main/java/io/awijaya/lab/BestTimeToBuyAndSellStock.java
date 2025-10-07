package io.awijaya.lab;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * level: easy
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;

//        if (prices.length <= 1) return 0;
//        int maxProfit = 0;
//        int currentSmallest = -1;
//        for (int i = 0; i < prices.length - 1; i++) {
//            if (prices[i] < currentSmallest || currentSmallest == -1) {
//                currentSmallest = prices[i];
//                int j = i + 1;
//                while (j < prices.length) {
//                    if (prices[j] < prices[i]) {
//                        break;
//                    }
//                    int profit = prices[j] - prices[i];
//                    if (profit > maxProfit) {
//                        maxProfit = profit;
//                    }
//                    j++;
//                }
//            }
//        }
//
//        return maxProfit;
    }

    public static void main(String[] args) {
//        int[] test = {7, 1, 5, 3, 6, 4};
//        System.out.println(new MyBestTimeToBuyAndSellStock().maxProfit(test)); // 5
//
//        int[] test1 = {7, 6, 4, 3, 1};
//        System.out.println(new MyBestTimeToBuyAndSellStock().maxProfit(test1)); // 0
//
//        int[] test2 = {2, 4, 1};
//        System.out.println(new MyBestTimeToBuyAndSellStock().maxProfit(test2)); // 2
//
//        int[] test3 = {2, 4, 1, 8};
//        System.out.println(new MyBestTimeToBuyAndSellStock().maxProfit(test3)); // 7

        int[] test4 = {3, 2, 6, 5, 0, 3};
        System.out.println(new BestTimeToBuyAndSellStock().maxProfit(test4)); // 4
    }
}
