package io.awijaya.algo;

public class MyStudentAttendanceRecord {
    public static boolean checkRecord(String s) {

        if (s.contains("LLL")) {
            return false;
        }
        int absentCounter = 0;
        for (char tmp : s.toCharArray()) {
            if (tmp == 'A') {
                absentCounter++;
            }
        }
        if (absentCounter >= 2) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String test1 = "PPALLP";
        System.out.println(checkRecord(test1));

        String test2 = "PPALLL";
        System.out.println(checkRecord(test2));
    }
}
