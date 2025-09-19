package io.awijaya.lab2;

import java.util.List;

/**
 * https://www.hackerrank.com/challenges/diagonal-difference/problem?isFullScreen=true
 * level easy
 */
public class MyDiagonalDifference {
    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int leftDiagonalPointer = 0;
        int leftDiagonalSum = 0;
        int rightDiagonalPointer = arr.size() - 1;
        int rightDiagonalSum = 0;

        for (List<Integer> row : arr) {
            leftDiagonalSum += row.get(leftDiagonalPointer);
            leftDiagonalPointer++;
            rightDiagonalSum += row.get(rightDiagonalPointer);
            rightDiagonalPointer--;
        }

        return Math.abs(leftDiagonalSum - rightDiagonalSum);
    }

    public static void main(String[] args) {
        int[][] mapTest = {{1, 2}, {4, 5}, {7, 8}};

        for (int i = 0; i < mapTest.length; i++) {
            for (int j = 0; j < mapTest[i].length; j++) {
                System.out.println(mapTest[i][j]);
            }
        }
    }
}
