package io.awijaya.lab;

// https://leetcode.com/problems/plus-one/description/

import java.util.Stack;

public class MyPlusOne {

    public static int[] plusOne(int[] digits) {
        Stack<Integer> outputStore = new Stack<>();

        int i = digits.length - 1;

        int carry = 0;
        while (i >= 0 || carry != 0) {
            int current;
            if (i >= 0) {
                current = digits[i];
            } else {
                current = 0;
            }

            int maybeCurrentPlusOne = 0;
            if (i == digits.length - 1) {
                maybeCurrentPlusOne = current + 1;
            } else {
                maybeCurrentPlusOne = current;
            }

            if (carry > 0) {
                maybeCurrentPlusOne += carry;
                carry = 0;
            }

            if (maybeCurrentPlusOne > 9) {
                carry = 1;
                outputStore.add(0);
            } else {
                outputStore.add(maybeCurrentPlusOne);
            }

            i--;
        }

        int[] output = new int[outputStore.size()];
        for (int j = 0; j < output.length; j++) {
            output[j] = outputStore.pop();
        }

        return output;
    }

    public int[] plusOneSimpleByCursor(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[]{1};
        }

        // Start from the rightmost digit
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                // No carry needed, just increment and return
                digits[i]++;
                return digits;
            }
            // Set current digit to 0 and continue to next digit
            digits[i] = 0;
        }

        // If we reach here, all digits were 9, need to create new array
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 3};
        System.out.println(plusOne(test)); // 1, 2, 4

        int[] test2 = {4, 3, 2, 1};
        System.out.println(plusOne(test2)); // 4, 3, 2, 2

        int[] test3 = {9};
        System.out.println(plusOne(test3)); // 10
    }
}
