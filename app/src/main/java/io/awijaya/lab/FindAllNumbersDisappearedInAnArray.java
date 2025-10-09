package io.awijaya.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
 * level: easy
 */
public class FindAllNumbersDisappearedInAnArray {
    /**
     * Approach 1: In-Place Marking with Negation (Optimal Solution)
     *
     * Use the array indices to mark presence of numbers by negating values. Since all numbers are
     * in range [1,n], we can use each number as an index (minus 1). Mark presence by making the
     * value at that index negative.
     *
     * @param nums the input array
     * @return list of disappeared numbers
     */
    public List<Integer> findDisappearedNumbersInPlaceNegation(int[] nums) {
        List<Integer> result = new ArrayList<>();

        // Mark presence by negating values at indices
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        // Find indices with positive values - these are missing numbers
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        // Restore original array (optional, good practice)
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        FindAllNumbersDisappearedInAnArray findAllNumbersDisappearedInAnArray = new FindAllNumbersDisappearedInAnArray();

        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = findAllNumbersDisappearedInAnArray.findDisappearedNumbersInPlaceNegation(nums);
        for (int i : result) {
            System.out.println(i);
        }

//        int[] nums2 = {1, 1};
//        List<Integer> result2 = findAllNumbersDisappearedInAnArray.findDisappearedNumbersInPlaceNegation(nums2);
//        for (int j : result2) {
//            System.out.println(j);
//        }
    }
}
