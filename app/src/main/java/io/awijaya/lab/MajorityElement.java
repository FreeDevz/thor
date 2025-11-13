package io.awijaya.lab;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/majority-element/
 * level: easy
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];

        int mid = nums.length / 2;
        Arrays.sort(nums);
        int currentCount = 0;
        int currentElement = 0;

        for (int i : nums) {
            if (currentCount == 0) {
                currentCount++;
                currentElement = i;
                continue;
            }
            if (currentElement != i) {
                currentElement = i;
                currentCount = 0;
                currentCount++;
            } else {
                currentCount++;
            }

            if (currentCount > mid) {
                return i;
            }
        }

        return 0;
    }

    /**
     * Approach 1: Boyer-Moore Voting Algorithm (Recommended)
     * <p>
     * This is the optimal solution. The key insight is that the majority element will always
     * "survive" the voting process because it appears more than n/2 times.
     * <p>
     * Time: O(n) Space: O(1)
     */
    public int majorityElementByCursor(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        // First pass: find the candidate
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Since the problem guarantees a majority element exists,
        // we don't need a second pass to verify
        return candidate;
    }

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();

        int[] test = {3, 2, 3};
        System.out.println(majorityElement.majorityElement(test)); // 3

        int[] test2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement.majorityElement(test2)); // 2

        int[] test3 = {2, 2, 1, 1, 3, 3, 3, 4, 4};
        System.out.println(majorityElement.majorityElementByCursor(test3)); // 3
    }
}
