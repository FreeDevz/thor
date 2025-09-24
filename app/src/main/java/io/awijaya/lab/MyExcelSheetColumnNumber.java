package io.awijaya.lab;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/description/
 * level: easy
 */
public class MyExcelSheetColumnNumber {
    public int titleToNumber(String columnTitle) {
        int total = 0;
        int incremental = 1;

        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            int current = (columnTitle.charAt(i) % 65) * incremental;
            total += current + incremental;
            incremental = incremental * 26;
        }

        return total;
    }

    public static void main(String[] args) {
        MyExcelSheetColumnNumber myExcelSheetColumnNumber = new MyExcelSheetColumnNumber();

        System.out.println(myExcelSheetColumnNumber.titleToNumber("A")); // 1
        System.out.println(myExcelSheetColumnNumber.titleToNumber("AB")); // 28
        System.out.println(myExcelSheetColumnNumber.titleToNumber("ZY")); // 701
    }

}
