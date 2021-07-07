package com.wz.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.
 * The athletes are placed based on their scores, where the 1st place athlete has the highest score,
 * the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
 * 1. The 1st place athlete's rank is "Gold Medal".
 * 2. The 2nd place athlete's rank is "Silver Medal".
 * 3. The 3rd place athlete's rank is "Bronze Medal".
 * 4. For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
 * Return an array answer of size n where answer[i] is the rank of the ith athlete.
 *
 * Example 1:
 * Input: score = [5,4,3,2,1]
 * Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
 *
 * Example 2:
 * Input: score = [10,3,8,9,4]
 * Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].
 *
 * Constraints:
 * 1. n == score.length
 * 2. 1 <= n <= 10^4
 * 3. 0 <= score[i] <= 10^6
 * 4. All the values in score are unique.
 */
public class RelativeRanks {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRelativeRanks(new int[]{5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(findRelativeRanks(new int[]{10, 3, 8, 9, 4})));
    }

    /**
     * Map + 排序
     * 先使用 map 记录每个元素的原始位置，然后对 score 排序，逆序遍历排序后的数组计算名次
     * 最后一个为 Gold，倒数第二个为 Silver，倒数第三个为 Bronze，其他为 n-i，将名次更新到对应的原始位置中
     */
    public static String[] findRelativeRanks(int[] score) {
        int n = score.length;
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(score[i], i);
        }
        Arrays.parallelSort(score);

        String[] result = new String[n];
        for (int i = n - 1; i >= 0; i--) {
            String value;
            if (i == n - 1) {
                value = "Gold Medal";
            } else if (i == n - 2) {
                value = "Silver Medal";
            } else if (i == n - 3) {
                value = "Bronze Medal";
            } else {
                value = String.valueOf(n - i);
            }
            result[map.get(score[i])] = value;
        }
        return result;
    }
}
