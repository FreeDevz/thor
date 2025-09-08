package io.awijaya.lab;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 */
public class MyLongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        String longestCommonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            longestCommonPrefix = findCommonPrefix(longestCommonPrefix, strs[i]);
        }


        return longestCommonPrefix;
    }

    public static String findCommonPrefix(String longestCommonPrefix, String str) {
        int minLength = Math.min(str.length(), longestCommonPrefix.length()); // mytip: learn to use Math class
        int commonLength = 0;

        for(int i = 0; i < minLength; i++) {
            if (str.charAt(i) != longestCommonPrefix.charAt(i)) {
                break;
            }
            commonLength++;
        }
        return longestCommonPrefix.substring(0, commonLength);
    }

    public static void main(String[] args) {
        String[] test1 = {"flower", "flow", "flight"}; // "fl"
        String[] test2 = {"dog", "racecar", "car"}; // ""
        String[] test3 = {"flower", "flight"}; // "fl"
        String[] test4 = {"flower", "flow"}; // "flow"
        String[] test5 = {"flower", "flowered"}; // "flower"
        String[] test6 = {"reflower", "flow", "flight"}; // ""
        String[] test7 = {"acc", "aaa", "aaba"}; // "a"
        String[] test8 = {"flower", "flower", "flower", "flower"}; // "flower"

        System.out.println(longestCommonPrefix(test1));
        System.out.println(longestCommonPrefix(test2));
        System.out.println(longestCommonPrefix(test3));
        System.out.println(longestCommonPrefix(test4));
        System.out.println(longestCommonPrefix(test5));
        System.out.println(longestCommonPrefix(test6));
        System.out.println(longestCommonPrefix(test7));
        System.out.println(longestCommonPrefix(test8));
    }
}
