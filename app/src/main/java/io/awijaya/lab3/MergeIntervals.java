package io.awijaya.lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/description/
 * level: medium
 */
public class MergeIntervals {
    /**
     * Approach 1: Sort and Merge (Recommended)
     * <p>
     * Sorts intervals by start time, then merges overlapping intervals by comparing the end time of
     * the last merged interval with the start time of the current interval.
     * <p>
     * This is the most straightforward and efficient approach for LeetCode problems.
     * <p>
     * Time: O(n log n) Space: O(1) excluding output array
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(intervals, Comparator.comparingInt(values -> values[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] last = merged.getLast();

            if (current[0] <= last[1]) {
                last[1] = Math.max(last[1], current[1]);
            } else {
                merged.add(current);
            }
        }

        return merged.toArray(new int[merged.size()][2]);
    }

    public static void main(String[] args) {
        int[][] test1 = new int[2][3];

        System.out.println("rows: " + test1.length);
        System.out.println("columns: " + test1[0].length);
    }
}
