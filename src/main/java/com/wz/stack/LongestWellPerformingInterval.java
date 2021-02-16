package com.wz.stack;

import java.util.HashMap;
import java.util.Map;

/**
 * We are given hours, a list of the number of hours worked per day for a given employee.
 * A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.
 * A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.
 * Return the length of the longest well-performing interval.
 *
 * Example 1:
 * Input: hours = [9,9,6,0,6,6,9]
 * Output: 3
 * Explanation: The longest well-performing interval is [9,9,6].
 *
 * Constraints:
 * 1. 1 <= hours.length <= 10000
 * 2. 0 <= hours[i] <= 16
 */
public class LongestWellPerformingInterval {
    public static void main(String[] args) {
        System.out.println(longestWPI(new int[]{9, 9, 6, 0, 6, 6, 9}));
    }

    /**
     * 与 {@link com.wz.array.SubarraySumEqualsK} 类似
     * 假设 [i,j] 是最长符合条件的时间段，那么必定在这个区间内大于 8 的元素个数减去小于 8 的元素个数的值为 1
     * 将大于 8 的元素记为 1，小于等于 8 的元素记为 -1，preSum 记录前缀和，则因此本题就是需要找到 preSum[j] - preSum[i] == 1
     * 遍历数组，如果 preSum > 0，则 result 直接更新为 i + 1
     * 否则，若 map 中存在 preSum - 1 的 key，则更新 result = max{result, i - map[preSum - 1]}
     * 同时，记录每个 preSum 第一次出现的位置 i
     */
    public static int longestWPI(int[] hours) {
        int result = 0, n = hours.length;
        Map<Integer, Integer> map = new HashMap<>(n);
        int preSum = 0;

        for (int i = 0; i < n; i++) {
            preSum += hours[i] > 8 ? 1 : -1;
            if (preSum > 0) {
                result = i + 1;
                continue;
            }

            map.putIfAbsent(preSum, i);
            if (map.containsKey(preSum - 1)) {
                result = Math.max(result, i - map.get(preSum - 1));
            }
        }

        return result;
    }
}
