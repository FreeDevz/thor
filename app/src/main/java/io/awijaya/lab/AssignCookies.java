package io.awijaya.lab;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/assign-cookies/description/
 * level: easy
 */
public class AssignCookies {
    /**
     * Approach 1: Greedy Two Pointer (Optimal)
     *
     * Algorithm: 1. Sort both greed factors and cookie sizes 2. Use two pointers: one for children
     * (g) and one for cookies (s) 3. Try to satisfy each child with the smallest available cookie
     * 4. If current cookie satisfies current child, move both pointers 5. Otherwise, try next
     * cookie (move only cookie pointer)
     *
     * Key Insight: - By sorting, we ensure optimal assignment - We want to use smallest cookie that
     * can satisfy each child - This leaves larger cookies for children with larger greed factors
     *
     * Time Complexity: O(n log n + m log m) where n = g.length, m = s.length - Sorting g takes O(n
     * log n) - Sorting s takes O(m log m) - Two pointer traversal takes O(n + m)
     *
     * Space Complexity: O(log n + log m) - Space used by sorting algorithms (typically O(log n) for
     * quicksort) - Can be O(1) if we consider sorting in-place
     *
     * @param g array of greed factors for children
     * @param s array of cookie sizes
     * @return maximum number of content children
     */
    public int findContentChildren(int[] g, int[] s) {
        // Sort both arrays in ascending order
        Arrays.sort(g);
        Arrays.sort(s);

        int childIndex = 0; // Pointer for children
        int cookieIndex = 0; // Pointer for cookies

        // Try to assign cookies to children
        while (childIndex < g.length && cookieIndex < s.length) {
            // If current cookie can satisfy current child
            if (s[cookieIndex] >= g[childIndex]) {
                childIndex++; // Move to next child (this child is satisfied)
            }
            cookieIndex++; // Move to next cookie (whether assigned or not)
        }

        // Number of satisfied children
        return childIndex;
    }

    public static void main(String[] args) {
        AssignCookies assignCookies = new AssignCookies();

        int[] g = {1, 2, 3};
        int[] s = {1, 1};
        System.out.println(assignCookies.findContentChildren(g, s)); // 1

        int[] g2 = {1, 2};
        int[] s2 = {1, 2, 3};
        System.out.println(assignCookies.findContentChildren(g2, s2)); // 2
    }
}
