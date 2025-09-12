package io.awijaya.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/description/
 */
public class MyPascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        if (numRows >= 1) {
            List<Integer> rowOne = List.of(1);
            result.add(rowOne);
        }

        List<Integer> rowOne = Arrays.asList(1, 1);
        if (numRows >= 2) {
            result.add(rowOne);
        }

        if (numRows > 2) {
            List<Integer> prevRow = rowOne;
            for (int i = 3; i <= numRows; i++) {
                List<Integer> currentRow = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    if (j == 0) {
                        currentRow.add(1);
                    }
                    if (j > 0 && j != i - 1) {
                        int leftRight = j - 1;
                        int topRight = j;
                        int sum = prevRow.get(leftRight) + prevRow.get(topRight);
                        currentRow.add(sum);
                    }
                    if (j == i - 1) {
                        currentRow.add(1);
                        result.add(currentRow);
                        prevRow = currentRow;
                        currentRow = new ArrayList<>();
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        new MyPascalsTriangle().generate(5);
    }
}
