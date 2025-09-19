package io.awijaya.lab2;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static void main(String[] args) {
        int[] test = {5, 3, 4, 2, 1};
        Arrays.sort(test);

        for(int i : test) {
            System.out.println(i);
        }

        List<Integer> testList = new ArrayList<>();
        testList.add(3);
        testList.add(1);
        testList.add(2);
        testList.add(4);
        testList.add(5);

//        testList.sort( (a, b) -> Integer.compare(a, b));
        testList.sort(Integer::compare);
        testList.sort( Comparator.comparingInt(value -> value));
        for(Integer i: testList) {
            System.out.println(i);
        }

        List<String> wordsList = new ArrayList<>();
        wordsList.add("dog");
        wordsList.add("chicken");
        wordsList.add("air");
        wordsList.add("buy");
        wordsList.add("egg");
        wordsList.add("Great");
        wordsList.add("Food");
        wordsList.sort(String::compareTo);
        for(String i: wordsList) {
            System.out.println(i);
        }
    }
}
