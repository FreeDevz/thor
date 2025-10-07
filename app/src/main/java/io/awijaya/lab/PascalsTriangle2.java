package io.awijaya.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/
 * level: easy
 */
public class PascalsTriangle2 {
    public List<Integer> getRow(int rowIndex) {
//        List<Integer> result = new ArrayList<>();

        List<Integer> row = new ArrayList<>();

        // Initialize the row with all 1s
        for (int i = 0; i <= rowIndex; i++) {
            row.add(1);
        }

        // Build the row in-place from right to left
        for (int i = 2; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }

        return row;
    }

    public static void main(String[] args) {
        System.out.println(new PascalsTriangle2().getRow(3));
    }
}
