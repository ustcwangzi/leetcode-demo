package com.wz.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * In a array nums of size 2 * n, there are n + 1 unique elements, and exactly one of these elements is repeated n times.
 * Return the element repeated n times.
 *
 * Example 1:
 * Input: nums[1,2,3,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums[2,1,2,5,3,2]
 * Output: 2
 *
 * Example 3:
 * Input: nums[5,1,5,2,5,3,5,4]
 * Output: 5
 *
 *
 * Note:
 * 1. 4 <= nums.length <= 10000
 * 2. 0 <= nums[i] < 10000
 * 3. nums.length is even
 */
public class NRepeatedElementInSize2NArray {
    public static void main(String[] args) {
        System.out.println(repeatedNTimes(new int[]{1, 2, 3, 3}));
    }

    /**
     * 数组中包含 n+1 个不同元素，有一个元素出现了 n 次，因此整个数组中只有一个元素重复了，直接使用 set 判断即可
     */
    public static int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return 0;
    }
}
