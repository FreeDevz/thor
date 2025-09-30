package io.awijaya.lab;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 * level: easy
 */
public class IntersectionOfTwoArrays {
    /**
     * Approach 1: HashSet (Most Efficient)
     * <p>
     * Time Complexity: O(n + m) where n = nums1.length, m = nums2.length Space Complexity: O(n + m)
     * <p>
     * This is the most efficient approach for this problem.
     */
    public int[] intersectionHashSet(int[] nums1, int[] nums2) {
        // Convert first array to set for O(1) lookup
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        // Use another set to store intersection results (avoids duplicates)
        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                resultSet.add(num);
            }
        }

        // Convert set to array
        int[] result = new int[resultSet.size()];
        int index = 0;
        for (int num : resultSet) {
            result[index++] = num;
        }

        return result;
    }
}
