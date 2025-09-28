package io.awijaya.lab;

/**
 * https://leetcode.com/problems/missing-number/description/
 * level: easy
 */
public class MyMissingNumber {
    public int missingNumber(int[] nums) {
        int totalIdeal = 0;
        int totalActual = 0;

        for (int i = 0; i < nums.length; i++) {
            totalIdeal += i;
            totalActual += nums[i];

            if (i == nums.length - 1) {
                totalIdeal += (i + 1);
            }
        }

        return totalIdeal - totalActual;
    }
}
