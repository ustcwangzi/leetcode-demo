package com.wz.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
 * Return True if its possible otherwise return False.
 *
 * Example 1:
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 *
 * Example 2:
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3, 4, 4, 5, 6};
        System.out.println(isPossibleDivide(nums, 4));

        nums = new int[]{3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11};
        System.out.println(isPossibleDivide(nums, 3));
    }

    /**
     * 排序后查找
     * 首先判断长度是不是 k 的倍数，排序后用 countMap 记录每个数出现的数量
     * 然后遍历数组中每个元素 num，对于每个 num，判断 countMap 中是否存在 num + (0...k)，存在且数量大于0则将数量减1
     */
    public static boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }

        Arrays.parallelSort(nums);
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, ++count);
        }

        for (int num : nums) {
            if (countMap.get(num) == 0) {
                continue;
            }
            for (int j = 0; j < k; j++) {
                Integer count = countMap.get(num + j);
                // 对 map 中 num + (0...k) 的出现次数减1
                if (count != null && count > 0) {
                    countMap.put(num + j, --count);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
