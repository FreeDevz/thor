package io.awijaya.lab3;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/ransom-note/description/
 * level: easy
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) {
            return false;
        }

        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Count frequency of each character in magazine
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Check if ransomNote can be constructed
        for (char c : ransomNote.toCharArray()) {
            Integer count = charCount.get(c);
            if (count == null || count == 0) {
                return false;
            }
            charCount.put(c, count - 1);
        }

        return true;
    }
}
