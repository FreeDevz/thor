package io.awijaya.lab;

/**
 * https://leetcode.com/problems/power-of-two/description/
 * level: easy
 */
public class PowerOfTwo {
    /**
     * Approach 1: Bit Manipulation (Optimal Solution)
     *
     * Powers of two have exactly one bit set to 1 in their binary representation. For example: - 1
     * (2^0) = 0001 - 2 (2^1) = 0010 - 4 (2^2) = 0100 - 8 (2^3) = 1000
     *
     * When we subtract 1 from a power of two, all bits after the single '1' bit become '1', and the
     * original '1' bit becomes '0'. For example: - 8 (1000) - 1 = 7 (0111) - 4 (0100) - 1 = 3
     * (0011)
     *
     * Performing a bitwise AND between n and (n-1) will yield 0 if n is a power of two: - 8 & 7 =
     * 1000 & 0111 = 0000 - 4 & 3 = 0100 & 0011 = 0000
     *
     * @param n the integer to check
     * @return true if n is a power of two, false otherwise
     */
    public boolean isPowerOfTwoBitManipulation(int n) {
        // Must be positive and n & (n-1) must be 0
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * Approach 2: Iterative Division
     *
     * Continuously divide n by 2 until it becomes 1 (power of two) or until it's no longer
     * divisible by 2 (not a power of two).
     *
     * @param n the integer to check
     * @return true if n is a power of two, false otherwise
     */
    public boolean isPowerOfTwoIterativeDivision(int n) {
        if (n <= 0) {
            return false;
        }

        while (n > 1) {
            if (n % 2 != 0) {
                return false; // Not divisible by 2, so not a power of two
            }
            n /= 2;
        }

        return true; // Reached 1, so it's a power of two
    }
}
