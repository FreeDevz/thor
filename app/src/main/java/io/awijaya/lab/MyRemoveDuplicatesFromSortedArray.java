package io.awijaya.lab;

public class MyRemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int k = 1; // Index for next unique element

        for (int i = 1; i < nums.length; i++) {
            // If current element is different from previous element
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i]; // Place unique element at position k
                k++; // Move to next position for unique element
            }
        }

        return k; // Return count of unique elements
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(removeDuplicates(nums));

        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums2));

        int[] nums3 = {0, 0, 0, 1};
        System.out.println(removeDuplicates(nums3));

        int[] nums4 = {1, 1};
        System.out.println(removeDuplicates(nums4));

        int[] nums5 = {0, 0, 0, 0, 3};
        System.out.println(removeDuplicates(nums5));
    }
}
