package com.wz.lists;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array.
 * Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.
 *
 * Example 1:
 * Input: [3, 1, 4, 1, 5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 *
 * Example 2:
 * Input:[1, 2, 3, 4, 5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 *
 * Example 3:
 * Input: [1, 3, 1, 5, 4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 */
public class KDiffPairsInArray {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 4, 1, 5};
        System.out.println(findPairs(nums, 2));

        nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(findPairs(nums, 1));

        nums = new int[]{1, 3, 1, 5, 4};
        System.out.println(findPairs(nums, 0));
    }

    /**
     * 含有重复数字的无序数组，还有一个整数k，让找出有多少对不重复的数对 (i, j) 使得i和j的差刚好为k
     * 由于k有可能为0，而只有含有至少两个相同的数字才能形成数对，那么就是说需要统计数组中每个数字的个数
     * 可以建立每个数字和其出现次数之间的映射，然后遍历 HashMap 中的数字，如果k为0且该数字出现的次数大于1，则结果 res 自增1
     * 如果k不为0，且用当前数字加上k后得到的新数字也在数组中存在，则结果 res 自增1
     */
    public static int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = 1;
            if (map.containsKey(num)) {
                count += map.get(num);
            }
            map.put(num, count);
        }

        if (k == 0) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() >= 2) {
                    result++;
                }
            }
        } else {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (map.containsKey(entry.getKey() + k)) {
                    result++;
                }
            }
        }

        return result;
    }
}
