package com.wz.lists;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Given a date, return the corresponding day of the week for that date.
 * The input is given as three integers representing the day, month and year respectively.
 * Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 *
 * Example 1:
 * Input: day = 31, month = 8, year = 2019
 * Output: "Saturday"
 *
 * Example 2:
 * Input: day = 18, month = 7, year = 1999
 * Output: "Sunday"
 *
 * Example 3:
 * Input: day = 15, month = 8, year = 1993
 * Output: "Sunday"
 */
public class DayOfTheWeek {
    private static final String[] weeks = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static void main(String[] args) {
        System.out.println(dayOfTheWeek(31, 8, 2019));
        System.out.println(dayOfTheWeek(18, 7, 1999));
        System.out.println(dayOfTheWeek(15, 8, 1993));
    }

    /**
     * 直接使用 LocalDate 即可， DayOfWeek.getValue 返回的是 1～7 对应周一到周日
     */
    public static String dayOfTheWeek(int day, int month, int year) {
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek week = date.getDayOfWeek();
        return weeks[week.getValue() - 1];
    }
}
