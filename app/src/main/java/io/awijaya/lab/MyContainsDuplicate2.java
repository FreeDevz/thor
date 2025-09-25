package io.awijaya.lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/description/
 * level: easy
 */
public class MyContainsDuplicate2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> seenWithValues = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentValue = nums[i];

            if (!seenWithValues.containsKey(currentValue)) {
                List<Integer> indices = new ArrayList<>();
                indices.add(i);
                seenWithValues.put(currentValue, indices);
            } else {
                List<Integer> previousIndices = seenWithValues.get(currentValue);
                List<Integer> updatedIndices = new ArrayList<>(previousIndices);

                for (int previousIndex : previousIndices) {
                    if (Math.abs(i - previousIndex) <= k) {
                        return true;
                    } else {
                        updatedIndices.add(i);
                    }
                }
                seenWithValues.replace(currentValue, updatedIndices);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MyContainsDuplicate2 myContainsDuplicate2 = new MyContainsDuplicate2();

        int[] test = {1, 2, 3, 1};
        System.out.println(myContainsDuplicate2.containsNearbyDuplicate(test, 3)); // true

        int[] test2 = {1, 0, 1, 1};
        System.out.println(myContainsDuplicate2.containsNearbyDuplicate(test2, 1)); // true

        int[] test3 = {1, 2, 3, 1, 2, 3};
        System.out.println(myContainsDuplicate2.containsNearbyDuplicate(test3, 2)); // false
    }
}
