package io.awijaya.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * level: medium
 */
public class LetterCombinationOfPhoneNumbers {
    // Mapping of digits to their corresponding letters
    private static final Map<Character, String> digitToLetters = Map.of('2', "abc", '3', "def", '4',
            "ghi", '5', "jkl", '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");

    /**
     * Solution 1: Recursive Backtracking
     *
     * This approach uses recursion to build all possible combinations. For each digit, we try all
     * possible letters and recursively build combinations for the remaining digits.
     */
    public List<String> letterCombinationsRecursive(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        // Base case: if we've processed all digits, add current combination to result
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get letters for current digit
        char digit = digits.charAt(index);
        String letters = digitToLetters.get(digit);

        // Try each letter for current digit
        for (char letter : letters.toCharArray()) {
            current.append(letter); // Choose
            backtrack(digits, index + 1, current, result); // Explore
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        LetterCombinationOfPhoneNumbers letterCombinationOfPhoneNumbers = new LetterCombinationOfPhoneNumbers();
        List<String> result = letterCombinationOfPhoneNumbers.letterCombinationsRecursive("23");
        for (String test: result) {
            System.out.println("test: " + test);
        }
    }
}
