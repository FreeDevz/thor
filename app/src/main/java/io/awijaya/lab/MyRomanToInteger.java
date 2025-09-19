package io.awijaya.lab;

/**
 * https://leetcode.com/problems/roman-to-integer/
 * level: easy
 */
public class MyRomanToInteger {

    private static int getRomanValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int currentValue = getRomanValue(s.charAt(i));

            // Check if we need to subtract (current < next)
            if (i < n - 1 && currentValue < getRomanValue(s.charAt(i + 1))) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String test1 = "III"; // 3
        System.out.println(romanToInt(test1));

        String test2 = "LVIII"; // 58
        System.out.println(romanToInt(test2));

        String test3 = "MCMXCIV"; // 1994
        System.out.println(romanToInt(test3));
    }
}
