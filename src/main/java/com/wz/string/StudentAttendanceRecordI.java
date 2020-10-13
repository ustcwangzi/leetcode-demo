package com.wz.string;

/**
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 * 1. 'A' : Absent.
 * 2. 'L' : Late.
 * 3. 'P' : Present.
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 * You need to return whether the student could be rewarded according to his attendance record.
 *
 * Example 1:
 * Input: "PPALLP"
 * Output: True
 *
 * Example 2:
 * Input: "PPALLL"
 * Output: False
 */
public class StudentAttendanceRecordI {
    public static void main(String[] args) {
        System.out.println(checkRecord("PPALLP"));
        System.out.println(checkRecord("PPALLL"));
    }

    public static boolean checkRecord(String s) {
        char[] array = s.toCharArray();
        int countA = 0;
        for (int i = 0; i < array.length; i++) {
            char cur = array[i];
            if (cur == 'A') {
                countA++;
            }
            if (countA > 1) {
                return false;
            }
            // 连续的L
            if (i <= array.length - 3 && array[i] == 'L' && array[i + 1] == 'L' && array[i + 2] == 'L') {
                return false;
            }
        }
        return true;
    }
}
