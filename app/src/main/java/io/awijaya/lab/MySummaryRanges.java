package io.awijaya.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/summary-ranges/description/
 * level: easy
 */
public class MySummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) return List.of();
        if (nums.length == 1) return List.of(String.valueOf(nums[0]));

        List<String> result = new ArrayList<>();
        boolean isContinuous = false;
        int prev = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                isContinuous = true;
                if (i == nums.length - 1) {
                    result.add(prev + "->" + nums[i]);
                }
            } else {
                if (isContinuous) {
                    result.add(prev + "->" + nums[i - 1]);
                    isContinuous = false;

                    if (i == nums.length - 1) {
                        result.add(String.valueOf(nums[i]));
                    }
                } else {
                    result.add(String.valueOf(prev));

                    if (i == nums.length - 1) {
                        result.add(String.valueOf(nums[i]));
                    }
                }
                prev = nums[i];
            }
        }

        return result;
    }

    /**
     * Approach 1: Two Pointers Use two pointers to track the start and end of each range.
     *
     * Time Complexity: O(n) Space Complexity: O(1) excluding output
     */
    public List<String> summaryRangesTwoPointers(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int start = nums[0];
        int end = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                end = nums[i];
            } else {
                // Add current range
                if (start == end) {
                    result.add(String.valueOf(start));
                } else {
                    result.add(start + "->" + end);
                }
                // Start new range
                start = nums[i];
                end = nums[i];
            }
        }

        // Add the last range
        if (start == end) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "->" + end);
        }

        return result;
    }

    public static void main(String[] args) {
        MySummaryRanges mySummaryRanges = new MySummaryRanges();

        int[] test = {0, 1, 2, 4, 5, 7};
        // "0->2","4->5","7"
        for (String result : mySummaryRanges.summaryRanges(test)) {
            System.out.println(result);
        }

        int[] test2 = {0, 2, 3, 4, 6, 8, 9};
        // "0","2->4","6","8->9"
        for (String result : mySummaryRanges.summaryRanges(test2)) {
            System.out.println(result);
        }

        int[] test3 = {1, 3};
        // "1","3"
        for (String result : mySummaryRanges.summaryRanges(test3)) {
            System.out.println(result);
        }
    }
}
