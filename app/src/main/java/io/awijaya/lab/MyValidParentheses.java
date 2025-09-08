package io.awijaya.lab;

// https://leetcode.com/problems/valid-parentheses/description/

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MyValidParentheses {

    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        // Odd length strings cannot be valid
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put('}', '{');
        mapping.put(']', '[');

        for (char c : s.toCharArray()) {
            if (mapping.containsKey(c)) {
                // Closing bracket
                if (stack.isEmpty() || stack.pop() != mapping.get(c)) {
                    return false;
                }
            } else {
                // Opening bracket
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // '(', ')', '{', '}', '[' and ']'
        String test1 = "()"; // true
        System.out.println(isValid(test1));

        String test2 = "()[]{}"; // true
        System.out.println(isValid(test2));

        String test3 = "(]"; // false
        System.out.println(isValid(test3));

        String test4 = "([])"; // true
        System.out.println(isValid(test4));

        String test5 = "([)]"; // false
        System.out.println(isValid(test5));
    }
}
