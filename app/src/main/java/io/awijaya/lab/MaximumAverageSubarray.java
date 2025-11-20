package io.awijaya.lab;

/**
 * https://leetcode.com/problems/maximum-average-subarray-i/description/
 * level: easy
 */
public class MaximumAverageSubarray {
    /**
     * Approach 1: Sliding Window (Recommended)
     *
     * Time Complexity: O(n) where n is the length of nums Space Complexity: O(1) - only using
     * constant extra space
     *
     * Strategy: Use sliding window technique to efficiently calculate the sum of each subarray of
     * length k. Start with the first window, then slide the window by removing the leftmost element
     * and adding the rightmost element.
     */
    public double findMaxAverage(int[] nums, int k) {
        // Calculate sum of first k elements
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        int maxSum = windowSum;

        // Slide the window across the array
        for (int i = k; i < nums.length; i++) {
            // Remove the leftmost element and add the rightmost element
            windowSum = windowSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        MaximumAverageSubarray maximumAverageSubarray = new MaximumAverageSubarray();

        int[] nums = {1, 12, -5, -6, 50, 3};
        System.out.println(maximumAverageSubarray.findMaxAverage(nums, 4)); // 12.75000

//        int[] nums2 = {5};
//        System.out.println(maximumAverageSubarray.findMaxAverage(nums2, 1)); // 5
//
//        int[] nums3 = {0, 1, 1, 3, 3};
//        System.out.println(maximumAverageSubarray.findMaxAverage(nums3, 4)); // 2.00000
//
//        int[] nums4 = {-1};
//        System.out.println(maximumAverageSubarray.findMaxAverage(nums4, 1)); // -1
//
//        int[] nums5 = {8860, -853, 6534, 4477, -45891};
//        System.out.println(maximumAverageSubarray.findMaxAverage(nums5, 5)); // -5374.6
    }
}
