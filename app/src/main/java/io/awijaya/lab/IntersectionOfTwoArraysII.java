package io.awijaya.lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * level: easy
 */
public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        // Use the smaller array for frequency counting to minimize space
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        // Count frequencies in the smaller array
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums1) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Build result array by checking frequencies in nums2
        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            if (frequencyMap.containsKey(num) && frequencyMap.get(num) > 0) {
                result.add(num);
                frequencyMap.put(num, frequencyMap.get(num) - 1);
            }
        }

        // Convert list to array
        int[] resultArray = new int[result.size()];
        for (int idx = 0; idx < result.size(); idx++) {
            resultArray[idx] = result.get(idx);
        }

        return resultArray;
    }
}
