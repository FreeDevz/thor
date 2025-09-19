package io.awijaya.lab;

/**
 * https://leetcode.com/problems/count-and-say/description/
 * level: medium
 */
public class MyCountAndSay {

    /**
     * Approach 1: Iterative String Building (Recommended)
     *
     * Builds the sequence iteratively by analyzing each term and constructing the next one. Uses
     * StringBuilder for efficient string concatenation.
     *
     * Time: O(n * m) Space: O(m)
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String result = "1";

        for (int i = 2; i <= n; i++) {
            result = buildNextTerm(result);
        }

        return result;
    }

    /**
     * Helper method to build the next term from the current term
     */
    private String buildNextTerm(String current) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i < current.length(); i++) {
            if (current.charAt(i) == current.charAt(i - 1)) {
                count++;
            } else {
                result.append(count).append(current.charAt(i - 1));
                count = 1;
            }
        }

        // Add the last group
        result.append(count).append(current.charAt(current.length() - 1));

        return result.toString();
    }

    public static void main(String[] args) {
        MyCountAndSay myCountAndSay = new MyCountAndSay();

//        System.out.println(myCountAndSay.countAndSay(1)); // 1
//        System.out.println(myCountAndSay.countAndSay(2)); // 11
        System.out.println(myCountAndSay.countAndSay(3)); // 21
        System.out.println(myCountAndSay.countAndSay(4)); // 1211
    }
}
