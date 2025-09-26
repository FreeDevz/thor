package io.awijaya.lab;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/isomorphic-strings/description/
 * level: easy
 */
public class MyIsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        if (s.isEmpty() || t.isEmpty() || (s.length() != t.length())) return false;

        if (s.length() == 1) return true;

        Map<Character, Character> sSeen = new HashMap<>();
        Map<Character, Character> tSeen = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char currentS = s.charAt(i);
            char currentT = t.charAt(i);

            if (!sSeen.containsKey(currentS)) {
                sSeen.put(currentS, currentT);
            } else {
                char storedT = sSeen.get(currentS);
                if (currentT != storedT) {
                    return false;
                }
            }

            if (!tSeen.containsKey(currentT)) {
                tSeen.put(currentT, currentS);
            } else {
                char storedS = tSeen.get(currentT);
                if (currentS != storedS) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MyIsomorphicStrings myIsomorphicStrings = new MyIsomorphicStrings();
        System.out.println(myIsomorphicStrings.isIsomorphic("egg", "add")); // true
        System.out.println(myIsomorphicStrings.isIsomorphic("foo", "bar")); // false
        System.out.println(myIsomorphicStrings.isIsomorphic("paper", "title")); // true
        System.out.println(myIsomorphicStrings.isIsomorphic("badc", "baba")); // false
        System.out.println(myIsomorphicStrings.isIsomorphic("bbbaaaba", "aaabbbba")); // false
    }
}
