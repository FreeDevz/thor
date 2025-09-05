package io.awijaya.algo;

// https://leetcode.com/problems/remove-element/

public class MyRemoveElement {

    public static int removeElement(int[] nums, int val) {

        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (current == val) {
                int j = i + 1;
                while (j < nums.length) {
                    int next = nums[j];
                    if (next != val) { // swap only if next is not same as val
                        nums[i] = next;
                        nums[j] = current;
                        k++;
                        break;
                    }
                    j++;
                }
            } else {
                k++;
            }
        }

        return k;
    }

    public static void main(String[] args) {
        int[] test1 = {3, 2, 2, 3};
        System.out.println(removeElement(test1, 3));

        int[] test2 = {0, 1, 2, 2, 3, 0, 4, 2};
        // 0, 1, 2, 3, 0, 4, 2, 2
        System.out.println(removeElement(test2, 2));
    }
}
