package io.awijaya.lab;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/single-number/
 *
 */
public class MySingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public int singleNumberHashSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                seen.remove(num);
            } else {
                seen.add(num);
            }
        }

        // The set should contain exactly one element
        return seen.iterator().next();
    }

    public static void main(String[] main) {
        int[] test = {2, 2, 1};
        System.out.println(new MySingleNumber().singleNumber(test)); // 1

        int[] test1 = {4, 1, 2, 1, 2};
        System.out.println(new MySingleNumber().singleNumber(test1)); // 4

        int[] test2 = {-1, -1, -2};
        System.out.println(new MySingleNumber().singleNumber(test2)); // -2

        int[] test3 = {1, 3, 1, -1, 3};
        System.out.println(new MySingleNumber().singleNumber(test3)); // -1
    }
}
