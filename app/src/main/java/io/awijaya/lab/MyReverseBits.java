package io.awijaya.lab;

/**
 * https://leetcode.com/problems/reverse-bits/description/
 * level: easy
 */
public class MyReverseBits {
    public int reverseBits(int n) {
        String binaryString = String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');
        StringBuilder reversedStringBuilder = new StringBuilder();

        for (int i = binaryString.length() - 1; i > -1; i--) {
            reversedStringBuilder.append(binaryString.charAt(i));
        }

        return Integer.parseInt(reversedStringBuilder.toString(), 2);
    }

    /**
     * Approach 1: Bit-by-bit Reversal (Simple and Clear)
     *
     * Extract each bit from the rightmost position and build the result from left to right.
     *
     * Time: O(32) = O(1) Space: O(1)
     */
    public int reverseBitsByCursor(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            // Extract the rightmost bit
            int bit = n & 1;

            // Shift result left to make room for new bit
            result = (result << 1) | bit;

            // Shift n right to process next bit
            n = n >>> 1; // Use unsigned right shift
        }

        return result;
    }

    public static void main(String[] args) {
        MyReverseBits myReverseBits = new MyReverseBits();
        System.out.println(myReverseBits.reverseBits(43261596)); // 964176192
        System.out.println(myReverseBits.reverseBits(2147483644)); // 1073741822
    }
}
