package io.awijaya.lab;

/**
 * https://leetcode.com/problems/palindrome-number/description/
 */
public class MyPalindromeNumber {

    public static boolean isPalindromeByString(int x) {
        if (x < 0) return false;

        String str = String.valueOf(x);

        String reverseStr = new StringBuilder(str).reverse().toString();
        if (reverseStr.equals(str)) {
            return true;
        }

        return false;
    }

    public static boolean isPalindromeByInteger(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;

        int reverse = 0;
        while (x > reverse) {
            // mytip: multiply by 10 to move to number to left, remainder by 10 to get last digit of a number
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }

        if (reverse == x || reverse / 10 == x) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int test1 = 121; // true
        int test2 = 1221; // true
        int test3 = 12321; // true
        int test4 = 122; // false
        int test5 = 12123; // false
        int test6 = 0; // true

        System.out.println(isPalindromeByString(test1));
        System.out.println(isPalindromeByString(test2));
        System.out.println(isPalindromeByString(test3));
        System.out.println(isPalindromeByString(test4));
        System.out.println(isPalindromeByString(test5));
        System.out.println(isPalindromeByString(test6));

        System.out.println("===========================");

        System.out.println(isPalindromeByInteger(test1));
        System.out.println(isPalindromeByInteger(test2));
        System.out.println(isPalindromeByInteger(test3));
        System.out.println(isPalindromeByInteger(test4));
        System.out.println(isPalindromeByInteger(test5));
        System.out.println(isPalindromeByInteger(test6));
    }
}
