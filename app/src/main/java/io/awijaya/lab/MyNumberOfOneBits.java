package io.awijaya.lab;

/**
 * https://leetcode.com/problems/number-of-1-bits/description/
 * level: easy
 */
public class MyNumberOfOneBits {
    public int hammingWeight(int n) {
        int numberOf1Bits = 0;

        while (n > 0) {
            if (n % 2 == 1) {
                numberOf1Bits++;
            }

            n /= 2;
        }

        return numberOf1Bits;
    }

    public static void main(String[] args) {
        MyNumberOfOneBits myNumberOfOneBits = new MyNumberOfOneBits();
        System.out.println(myNumberOfOneBits.hammingWeight(11)); // 3
        System.out.println(myNumberOfOneBits.hammingWeight(128)); // 1
        System.out.println(myNumberOfOneBits.hammingWeight(2147483645)); // 30
    }
}
