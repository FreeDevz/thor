package io.awijaya.lab;

// https://leetcode.com/problems/search-insert-position/

public class MySearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        System.out.println(5/2);
        System.out.println(4/2);

        int[] test = {1, 3, 5, 6};
        int target = 5;

        System.out.println(searchInsert(test, target)); // 2

        int[] test2 = {1, 3, 5, 6};
        int target2 = 2;

        System.out.println(searchInsert(test2, target2)); // 1

        int[] test3 = {1, 3, 5, 6};
        int target3 = 7;

        System.out.println(searchInsert(test3, target3)); // 4
    }
}
