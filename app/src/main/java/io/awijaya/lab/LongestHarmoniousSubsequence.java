package io.awijaya.lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-harmonious-subsequence/description/
 * level: easy
 */
public class LongestHarmoniousSubsequence {

    /**
     * Approach 1: HashMap with Frequency Counting (Recommended)
     *
     * Time Complexity: O(n) where n is the length of nums Space Complexity: O(n) for the frequency
     * map
     *
     * Strategy: Count frequency of each number, then for each number x, check if x+1 exists. If so,
     * the harmonious subsequence length is count[x] + count[x+1].
     */
    public int findLHS(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        // Count frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int maxLength = 0;
        // For each number, check if its consecutive number exists
        for (int num : frequencyMap.keySet()) {
            if (frequencyMap.containsKey(num + 1)) {
                maxLength = Math.max(maxLength, frequencyMap.get(num) + frequencyMap.get(num + 1));
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestHarmoniousSubsequence longestHarmoniousSubsequence = new LongestHarmoniousSubsequence();

        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println(longestHarmoniousSubsequence.findLHS(nums)); // 5

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(longestHarmoniousSubsequence.findLHS(nums2)); // 2

        int[] nums3 = {1, 1, 1, 1};
        System.out.println(longestHarmoniousSubsequence.findLHS(nums3)); // 0

        int[] nums4 = {1, 2, 3, 4, 4};
        System.out.println(longestHarmoniousSubsequence.findLHS(nums4)); // 3
    }
}
