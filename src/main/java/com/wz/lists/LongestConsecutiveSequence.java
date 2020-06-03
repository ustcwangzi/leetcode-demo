package com.wz.lists;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }

    /**
     * 将所有数都加入集合中，然后再遍历这些数，能O(1)判断某个数是否在集合中
     * 所以可以以当前数为中心逐个向上及向下检查，为了避免重复检查，每查到一个数，都要将其从集合中移除
     * 这样每遇到一个数，都检查它的上下边界，就能找出最长的连续数列
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int result = 0;
        for (int num : nums) {
            int cur = num, count = 1;
            numSet.remove(cur);
            // 以当前数为中心向下检查
            while (numSet.contains(--cur)) {
                count++;
                numSet.remove(cur);
            }

            // 以当前数为中心向上检查
            cur = num;
            while (numSet.contains(++cur)) {
                count++;
                numSet.remove(cur);
            }

            // 结果更新
            result = Math.max(result, count);
        }

        return result;
    }
}
