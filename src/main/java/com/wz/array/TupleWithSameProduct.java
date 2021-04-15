package com.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d)
 * such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.
 *
 * Example 1:
 * Input: nums = [2,3,4,6]
 * Output: 8
 * Explanation: There are 8 valid tuples:
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 *
 * Example 2:
 * Input: nums = [1,2,4,5,10]
 * Output: 16
 * Explanation: There are 16 valids tuples:
 * (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
 * (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
 * (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,4,5)
 * (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 10^4
 * 3. All elements in nums are distinct.
 */
public class TupleWithSameProduct {
    public static void main(String[] args) {
        System.out.println(tupleSameProduct(new int[]{2, 3, 4, 6}));
        System.out.println(tupleSameProduct(new int[]{1, 2, 4, 5, 10}));
    }

    /**
     * 每四个满足条件的元素能组成 8 个 tuple，使用 map 存储当两个元素的乘积及其出现次数
     * 双层遍历数组，当发现当前乘积存在于 map 中时，出现次数 * 8 累加到结果中
     */
    public static int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];
                if (map.containsKey(product)) {
                    count += 8 * map.get(product);
                }
                map.put(product, map.getOrDefault(product, 0) + 1);
            }
        }
        return count;
    }
}
