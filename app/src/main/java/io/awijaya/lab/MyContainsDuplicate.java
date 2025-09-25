package io.awijaya.lab;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/
 * level: easy
 */
public class MyContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
//        Set<Integer> seen = new HashSet<>();
//        for (int number : nums) {
//            if (!seen.contains(number)) {
//                seen.add(number);
//            } else {
//                return true;
//            }
//        }

        return false;
    }

    public static void main(String[] args) {
        MyContainsDuplicate myContainsDuplicate = new MyContainsDuplicate();

        int[] test = {1, 2, 3, 1};
        System.out.println(myContainsDuplicate.containsDuplicate(test)); // true

        int[] test2 = {1, 2, 3, 4};
        System.out.println(myContainsDuplicate.containsDuplicate(test2)); // false

        int[] test3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println(myContainsDuplicate.containsDuplicate(test3)); // true
    }
}
