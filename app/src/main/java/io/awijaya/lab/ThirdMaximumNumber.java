package io.awijaya.lab;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/third-maximum-number/description/
 * level: easy
 */
public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        int maxNumber = 0;
        TreeSet<Integer> seen = new TreeSet<>();
        int thirdMaxNumber = 0;

        for (int num : nums) {
            if (!seen.contains(num)) {
                seen.add(num);
            }
            if (num > maxNumber) {
                maxNumber = num;
            }
        }

        if (seen.size() >= 3) {
            thirdMaxNumber = seen.descendingSet().stream().toList().get(2);
        }

        return seen.size() < 3 ? maxNumber : thirdMaxNumber;
    }

    /**
     * Approach 1: Three-Variable Tracking (Most Efficient)
     * <p>
     * Time Complexity: O(n) where n = nums.length Space Complexity: O(1) - only uses three
     * variables
     * <p>
     * This is the most efficient approach for this problem.
     */
    public int thirdMaxThreeVariables(int[] nums) {
        // Use Long.MIN_VALUE to handle Integer.MIN_VALUE cases
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;

        for (int num : nums) {
            // Skip duplicates
            if (num == first || num == second || num == third) {
                continue;
            }

            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num > second) {
                third = second;
                second = num;
            } else if (num > third) {
                third = num;
            }
        }

        // Return third if it exists, otherwise return first (maximum)
        return third == Long.MIN_VALUE ? (int) first : (int) third;
    }
}
