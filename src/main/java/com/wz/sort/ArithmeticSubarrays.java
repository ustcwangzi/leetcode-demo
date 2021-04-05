package com.wz.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A sequence of numbers is called arithmetic if it consists of at least two elements,
 * and the difference between every two consecutive elements is the same.
 * More formally, a sequence s is arithmetic if and only if s[i+1] - s[i] == s[1] - s[0] for all valid i.
 * For example, these are arithmetic sequences:
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic:
 * 1, 1, 2, 5, 7
 * You are given an array of n integers, nums, and two arrays of m integers each, l and r, representing the m range queries,
 * where the ith query is the range [l[i], r[i]]. All the arrays are 0-indexed.
 * Return a list of boolean elements answer, where answer[i] is true if the subarray nums[l[i]], nums[l[i]+1], ... , nums[r[i]]
 * can be rearranged to form an arithmetic sequence, and false otherwise.
 *
 * Example 1:
 * Input: nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
 * Output: [true,false,true]
 * Explanation:
 * In the 0th query, the subarray is [4,6,5]. This can be rearranged as [6,5,4], which is an arithmetic sequence.
 * In the 1st query, the subarray is [4,6,5,9]. This cannot be rearranged as an arithmetic sequence.
 * In the 2nd query, the subarray is [5,9,3,7]. This can be rearranged as [3,5,7,9], which is an arithmetic sequence.
 *
 * Constraints:
 * 1. n == nums.length
 * 2. m == l.length
 * 3. m == r.length
 * 4. 2 <= n <= 500
 * 5. 1 <= m <= 500
 * 6. 0 <= l[i] < r[i] < n
 * 7. -10^5 <= nums[i] <= 10^5
 */
public class ArithmeticSubarrays {
    public static void main(String[] args) {
        System.out.println(checkArithmeticSubarrays(new int[]{4, 6, 5, 9, 3, 7}, new int[]{0, 0, 2}, new int[]{2, 3, 5}));
    }

    /**
     * 根据 l、r 数组将 nums 划分为每个小数组，然后判断每个小数组是否是 Arithmetic 数组
     */
    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>(l.length);
        for (int i = 0; i < l.length; i++) {
            result.add(isArithmetic(Arrays.copyOfRange(nums, l[i], r[i] + 1)));
        }
        return result;
    }

    private static boolean isArithmetic(int[] array) {
        Arrays.parallelSort(array);
        int diff = array[1] - array[0];
        for (int i = 2; i < array.length; i++) {
            if (array[i] - array[i - 1] != diff) {
                return false;
            }
        }
        return true;
    }
}
