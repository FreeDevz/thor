package io.awijaya.lab;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower/description/
 * level: easy
 */
public class GuessNumberHigherOrLower {
    int picked;

    public GuessNumberHigherOrLower(int picked) {
        this.picked = picked;
    }

    public int guess(int num) {
        if (num == picked) {
            return 0;
        }

        if (num < picked) {
            return 1;
        }

        return -1;
    }

    public int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            int guessed = guess(mid);
            if (guessed == 0) {
                return mid;
            }
            if (guessed == 1) {
                left = mid + 1;
            }
            if (guessed == -1) {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        GuessNumberHigherOrLower guessNumberHigherOrLower = new GuessNumberHigherOrLower(6);
        System.out.println(guessNumberHigherOrLower.guessNumber(10)); // 5
//        System.out.println(guessNumberHigherOrLower.guessNumber(4)); // 1
//        System.out.println(guessNumberHigherOrLower.guessNumber(5)); // 0
    }
}
