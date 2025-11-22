package io.awijaya.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The backtracking pattern
 * // 1. Try including the element
 * current.add(nums[index]);
 * count += backtrack(...);  // Explore this path
 *
 * // 2. Restore state (backtrack)
 * current.remove(current.size() - 1);  // Line 69 - critical!
 *
 * // 3. Now current is back to its original state
 * //    for exploring paths that exclude this element
 *
 */
public class NumberOfBeautifulSubsets {
    /**
     * Approach 1: Backtracking (Recommended)
     *
     * This approach explores all possible subsets using backtracking. For each element, we decide
     * whether to include it or not. Before including an element, we check if it would violate the
     * beautiful subset condition (no two elements with absolute difference equal to k).
     *
     * Strategy: 1. Sort the array to make checking easier 2. Use backtracking to explore all
     * subsets 3. Before including an element, check if it conflicts with any existing element 4.
     * Count all valid non-empty subsets
     *
     * Time Complexity: O(2^n * n) where n is the length of nums Space Complexity: O(n) for
     * recursion stack and current subset
     *
     * @param nums array of positive integers
     * @param k positive integer representing the forbidden difference
     * @return number of non-empty beautiful subsets
     */
    public int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        return backtrack(nums, 0, new ArrayList<>(), k) - 1; // Subtract 1 to exclude empty subset
    }

    private int backtrack(int[] nums, int index, List<Integer> current, int k) {
        if (index == nums.length) {
            return 1; // Count this subset (including empty subset)
        }

        // Option 1: Exclude current element
        int count = backtrack(nums, index + 1, current, k);

        // Option 2: Include current element if valid
        boolean canInclude = true;
        for (int num : current) {
            if (Math.abs(nums[index] - num) == k) {
                canInclude = false;
                break;
            }
        }

        if (canInclude) {
            current.add(nums[index]);
            count += backtrack(nums, index + 1, current, k);
            current.remove(current.size() - 1); // Backtrack
        }

        return count;
    }

    public static void main(String[] args) {
        NumberOfBeautifulSubsets numberOfBeautifulSubsets = new NumberOfBeautifulSubsets();
        System.out.println(numberOfBeautifulSubsets.beautifulSubsets(new int[] {2, 4, 6}, 2));
    }
}
