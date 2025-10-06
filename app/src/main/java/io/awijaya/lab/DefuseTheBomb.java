package io.awijaya.lab;

/**
 * http://leetcode.com/problems/defuse-the-bomb/description/
 * level: easy
 */
public class DefuseTheBomb {
    /**
     * Approach 1: Sliding Window (Recommended)
     *
     * Time Complexity: O(n) where n is the length of code Space Complexity: O(1) - only using
     * constant extra space
     *
     * Strategy: Use sliding window technique to efficiently calculate the sum of k consecutive
     * elements. Handle circular array by using modulo arithmetic.
     */
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] decrypted = new int[n];

        // Handle k == 0 case
        if (k == 0) {
            return decrypted; // All zeros
        }

        // Calculate initial window sum
        int windowSum = 0;
        if (k > 0) {
            // Sum of next k elements starting from index 1
            for (int i = 1; i <= k; i++) {
                windowSum += code[i % n];
            }
        } else {
            // Sum of previous |k| elements starting from index n + k
            int start = n + k;
            for (int i = 0; i < Math.abs(k); i++) {
                windowSum += code[(start + i) % n];
            }
        }

        // Slide the window and fill decrypted array
        for (int i = 0; i < n; i++) {
            decrypted[i] = windowSum;

            if (k > 0) {
                // Remove the element leaving the window and add the element entering
                windowSum -= code[(i + 1) % n];
                windowSum += code[(i + 1 + k) % n];
            } else {
                // For negative k, adjust window differently
                windowSum -= code[(i + n + k) % n];
                windowSum += code[i % n];
            }
        }

        return decrypted;
    }

    public static void main(String[] args) {
        DefuseTheBomb defuseTheBomb = new DefuseTheBomb();

//        int[] code = {5, 7, 1, 4};
//        int[] result = defuseTheBomb.decrypt(code, 3); // 12,10,16,13
//        for (int i : result) {
//            System.out.println(i);
//        }

//        int[] code2 = {1, 2, 3, 4};
//        int[] result2 = defuseTheBomb.decrypt(code2, 0); // 0,0,0,0
//        for (int i : result2) {
//            System.out.println(i);
//        }
//
        int[] code3 = {2, 4, 9, 3};
        int[] result3 = defuseTheBomb.decrypt(code3, -2); // 12,5,6,13
        for (int i : result3) {
            System.out.println(i);
        }
    }
}
