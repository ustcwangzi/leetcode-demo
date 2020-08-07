package com.wz.lists;

/**
 * Given two integer arrays startTime and endTime and given an integer queryTime.
 * The ith student started doing their homework at the time startTime[i] and finished it at time endTime[i].
 * Return the number of students doing their homework at time queryTime. More formally,
 * return the number of students where queryTime lays in the interval [startTime[i], endTime[i]] inclusive.
 *
 * Example 1:
 * Input: startTime = [1,2,3], endTime = [3,2,7], queryTime = 4
 * Output: 1
 * Explanation: We have 3 students where:
 * The first student started doing homework at time 1 and finished at time 3 and wasn't doing anything at time 4.
 * The second student started doing homework at time 2 and finished at time 2 and also wasn't doing anything at time 4.
 * The third student started doing homework at time 3 and finished at time 7 and was the only student doing homework at time 4.
 *
 * Example 2:
 * Input: startTime = [4], endTime = [4], queryTime = 4
 * Output: 1
 * Explanation: The only student was doing their homework at the queryTime.
 *
 * Example 3:
 * Input: startTime = [4], endTime = [4], queryTime = 5
 * Output: 0
 */
public class NumberOfStudentsDoingHomeworkAtGivenTime {
    public static void main(String[] args) {
        int[] startTime = new int[]{1, 2, 3};
        int[] endTime = new int[]{3, 2, 7};
        System.out.println(busyStudent(startTime, endTime, 4));

        startTime = new int[]{4};
        endTime = new int[]{4};
        System.out.println(busyStudent(startTime, endTime, 4));

        System.out.println(busyStudent(startTime, endTime, 5));
    }

    /**
     * 直接遍历，queryTime 介于 startTime 和 endTime 之间即可
     */
    public static int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int result = 0;
        for (int i = 0; i < endTime.length; i++) {
            if (queryTime >= startTime[i] && queryTime <= endTime[i]) {
                result++;
            }
        }
        return result;
    }
}
