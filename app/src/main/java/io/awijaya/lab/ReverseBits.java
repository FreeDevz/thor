package io.awijaya.lab;

/**
 * https://leetcode.com/problems/reverse-bits/description/
 * level: easy
 */
public class ReverseBits {
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
     * <p>
     * Extract each bit from the rightmost position and build the result from left to right.
     * <p>
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
        ReverseBits reverseBits = new ReverseBits();
        System.out.println(reverseBits.reverseBits(43261596)); // 964176192
        System.out.println(reverseBits.reverseBits(2147483644)); // 1073741822

        int positiveNum = 12; // Binary: 0000...00001100
        int negativeNum = -12; // Binary: 1111...11110100 (two's complement)

        /**
         * >>> (Unsigned Right Shift): Shifts bits to the right and fills the leftmost bits with zeros.
         * This means that for positive numbers, it behaves the same as >>, but for negative numbers, it effectively treats the number as unsigned, resulting in a positive value.
         *
         * >> (Signed Right Shift): Shifts bits to the right and fills the leftmost bits with the sign bit of the original number.
         * If the number was positive (sign bit 0), it fills with zeros. If the number was negative (sign bit 1), it fills with ones, preserving the sign.
         */
        // Unsigned right shift
        System.out.println(positiveNum >>> 2); // Output: 3 (Binary: 0000...00000011)
        System.out.println(negativeNum >>> 2); // Output: 1073741821 (Binary: 0011...111101) - a large positive number

        // Signed right shift for comparison
        System.out.println(positiveNum >> 2);  // Output: 3 (Binary: 0000...00000011)
        System.out.println(negativeNum >> 2);  // Output: -3 (Binary: 1111...11111101)
    }
}
