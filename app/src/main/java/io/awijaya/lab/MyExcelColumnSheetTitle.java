package io.awijaya.lab;

/**
 * https://leetcode.com/problems/excel-sheet-column-title/description/
 * level: easy
 */
public class MyExcelColumnSheetTitle {
    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--; // Convert from 1-indexed to 0-indexed
            result.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        MyExcelColumnSheetTitle myExcelColumnSheetTitle = new MyExcelColumnSheetTitle();
        System.out.println(myExcelColumnSheetTitle.convertToTitle(1)); // A
        System.out.println(myExcelColumnSheetTitle.convertToTitle(28)); // AB
        System.out.println(myExcelColumnSheetTitle.convertToTitle(701)); // ZY
        System.out.println(myExcelColumnSheetTitle.convertToTitle(2147483647)); // FXSHRXW
    }
}
