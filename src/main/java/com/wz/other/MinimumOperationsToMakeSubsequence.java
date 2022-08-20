package com.wz.other;

import java.util.*;

/**
 * You are given an array target that consists of distinct integers and another integer array arr that can have duplicates.
 * In one operation, you can insert any integer at any position in arr. For example, if arr = [1,4,1,2], you can add 3 in the middle and make it [1,4,3,1,2].
 * Note that you can insert the integer at the very beginning or end of the array.
 * Return the minimum number of operations needed to make target a subsequence of arr.
 * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without
 * changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
 *
 * Example 1:
 * Input: target = [5,1,3], arr = [9,4,2,3,4]
 * Output: 2
 * Explanation: You can add 5 and 1 in such a way that makes arr = [5,9,4,1,2,3,4], then target will be a subsequence of arr.
 *
 * Example 2:
 * Input: target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
 * Output: 3
 *
 * Constraints:
 * 1. 1 <= target.length, arr.length <= 10^5
 * 2. 1 <= target[i], arr[i] <= 10^9
 * 3. target contains no duplicates.
 */
public class MinimumOperationsToMakeSubsequence {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{5, 1, 3}, new int[]{9, 4, 2, 3, 4}));
        System.out.println(minOperations(new int[]{6, 4, 8, 1, 3, 2}, new int[]{4, 7, 6, 2, 3, 8, 6, 1}));
    }

    /**
     * 是对 {@link com.wz.dynamicprogramming.LongestIncreasingSubsequence} 的扩展
     * target 长度减去最长子序列的长度就是结果，因为可以在 arr 任意位置插入数字，
     * 使用 Map 记录 target 中元素及其索引，问题转化为 arr 数组对应的 index 数组求最长递增子序列
     */
    public static int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }

        List<Integer> nums = new ArrayList<>();
        for (int cur : arr) {
            if (map.containsKey(cur)) {
                nums.add(map.get(cur));
            }
        }
        return target.length - lengthOfLIS(nums);
    }

    /**
     * stack 用来储存到目前为止最长的 subsequence，遍历 nums，对于每个数，用 ceiling 去寻找它应该在 stack 里的位置
     * 如果 num 比 stack 里所有元素都要大，可以成为新的 tail，把当前数加入 stack，否则说明当前 subsequence 里有比其大的元素可以被替换
     * 替换可能会破坏顺序，但是其实这无关紧要，因为最后关心的是 stack 的长度，而不是内容
     */
    public static int lengthOfLIS(List<Integer> nums) {
        TreeSet<Integer> stack = new TreeSet<>();
        for (int num : nums) {
            if (stack.isEmpty() || stack.last() < num) {
                stack.add(num);
                continue;
            }
            stack.remove(stack.ceiling(num));
            stack.add(num);
        }
        return stack.size();
    }
}
