package io.awijaya.lab;

/**
 * https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/description/
 * level: easy
 */
public class SubstringsOfSizeThreeWithDistinctCharacters {
    public int countGoodSubstrings(String s) {
        if (s.length() < 3) return 0;

        int count = 0;
        char[] window = new char[3];
        for (int i = 0; i < 3; i++) {
            window[i] = s.charAt(i);
        }
        if (isGoodWindow(window)) {
            count++;
        }

        for (int i = 1; (i + 2) < s.length(); i++) {
            window[0] = s.charAt(i);
            window[1] = s.charAt(i+1);
            window[2] = s.charAt(i+2);
            if (isGoodWindow(window)) {
                count++;
            }
        }

        return count;
    }

    private boolean isGoodWindow(char[] window) {
        if (window[0] != window[1] && window[1] != window[2] && window[0] != window[2]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SubstringsOfSizeThreeWithDistinctCharacters substringsOfSizeThreeWithDistinctCharacters = new SubstringsOfSizeThreeWithDistinctCharacters();

        System.out.println(substringsOfSizeThreeWithDistinctCharacters.countGoodSubstrings("xyzzaz")); // 1

        System.out.println(substringsOfSizeThreeWithDistinctCharacters.countGoodSubstrings("aababcabc")); // 4
    }
}
