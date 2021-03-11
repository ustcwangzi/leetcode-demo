package com.wz.greedy;

/**
 * You are given a string time in the form of hh:mm, where some of the digits in the string are hidden (represented by ?).
 * The valid times are those inclusively between 00:00 and 23:59.
 * Return the latest valid time you can get from time by replacing the hidden digits.
 *
 * Example 1:
 * Input: time = "2?:?0"
 * Output: "23:50"
 * Explanation: The latest hour beginning with the digit '2' is 23 and the latest minute ending with the digit '0' is 50.
 *
 * Example 2:
 * Input: time = "0?:3?"
 * Output: "09:39"
 *
 * Constraints:
 * 1. time is in the format hh:mm.
 * 2. It is guaranteed that you can produce a valid time from the given string.
 */
public class LatestTimeByReplacingHiddenDigits {
    public static void main(String[] args) {
        System.out.println(maximumTime("2?:?0"));
        System.out.println(maximumTime("0?:3?"));
    }

    public static String maximumTime(String time) {
        char[] array = time.toCharArray();
        if (array[0] == '?') {
            array[0] = array[1] == '?' || array[1] < '4' ? '2' : '1';
        }
        if (array[1] == '?') {
            array[1] = array[0] == '2' ? '3' : '9';
        }
        if (array[3] == '?') {
            array[3] = '5';
        }
        if (array[4] == '?') {
            array[4] = '9';
        }
        return new String(array);
    }
}
