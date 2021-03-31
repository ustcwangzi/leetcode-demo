package com.wz.sort;

import java.util.Arrays;

/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 *
 * Example 1:
 * Input: nums = [10,2]
 * Output: "210"
 *
 * Example 2:
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 *
 * Constraints:
 * 1. 1 <= nums.length <= 100
 * 2. 0 <= nums[i] <= 10^9
 */
public class LargestNumber {
    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{10, 2}));
    }

    /**
     * 需要返回的是一个字符串，因此，在构建时也以字符串的形式处理
     * 对于 s1 和 s2，如果 s1+s2 > s2+s1，就返回true，即按照两个字符串相加较大的次序进行排列
     * 排序之后，构建成一个字符串即可，最后处理一下全是 0 的情况
     */
    public static String largestNumber(int[] nums) {
        String[] array = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.parallelSort(array, ((o1, o2) -> (o2 + o1).compareTo(o1 + o2)));
        String result = String.join("", array);
        return result.charAt(0) == '0' ? "0" : result;
    }
}
