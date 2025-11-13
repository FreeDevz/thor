package io.awijaya.lab3;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 * level: medium
 */
public class TopKFrequentElements {
    /**
     * Default helper that delegates to the bucket sort implementation.
     *
     * @param nums input array
     * @param k    number of most frequent elements to return
     * @return the {@code k} most frequent elements
     */
    public int[] topKFrequent(int[] nums, int k) {
        return topKFrequentBucket(nums, k);
    }

    /**
     * Approach 1: Bucket Sort (Recommended)
     *
     * <p>
     * Counts frequencies and places numbers into buckets indexed by frequency. Iterate buckets from
     * highest to lowest frequency to collect the top {@code k} numbers.
     * </p>
     *
     * @param nums input array
     * @param k    number of most frequent elements to return
     * @return the {@code k} most frequent elements
     */
    public int[] topKFrequentBucket(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = buildFrequencyMap(nums);
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = (List<Integer>[]) new List[nums.length + 1];

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int frequency = entry.getValue();
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(entry.getKey());
        }

        List<Integer> result = new ArrayList<>(k);
        for (int freq = buckets.length - 1; freq >= 0 && result.size() < k; freq--) {
            if (buckets[freq] != null) {
                result.addAll(buckets[freq]);
            }
        }

        return toArray(result, k);
    }

    private int[] toArray(List<Integer> values, int k) {
        int[] result = new int[Math.min(k, values.size())];
        for (int i = 0; i < result.length; i++) {
            result[i] = values.get(i);
        }
        return result;
    }

    /**
     * Helper to build a frequency map.
     */
    public Map<Integer, Integer> buildFrequencyMap(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.merge(num, 1, Integer::sum);
        }
        return frequencyMap;
    }

    public static void main(String[] args) {
        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequentElements.topKFrequent(nums, k);
        for (int i : result) {
            System.out.println("num: " + i);
        }
    }
}
