package io.awijaya.lab;

// https://leetcode.com/problems/merge-sorted-array/description/


/**
 * Approach 1: Two Pointers from End (Optimal Solution)
 * <p>
 * This is the most efficient approach for merging sorted arrays in-place. The key insight is to
 * work backwards from the end of nums1 to avoid overwriting elements that haven't been
 * processed yet.
 * <p>
 * Algorithm: 1. Start from the end of both arrays (last valid elements) 2. Compare elements and
 * place the larger one at the end of nums1 3. Move pointers backwards 4. Continue until all
 * elements from nums2 are processed
 * <p>
 * Time Complexity: O(m + n) where m and n are the lengths of the arrays Space Complexity: O(1)
 * as we modify nums1 in-place
 */
public class MyMergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointers for the last valid elements in each array
        int i = m - 1; // Last valid element in nums1
        int j = n - 1; // Last element in nums2
        int k = m + n - 1; // Last position in nums1

        // Merge from the end
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        if (nums1.length == 0) {
            return;
        }

//        for (int i = m; i < nums1.length; i++) {
//            for (int j = 0; j < nums2.length; j++) {
//                nums1[i] = nums2[j];
//                i++;
//            }
//        }
//
//        int i = 0;
//        int j = nums1.length - 1;

//        while ((m + j) < nums1.length && i < (m + j)) {
//        while (i < j && i < m - 1 && j > m - 1) {
//            if (nums1[i] > nums1[j]) {
//                int tmp = nums1[j];
//                nums1[j] = nums1[i];
//                nums1[i] = tmp;
//                j--;
//            } else {
//                i++;
//            }
//        }

//        for (int i = 0; i < nums1.length - 1; i++) {
//            int lookahead = m;
//            while (lookahead < nums1.length - 1) {
//                if (nums1[i] == nums1[lookahead]) {
//                    int tmp = nums1[lookahead-1];
//                    nums1[lookahead-1] = nums1[i];
//                    nums1[i] = tmp;
//                    break;
//                }
//                lookahead++;
//            }
//        }
    }

    public static void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void main(String[] args) {
        int[] nums1Test = {1, 2, 3, 0, 0, 0};
        int mTest = 3;

        int[] nums2Test = {2, 5, 6};
        int nTest = 3;

        merge(nums1Test, mTest, nums2Test, nTest); // 1,2,2,3,5,6
        print(nums1Test);

        // ----------------------
        System.out.println("=========================");
        int[] nums1Test2 = {-1, 0, 0, 3, 3, 3, 0, 0, 0};
        int mTest2 = 6;

        int[] nums2Test2 = {1, 2, 2};
        int nTest2 = 3;

        merge(nums1Test2, mTest2, nums2Test2, nTest2); // -1,0,0,1,2,2,3,3,3
        print(nums1Test2);

        // ----------------------
        System.out.println("=========================");
        int[] nums1Test3 = {-1, 0, 1, 1, 0, 0, 0, 0, 0};
        int mTest3 = 4;

        int[] nums2Test3 = {-1, 0, 2, 2, 3};
        int nTest3 = 5;

        merge(nums1Test3, mTest3, nums2Test3, nTest3); // -1,-1,0,0,1,1,2,2,3
        print(nums1Test3);

        // ----------------------
        System.out.println("=========================");
        int[] nums1Test4 = {-1, 3, 0, 0, 0, 0, 0};
        int mTest4 = 2;

        int[] nums2Test4 = {0, 0, 1, 2, 3};
        int nTest4 = 5;

        merge(nums1Test4, mTest4, nums2Test4, nTest4); // -1,0,0,1,2,3,3
        print(nums1Test4);

        System.out.println("=========================");
        int[] nums1Test5 = {1, 2, 3, 0, 0, 0};
        int mTest5 = 3;

        int[] nums2Test5 = {1, 2, 3};
        int nTest5 = 3;

        merge(nums1Test5, mTest5, nums2Test5, nTest5); // 1, 1, 2, 2, 3, 3
        print(nums1Test5);

        System.out.println("=========================");
        int[] nums1Test6 = {1, 2, 4, 5, 6, 0};
        int mTest6 = 5;

        int[] nums2Test6 = {3};
        int nTest6 = 1;

        merge(nums1Test6, mTest6, nums2Test6, nTest6); // 1,2,3,4,5,6
        print(nums1Test6);
    }
}
