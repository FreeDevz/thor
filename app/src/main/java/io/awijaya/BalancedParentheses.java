package io.awijaya;

public class BalancedParentheses {

    public static boolean isBalanced(String str) {
        if (!str.startsWith("("))
            return false;

        int openBrackets = 0;
        int closeBrackets = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                openBrackets++;
            }
            if (str.charAt(i) == ')') {
                closeBrackets++;
            }
            if (closeBrackets > openBrackets) {
                return false;
            }
        }
        if (openBrackets != closeBrackets) {
            return false;
        }
        else
            return true;
    }

    public static void main(String[] args) {
        String test1 = "(()())"; // true
        String test2 = "(()"; // false
        String test3 = ")("; // false
        String test4 = "()(())"; // true
        String test5 = ")((())"; // false
        String test6 = "())("; // false

        System.out.println(isBalanced(test1));
        System.out.println(isBalanced(test2));
        System.out.println(isBalanced(test3));
        System.out.println(isBalanced(test4));
        System.out.println(isBalanced(test5));
        System.out.println(isBalanced(test6));
    }
}
