package io.awijaya.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/description/
 * level: medium
 */
public class ThreeSum {
    /**
     * Approach 1: Sorting + Two Pointers (Recommended)
     *
     * This is the optimal solution for the 3Sum problem.
     *
     * Algorithm: 1. Sort the array to enable two-pointer technique and easy duplicate skipping 2.
     * For each element nums[i], treat it as the first element of potential triplet 3. Use two
     * pointers (left=i+1, right=n-1) to find pairs that sum to -nums[i] 4. Skip duplicates at all
     * levels to avoid duplicate triplets
     *
     * Key insights: - Sorting enables two-pointer technique and duplicate handling - If nums[i] >
     * 0, we can break early since all remaining elements are positive - Skip duplicate values for
     * first element and when finding valid triplets
     *
     * Time Complexity: O(n²) - O(n log n) for sorting + O(n²) for two-pointer search Space
     * Complexity: O(1) excluding output space
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // Early termination: if first element is positive, no valid triplets exist
            if (nums[i] > 0) {
                break;
            }

            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i]; // We want nums[left] + nums[right] = target

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    // Found a valid triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left pointer
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // Skip duplicates for right pointer
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < target) {
                    left++; // Need larger sum
                } else {
                    right--; // Need smaller sum
                }
            }
        }

        return result;
    }
}
