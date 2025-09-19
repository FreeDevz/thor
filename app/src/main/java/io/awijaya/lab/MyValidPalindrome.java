package io.awijaya.lab;

/**
 * https://leetcode.com/problems/valid-palindrome/
 * level: easy
 */
public class MyValidPalindrome {
    public boolean isPalindrome(String s) {
        String cleaned = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        if (cleaned.isEmpty()) return true;

        int i = 0;
        int j = cleaned.length() - 1;

        while (i <= j) {
            if (cleaned.charAt(i) != cleaned.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static class TwoPointersApproach {
        public boolean isPalindrome(String s) {
            if (s == null || s.isEmpty()) {
                return true;
            }

            int left = 0;
            int right = s.length() - 1;

            while (left < right) {
                // Skip non-alphanumeric characters from left
                while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                    left++;
                }

                // Skip non-alphanumeric characters from right
                while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                    right--;
                }

                // Compare characters (case-insensitive)
                if (Character.toLowerCase(s.charAt(left)) != Character
                        .toLowerCase(s.charAt(right))) {
                    return false;
                }

                left++;
                right--;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        MyValidPalindrome myValidPalindrome = new MyValidPalindrome();

        String test = "A man, a plan, a canal: Panama";
        System.out.println(myValidPalindrome.isPalindrome(test)); // true

        String test2 = "race a car";
        System.out.println(myValidPalindrome.isPalindrome(test2)); // false

        String test3 = " ";
        System.out.println(myValidPalindrome.isPalindrome(test3)); // true
    }
}
