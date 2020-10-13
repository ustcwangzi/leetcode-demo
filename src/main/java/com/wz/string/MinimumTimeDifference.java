package com.wz.string;

import java.util.Arrays;
import java.util.List;

/**
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
 *
 * Example:
 * Input: ["23:59","00:00"]
 * Output: 1
 *
 * Note:
 * 1. The number of time points in the given list is at least 2 and won't exceed 20000.
 * 2. The input time is legal and ranges from 00:00 to 23:59.
 */
public class MinimumTimeDifference {
    public static void main(String[] args) {
        System.out.println(findMinDifference(Arrays.asList("23:59", "00:00")));
    }

    /**
     * 把小时和分钟数提取出来并计算总分钟数存入一个新数组，然后再对新数组进行排序，再计算两两之差
     * 注意特殊情况就是第一个和最后一个时间点进行比较时，第一个时间点需要加上24小时再做差值
     */
    public static int findMinDifference(List<String> timePoints) {
        int[] nums = new int[timePoints.size()];
        int index = 0;
        for (String timePoint : timePoints) {
            int hour = Integer.parseInt(timePoint.substring(0, 2)), minutes = Integer.parseInt(timePoint.substring(3));
            nums[index++] = hour * 60 + minutes;
        }
        Arrays.parallelSort(nums);

        int result = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            result = Math.min(result, nums[i] - nums[i - 1]);
        }
        return Math.min(result, 1440 + nums[0] - nums[nums.length - 1]);
    }
}
