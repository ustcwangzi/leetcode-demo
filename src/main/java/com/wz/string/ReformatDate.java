package com.wz.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a date string in the form Day Month Year, where:
 * 1. Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
 * 2. Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
 * 3. Year is in the range [1900, 2100].
 * Convert the date string to the format YYYY-MM-DD, where:
 * 1. YYYY denotes the 4 digit year.
 * 2. MM denotes the 2 digit month.
 * 3. DD denotes the 2 digit day.
 *
 * Example 1:
 * Input: date = "20th Oct 2052"
 * Output: "2052-10-20"
 *
 * Example 2:
 * Input: date = "6th Jun 1933"
 * Output: "1933-06-06"
 *
 * Constraints:
 * The given dates are guaranteed to be valid, so no error handling is necessary.
 */
public class ReformatDate {
    public static void main(String[] args) {
        System.out.println(reformatDate("20th Oct 2052"));
    }

    public static String reformatDate(String date) {
        Map<String, String> months = new HashMap<>(12);
        months.put("Jan", "01");
        months.put("Feb", "02");
        months.put("Mar", "03");
        months.put("Apr", "04");
        months.put("May", "05");
        months.put("Jun", "06");
        months.put("Jul", "07");
        months.put("Aug", "08");
        months.put("Sep", "09");
        months.put("Oct", "10");
        months.put("Nov", "11");
        months.put("Dec", "12");

        String[] array = date.split(" ");
        StringBuilder builder = new StringBuilder();
        builder.append(array[2]);
        builder.append("-");
        builder.append(months.get(array[1]));
        builder.append("-");
        String day = array[0].substring(0, array[0].length() - 2);
        if (day.length() == 1) {
            builder.append("0");
        }
        builder.append(day);
        return builder.toString();
    }
}
