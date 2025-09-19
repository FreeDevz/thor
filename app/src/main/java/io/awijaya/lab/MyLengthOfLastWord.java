package io.awijaya.lab;

/**
 * https://leetcode.com/problems/length-of-last-word/description
 * level: easy
 */
public class MyLengthOfLastWord {

    public int lengthOfLastWord(String s) {
        String trimmed = s.trim();
        String lastWord = "";
        if (trimmed.contains(" ")) {
            int lastIndexOfWhitespace = trimmed.lastIndexOf(" ");
            lastWord = trimmed.substring(lastIndexOfWhitespace + 1, trimmed.length());
        } else {
            lastWord = trimmed;
        }

        return lastWord.length();
    }

    public static void main(String[] args) {

    }
}
