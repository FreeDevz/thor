package io.awijaya.lab3;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * level: easy
 */
public class TwoSum {
    /**
     * Approach 1: Hash Map - One Pass (Recommended)
     *
     * This is the optimal solution for the Two Sum problem.
     *
     * Algorithm: 1. Create a hash map to store each number and its index 2. For each number,
     * calculate its complement (target - current number) 3. Check if the complement exists in the
     * hash map 4. If found, return the indices; otherwise, add current number to map
     *
     * Key insights: - We only need to traverse the array once - Hash map provides O(1) lookup time
     * - We check for complement before adding current element to avoid using same element twice
     *
     * Time Complexity: O(n) where n is the length of the array Space Complexity: O(n) for the hash
     * map
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check if complement exists in the map
            if (numToIndex.containsKey(complement)) {
                return new int[] {numToIndex.get(complement), i};
            }

            // Add current number and its index to the map
            numToIndex.put(nums[i], i);
        }

        // No solution found (shouldn't happen based on problem constraints)
        return new int[0];
    }
}
