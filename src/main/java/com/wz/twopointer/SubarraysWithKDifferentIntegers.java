package com.wz.twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * A good array is an array where the number of different integers in that array is exactly k.
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 *
 * Example 2:
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 * Constraints:
 * 1. 1 <= nums.length <= 2 * 10^4
 * 2. 1 <= nums[i], k <= nums.length
 */
public class SubarraysWithKDifferentIntegers {
    public static void main(String[] args) {
        System.out.println(subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
        System.out.println(subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3));
    }

    /**
     * 直接求刚好有 k 个不同元素，在不遍历整个子数组的情况下不大容易求出，相对比较好求的最多有 k 个不同的元素的情况
     * 可以将问题转换为 (刚好有 k 个不同的元素) = (最多有 k 个不同的元素) - (最多有 k-1 个不同的元素)
     */
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return mostKDistinct(nums, k) - mostKDistinct(nums, k - 1);
    }

    /**
     * 使用滑动窗口求出最多有 k 个不同元素的子数组个数
     */
    private static int mostKDistinct(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, result = 0;
        for (int j = 0; j < nums.length; j++) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            while (i <= j && map.size() > k) {
                if (map.containsKey(nums[i])) {
                    map.put(nums[i], map.get(nums[i]) - 1);
                }
                if (map.get(nums[i]) == 0) {
                    map.remove(nums[i]);
                }
                i++;
            }
            // nums[i...j] 最多有 k 个不同元素
            result += j - i + 1;
        }
        return result;
    }
}
