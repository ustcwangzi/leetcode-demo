package com.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of the non-negative integer x.
 * For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:
 * 1. 0 <= i < j < nums.length
 * 2. nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: nums = [42,11,1,97]
 * Output: 2
 * Explanation: The two pairs are:
 *  - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 *  - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^9
 */
public class CountNicePairsInArray {
    public static void main(String[] args) {
        System.out.println(countNicePairs(new int[]{42, 11, 1, 97}));
    }

    /**
     * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
     * 等同于
     * nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
     * 因此可以直接遍历数组，使用 map 记录每个 value 出现次数，再次出现时就进行叠加
     */
    public static int countNicePairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0, mod = 1000000007;
        for (int num : nums) {
            int value = num - reverse(num);
            if (map.containsKey(value)) {
                result = (result + map.get(value)) % mod;
            }
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        return result;
    }

    private static int reverse(int num) {
        int result = 0;
        while (num != 0) {
            result = result * 10;
            result += num % 10;
            num /= 10;
        }
        return result;
    }
}
