package com.wz.math;

import java.time.LocalDate;

/**
 * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.
 *
 * Example 1:
 * Input: date = "2019-01-09"
 * Output: 9
 * Explanation: Given date is the 9th day of the year in 2019.
 *
 * Example 2:
 * Input: date = "2019-02-10"
 * Output: 41
 */
public class DayOfTheYear {
    public static void main(String[] args) {
        System.out.println(dayOfYear("2019-01-09"));
        System.out.println(dayOfYear("2019-02-10"));
    }

    /**
     * 直接使用内置的LocalDate
     */
    public static int dayOfYear(String date) {
        String[] array = date.split("-");
        return LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2])).getDayOfYear();
    }
}
