package io.awijaya.lab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-anagram/description/
 * level: easy
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (Character x : s.toCharArray()) {
            frequencyMap.computeIfPresent(x, (key, currentCount) -> currentCount + 1);
            frequencyMap.putIfAbsent(x, 1);
        }

        for (Character x : t.toCharArray()) {
            if (!frequencyMap.containsKey(x)) {
                return false;
            } else {
                frequencyMap.computeIfPresent(x, (key, currentCount) -> currentCount - 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Approach 1: Sorting (Straightforward)
     *
     * Time Complexity: O(n log n) where n = s.length() Space Complexity: O(n) for char arrays
     *
     * Strategy: Sort both strings and compare if they are equal.
     */
    public boolean isAnagramSorting(String s, String t) {
        // Early exit: if lengths differ, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Convert to char arrays and sort
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);

        // Compare sorted arrays
        return Arrays.equals(sArr, tArr);
    }

    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        System.out.println(validAnagram.isAnagram("anagram", "nagaram")); // true
        System.out.println(validAnagram.isAnagram("rat", "car")); // false
    }
}
