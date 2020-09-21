package com.wz.math;

/**
 * Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit exactly once.
 * 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59.
 * The earliest 24-hour time is 00:00, and the latest is 23:59.
 * Return the latest 24-hour time in "HH:MM" format.  If no valid time can be made, return an empty string.
 *
 * Example 1:
 * Input: A = [1,2,3,4]
 * Output: "23:41"
 * Explanation: The valid 24-hour times are "12:34", "12:43", "13:24", "13:42", "14:23", "14:32", "21:34", "21:43", "23:14", and "23:41". Of these times, "23:41" is the latest.
 *
 * Example 2:
 * Input: A = [5,5,5,5]
 * Output: ""
 * Explanation: There are no valid 24-hour times as "55:55" is not valid.
 */
public class LargestTimeForGivenDigits {
    public static void main(String[] args) {
        System.out.println(largestTimeFromDigits(new int[]{1, 2, 3, 4}));
        System.out.println(largestTimeFromDigits(new int[]{5, 5, 5, 5}));
    }

    /**
     * 本质上是求一个数组的全排列，然后判断是否符合时间的格式
     */
    public static String largestTimeFromDigits(int[] arr) {
        int[] result = new int[]{-1};
        dfs(arr, result, 0, 0, new boolean[arr.length]);
        if (result[0] == -1) {
            return "";
        }
        return getRes(result[0]);
    }

    private static String getRes(int time) {
        int hour = time / 100;
        int minute = time % 100;
        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute);
    }

    private static void dfs(int[] arr, int[] result, int num, int count, boolean[] used) {
        if (count == arr.length) {
            result[0] = Math.max(result[0], num);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            int cal = num * 10 + arr[i];
            if (count == 1 && (cal < 0 || cal >= 24)) {
                continue;
            }
            if (count == 3 && (cal % 100 < 0 || cal % 100 >= 60)) {
                continue;
            }
            used[i] = true;
            dfs(arr, result, cal, count + 1, used);
            used[i] = false;
        }
    }
}
