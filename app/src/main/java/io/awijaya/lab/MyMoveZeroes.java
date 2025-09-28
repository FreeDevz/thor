package io.awijaya.lab;

/**
 * https://leetcode.com/problems/move-zeroes/
 * level: easy
 */
public class MyMoveZeroes {
    /**
     * Approach 1: Two Pointers with Swap (Optimal) Use two pointers to move non-zero elements to
     * the front while maintaining order.
     * <p>
     * Time Complexity: O(n) Space Complexity: O(1)
     */
    public void moveZeroesTwoPointers(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int writeIndex = 0; // Position for next non-zero element

        // First pass: move all non-zero elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != writeIndex) {
                    // Swap only if necessary
                    int temp = nums[writeIndex];
                    nums[writeIndex] = nums[i];
                    nums[i] = temp;
                }
                writeIndex++;
            }
        }
    }
}
