package com.wz.stack;

import java.util.Arrays;

/**
 * Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
 * An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
 * We define that a subsequence a is more competitive than a subsequence b (of the same length)
 * if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b.
 * For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.
 *
 * Example 1:
 * Input: nums = [3,5,2,6], k = 2
 * Output: [2,6]
 * Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
 *
 * Example 2:
 * Input: nums = [2,4,3,3,5,4,9,6], k = 4
 * Output: [2,3,3,4]
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^9
 * 3. 1 <= k <= nums.length
 */
public class FindTheMostCompetitiveSubsequence {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(mostCompetitive(new int[]{3, 5, 2, 6}, 2)));
    }

    /**
     * 单调栈，因为本题需要返回数组，因此直接使用数组来表示栈
     * 剩下 k 个元素，也就是需要删除 n-k 个元素，使用 index 记录当前栈顶位置，remove 记录还可以删除的元素个数
     * 因为需要的结果是字典序最小的，而开始的元素越小，结果越小，因此只允许更大的元素入栈
     * 遍历数组，若栈不空 && 栈顶大于当前元素 && 可以删除的元素个数大于 0，则出栈，同时将 remove 减一
     * 然后判断栈是否已满，若已满说明当前元素无法入栈，则多删一个元素，将 remove 减一；否则将当前元素入栈
     */
    public static int[] mostCompetitive(int[] nums, int k) {
        int[] result = new int[k];
        int index = 0, remove = nums.length - k;
        for (int num : nums) {
            while (index > 0 && result[index - 1] > num && remove > 0) {
                index--;
                remove--;
            }

            if (index < k) {
                result[index++] = num;
            } else {
                remove--;
            }
        }
        return result;
    }
}
