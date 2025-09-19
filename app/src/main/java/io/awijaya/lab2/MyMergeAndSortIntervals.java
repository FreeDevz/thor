package io.awijaya.lab2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * https://www.hackerrank.com/contests/software-engineer-prep-kit/challenges/merge-and-sort-intervals/problem?isFullScreen=true
 * level: medium
 * overlapping algorithm
 */
public class MyMergeAndSortIntervals {
    public static List<List<Integer>> mergeHighDefinitionIntervals(List<List<Integer>> intervals) {
        // Write your code here
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        // Sort intervals by start time
        intervals.sort(Comparator.comparingInt(a -> a.get(0)));

        List<List<Integer>> merged = new ArrayList<>();
        merged.add(new ArrayList<>(intervals.get(0)));

        for (int i = 1; i < intervals.size(); i++) {
            List<Integer> current = intervals.get(i);
            List<Integer> last = merged.get(merged.size() - 1);

            // If current interval overlaps with the last merged interval
            if (current.get(0) <= last.get(1)) {
                // Merge by updating the end time
                last.set(1, Math.max(last.get(1), current.get(1)));
            } else {
                // No overlap, add current interval
                merged.add(new ArrayList<>(current));
            }
        }

        return merged;
    }
}
