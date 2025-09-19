package io.awijaya.lab;

/**
 * https://leetcode.com/problems/counting-bits/description/
 * level: easy
 */
public class MyCountingBits {
    /**
     * Approach 1: Dynamic Programming with Right Shift
     *
     * Key Insight: The number of 1's in the binary representation of a number i is equal to the
     * number of 1's in i/2 (i right-shifted by 1) plus the least significant bit of i.
     *
     * Formula: ans[i] = ans[i >> 1] + (i & 1)
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * @param n the upper bound (inclusive)
     * @return array containing count of 1's for each number from 0 to n
     */
    public int[] countBitsDP(int n) {
        int[] ans = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        MyCountingBits myCountingBits = new MyCountingBits();
        for(int i: myCountingBits.countBitsDP(5)) {
            System.out.println(i);
        }
    }
}
