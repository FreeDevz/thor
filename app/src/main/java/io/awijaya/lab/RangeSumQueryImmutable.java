package io.awijaya.lab;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 * level: easy
 */
public class RangeSumQueryImmutable {
    int[] prefixSum;

    /**
     * Approach 1: Prefix Sum Array (Optimal) Precompute prefix sums to answer range queries in O(1)
     * time.
     *
     * Time Complexity: O(n) constructor, O(1) sumRange Space Complexity: O(n)
     */
    public RangeSumQueryImmutable(int[] nums) {
        if (nums == null || nums.length == 0) {
            prefixSum = new int[1];
            return;
        }

        prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left < 0 || right >= prefixSum.length - 1 || left > right) {
            throw new IllegalArgumentException("Invalid range");
        }
        return prefixSum[right + 1] - prefixSum[left];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        RangeSumQueryImmutable numArray = new RangeSumQueryImmutable(nums);
        System.out.println(numArray.sumRange(0, 2)); // 1
        System.out.println(numArray.sumRange(2, 5)); // -1
        System.out.println(numArray.sumRange(0, 5)); // -3
    }
}
