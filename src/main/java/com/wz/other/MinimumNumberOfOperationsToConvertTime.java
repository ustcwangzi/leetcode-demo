package com.wz.other;

/**
 * You are given two strings current and correct representing two 24-hour times.
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.
 * In one operation you can increase the time current by 1, 5, 15, or 60 minutes. You can perform this operation any number of times.
 * Return the minimum number of operations needed to convert current to correct.
 *
 * Example 1:
 * Input: current = "02:30", correct = "04:35"
 * Output: 3
 * Explanation:
 * We can convert current to correct in 3 operations as follows:
 * - Add 60 minutes to current. current becomes "03:30".
 * - Add 60 minutes to current. current becomes "04:30".
 * - Add 5 minutes to current. current becomes "04:35".
 * It can be proven that it is not possible to convert current to correct in fewer than 3 operations.
 *
 * Example 2:
 * Input: current = "11:00", correct = "11:01"
 * Output: 1
 * Explanation: We only have to add one minute to current, so the minimum number of operations needed is 1.
 *
 * Constraints:
 * 1. current and correct are in the format "HH:MM"
 * 2. current <= correct
 */
public class MinimumNumberOfOperationsToConvertTime {
    public static void main(String[] args) {
        System.out.println(convertTime("02:30", "04:35"));
        System.out.println(convertTime("11:00", "11:01"));
    }

    /**
     * 计算 correct 与 current 直接相差的分钟数，然后从 60、15、5 依次减去最大的分钟数，并加到结果中
     * 最后剩下的只能用 1 去减，直接将剩余的值加到结果中
     */
    public static int convertTime(String current, String correct) {
        String[] currentArray = current.split(":"), correctArray = correct.split(":");
        int delta = Integer.parseInt(correctArray[0]) * 60 + Integer.parseInt(correctArray[1])
                - Integer.parseInt(currentArray[0]) * 60 - Integer.parseInt(currentArray[1]);
        if (delta == 0) {
            return 0;
        }

        int count = 0;
        if (delta >= 60) {
            count += delta / 60;
            delta %= 60;
        }
        if (delta >= 15) {
            count += delta / 15;
            delta %= 15;
        }
        if (delta >= 5) {
            count += delta / 5;
            delta %= 5;
        }
        return count + delta;
    }
}
