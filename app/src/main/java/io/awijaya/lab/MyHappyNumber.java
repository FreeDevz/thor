package io.awijaya.lab;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/
 * level: easy
 */
public class MyHappyNumber {
    public boolean isHappy(int n) {
        if (n < 10 && n % 2 != 1) return false;

        HashSet<Integer> seen = new HashSet<>();
        seen.add(n);

        int sum = 0;
        int tempNum = n;

        while (sum != 1) {
            while (tempNum > 0) {
                int digit = tempNum % 10; // Get the last digit
                int squared = digit * digit;
                sum += squared;
                tempNum /= 10; // Remove the last digit
            }

            if (sum == 1) {
                return true;
            } else {
                if (seen.contains(sum)) {
                    return false;
                }
                seen.add(sum);
                tempNum = sum;
                sum = 0;
            }
        }

        return false;
    }

    /**
     * Approach 1: HashSet Approach (Simple and Clear)
     *
     * Use a HashSet to detect cycles by tracking seen numbers.
     *
     * Time: O(log n) Space: O(log n)
     */
    public boolean isHappyHashSet(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getSumOfSquares(n);
        }

        return n == 1;
    }

    /**
     * Approach 2: Floyd's Cycle Detection (Optimal)
     *
     * Use two pointers moving at different speeds to detect cycles without extra space.
     *
     * Time: O(log n) Space: O(1)
     */
    public boolean isHappyFloyd(int n) {
        int slow = n;
        int fast = getSumOfSquares(n);

        while (fast != 1 && slow != fast) {
            slow = getSumOfSquares(slow);
            fast = getSumOfSquares(getSumOfSquares(fast));
        }

        return fast == 1;
    }

    private int getSumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        MyHappyNumber myHappyNumber = new MyHappyNumber();
//        System.out.println(myHappyNumber.isHappy(19)); // true
//        System.out.println(myHappyNumber.isHappy(2)); // false
        System.out.println(myHappyNumber.isHappy(3)); //
    }
}
