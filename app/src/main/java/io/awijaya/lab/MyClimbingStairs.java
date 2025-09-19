package io.awijaya.lab;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * level: easy
 */
public class MyClimbingStairs {
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1; // ways to reach step 1
        int prev1 = 2; // ways to reach step 2

        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int test = 2;
        System.out.println(climbStairs(test));

        int test2 = 3;
        System.out.println(climbStairs(test2));

        int test3 = 4;
        System.out.println(climbStairs(test3)); // 5

        int test4 = 5;
        System.out.println(climbStairs(test4)); // 8
    }
}
