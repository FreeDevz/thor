package io.awijaya.lab;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-nice-substring/description/
 * level: easy
 */
public class LongestNiceSubstring {
    /**
     * Approach 1: Divide and Conquer (Recommended)
     * <p>
     * Time Complexity: O(n^2) where n is the length of string Space Complexity: O(n) for recursion
     * stack
     * <p>
     * Strategy: For each character that doesn't have both uppercase and lowercase versions, split
     * the string at that position and recursively find the longest nice substring in both parts.
     * Return the longer of the two results.
     */
    public String longestNiceSubstringDivideAndConquer(String s) {
        return helper(s);
    }

    private String helper(String s) {
        if (s.length() < 2) {
            return "";
        }

        Set<Character> charSet = new HashSet<>();
        for (char c : s.toCharArray()) {
            charSet.add(c);
        }

        // Find the first character that doesn't have both cases
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!charSet.contains(Character.toLowerCase(c))
                    || !charSet.contains(Character.toUpperCase(c))) {

                // Split at this character and recurse
                String left = helper(s.substring(0, i));
                String right = helper(s.substring(i + 1));

                // Return the longer substring, or left if equal length (earliest occurrence)
                return left.length() >= right.length() ? left : right;
            }
        }

        // If we reach here, the entire string is nice
        return s;
    }

    /**
     * Approach 2: Sliding Window with Character Set Check
     *
     * Time Complexity: O(n^2) where n is the length of string Space Complexity: O(1) - only using
     * constant extra space
     *
     * Strategy: Use sliding window to check all possible substrings. For each substring, verify
     * that every character has both uppercase and lowercase versions.
     */
    public String longestNiceSubstringSlidingWindow(String s) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (isNice(substring) && substring.length() > result.length()) {
                    result = substring;
                }
            }
        }

        return result;
    }

    /**
     * Helper method to check if a substring is nice using set operations
     */
    private boolean isNice(String s) {
        if (s.length() < 2) {
            return false;
        }

        Set<Character> charSet = new HashSet<>();
        for (char c : s.toCharArray()) {
            charSet.add(c);
        }

        for (char c : charSet) {
            if (!charSet.contains(Character.toLowerCase(c))
                    || !charSet.contains(Character.toUpperCase(c))) {
                return false;
            }
        }

        return true;
    }
}
