package io.awijaya.lab;

import java.util.*;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/description/
 * level: easy
 */
public class MyContainsDuplicate2 {

    /**
     * Approach 1: Sliding Window with HashSet - Optimal Solution
     *
     * Time Complexity: O(n) - Single pass through the array Space Complexity: O(min(n, k)) -
     * HashSet stores at most k elements
     *
     * This is the most efficient approach for this problem. We maintain a sliding window of size
     * k+1 using a HashSet. For each element, we check if it already exists in the current window.
     *
     * @param nums The input array
     * @param k The maximum allowed distance between duplicate indices
     * @return true if duplicates exist within distance k, false otherwise
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k < 0) {
            return false;
        }

        // Special case: k = 0 means we can only have duplicates at the same index
        // Since we can't have duplicates at the same index, return false
        if (k == 0) {
            return false;
        }

        Set<Integer> window = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // Remove element that's now outside the window (if window size > k)
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }

            // Check if current element already exists in the window
            if (window.contains(nums[i])) {
                return true;
            }

            // Add current element to the window
            window.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        MyContainsDuplicate2 myContainsDuplicate2 = new MyContainsDuplicate2();

        int[] test = {1, 2, 3, 1};
        System.out.println(myContainsDuplicate2.containsNearbyDuplicate(test, 3)); // true

        int[] test2 = {1, 0, 1, 1};
        System.out.println(myContainsDuplicate2.containsNearbyDuplicate(test2, 1)); // true

        int[] test3 = {1, 2, 3, 1, 2, 3};
        System.out.println(myContainsDuplicate2.containsNearbyDuplicate(test3, 2)); // false
    }
}
