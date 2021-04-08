package com.wz.array;

/**
 * Given an array of positive integers arr,  find a pattern of length m that is repeated k or more times.
 * A pattern is a subarray (consecutive sub-sequence) that consists of one or more values,
 * repeated multiple times consecutively without overlapping. A pattern is defined by its length and the number of repetitions.
 * Return true if there exists a pattern of length m that is repeated k or more times, otherwise return false.
 *
 * Example 1:
 * Input: arr = [1,2,4,4,4,4], m = 1, k = 3
 * Output: true
 * Explanation: The pattern (4) of length 1 is repeated 4 consecutive times. Notice that pattern can be repeated k or more times but not less.
 *
 * Example 2:
 * Input: arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
 * Output: true
 * Explanation: The pattern (1,2) of length 2 is repeated 2 consecutive times. Another valid pattern (2,1) is also repeated 2 times.
 *
 * Example 3:
 * Input: arr = [1,2,3,1,2], m = 2, k = 2
 * Output: false
 * Explanation: Notice that the pattern (1,2) exists twice but not consecutively, so it doesn't count.
 *
 * Constraints:
 * 1. 2 <= arr.length <= 100
 * 2. 1 <= arr[i] <= 100
 * 3. 1 <= m <= 100
 * 4. 2 <= k <= 100
 */
public class DetectPatternOfLengthMRepeatedKOrMoreTimes {
    public static void main(String[] args) {
        System.out.println(containsPattern(new int[]{1, 2, 1, 2, 1, 1, 1, 3}, 2, 2));
        System.out.println(containsPattern(new int[]{1, 2, 3, 1, 2}, 2, 2));
    }

    /**
     * 判断数组中是否存在这样的子数组，其长度是 m*k，并且子数组可以分成长度为 m 的 k 个相同子数组（元素和顺序完全一致）
     * 遍历，对于每个位置 i，如果 arr[i] == arr[i+m]，则 count++，否则 count 置为 0
     * 如果 count 达到了 m×(k−1)，则说明找到了一个符合要求的模式
     * 这里使用 m×(k−1)，是因为对于最后一个子数组，不要求继续和后面的子数组相等，以 [1,2,1,2,1,2,3,4], 2, 3 为例
     * 当遍历到第二个子数组的最后一个元素 2 时，已经满足条件，此时 count = 4，也就是前面的两个子数组遍历完成，不再要求后面的元素
     */
    public static boolean containsPattern(int[] arr, int m, int k) {
        int count = 0;
        for (int i = 0; i < arr.length - m; i++) {
            if (arr[i] == arr[i + m]) {
                count++;
            } else {
                count = 0;
            }
            if (count == m * (k - 1)) {
                return true;
            }
        }
        return false;
    }
}
