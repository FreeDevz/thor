package io.awijaya.lab3;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/ransom-note/description/
 * level: easy
 */
public class RansomNote2 {
    public String find(String[] words, String note) {
        for (String word : words) {
            if (canCreateWord(word, note)) {
                return word;
            }
        }
        return "-";
    }

    public boolean canCreateWord(String word, String note) {
        Map<Character, Integer> map = new HashMap<>();
        for (char a: note.toCharArray()) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        for (char a: word.toCharArray()) {
            Integer frequency = map.get(a);
            if (frequency == null || frequency == 0) {
                return false;
            }
            map.put(a, frequency - 1);
        }
        return true;
    }

    public static void main(String[] argv) {
        String[] words = {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};
        String[] words2 = {"cat"};
        String note1 = "ctay";
        String note2 = "bcanihjsrrrferet";
        String note3 = "tbaykkjlga";
        String note4 = "bbbblkkjbaby";
        String note5 = "dad";
        String note6 = "breadmaking";
        String note7 = "dadaa";

        RansomNote2 solution = new RansomNote2();
        System.out.println("test: " + solution.find(words, note1));
        System.out.println("test: " + solution.find(words, note2));
        System.out.println("test: " + solution.find(words, note3));
        System.out.println("test: " + solution.find(words, note4));
        System.out.println("test: " + solution.find(words, note5));
        System.out.println("test: " + solution.find(words, note6));
        System.out.println("test: " + solution.find(words, note7));
    }
}
