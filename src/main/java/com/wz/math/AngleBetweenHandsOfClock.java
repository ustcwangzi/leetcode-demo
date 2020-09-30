package com.wz.math;

/**
 * Given two numbers, hour and minutes. Return the smaller angle (in degrees) formed between the hour and the minute hand.
 *
 * Example 1:
 * Input: hour = 12, minutes = 30
 * Output: 165
 *
 * Example 2:
 * Input: hour = 3, minutes = 30
 * Output: 75
 */
public class AngleBetweenHandsOfClock {
    public static void main(String[] args) {
        System.out.println(angleClock(12, 30));
        System.out.println(angleClock(3, 30));
    }

    /**
     * 一分钟的夹角是6度，一小时的夹角是30度
     * 根据这个定义分别算出时针和分针与 00:00 这个位置的夹角，注意最后两者的夹角一定不会大于360度
     */
    public static double angleClock(int hour, int minutes) {
        int oneMinAngle = 6, oneHourAngle = 30;
        double minutesAngle = oneMinAngle * minutes;
        double hoursAngle = (hour % 12 + minutes / 60.0) * oneHourAngle;
        double diff = Math.abs(hoursAngle - minutesAngle);
        return Math.min(diff, 360 - diff);
    }
}
