package io.awijaya.lab;

// https://leetcode.com/problems/sqrtx/description/

public class MySqrtx {
    public static int mySqrt(int x) {
        if (x <= 1) return x;

        int left = 2;
        int right = x;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            }
            if (square < x) {
                left = mid + 1;
            }
            if (square > x) {
                right = mid - 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(1)); // 1
        System.out.println(mySqrt(2)); // 1
        System.out.println(mySqrt(3)); // 1
        System.out.println(mySqrt(4)); // 2
        System.out.println(mySqrt(8)); // 3
        System.out.println(mySqrt(9)); // 3
        System.out.println(mySqrt(16)); // 4
        System.out.println(mySqrt(24)); // 4
        System.out.println(mySqrt(25)); // 5
        System.out.println(mySqrt(2147483647)); // 46340
    }
}
