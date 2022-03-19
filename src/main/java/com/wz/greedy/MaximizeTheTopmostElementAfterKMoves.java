package com.wz.greedy;

/**
 * You are given a 0-indexed integer array nums representing the contents of a pile, where nums[0] is the topmost element of the pile.
 * In one move, you can perform either of the following:
 * 1. If the pile is not empty, remove the topmost element of the pile.
 * 2. If there are one or more removed elements, add any one of them back onto the pile. This element becomes the new topmost element.
 * You are also given an integer k, which denotes the total number of moves to be made.
 * Return the maximum value of the topmost element of the pile possible after exactly k moves. In case it is not possible to obtain a non-empty pile after k moves, return -1.
 *
 * Example 1:
 * Input: nums = [5,2,2,4,0,6], k = 4
 * Output: 5
 * Explanation:
 * One of the ways we can end with 5 at the top of the pile after 4 moves is as follows:
 * - Step 1: Remove the topmost element = 5. The pile becomes [2,2,4,0,6].
 * - Step 2: Remove the topmost element = 2. The pile becomes [2,4,0,6].
 * - Step 3: Remove the topmost element = 2. The pile becomes [4,0,6].
 * - Step 4: Add 5 back onto the pile. The pile becomes [5,4,0,6].
 * Note that this is not the only way to end with 5 at the top of the pile. It can be shown that 5 is the largest answer possible after 4 moves.
 *
 * Example 2:
 * Input: nums = [2], k = 1
 * Output: -1
 * Explanation:
 * In the first move, our only option is to pop the topmost element of the pile.
 * Since it is not possible to obtain a non-empty pile after one move, we return -1.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i], k <= 10^9
 */
public class MaximizeTheTopmostElementAfterKMoves {
    public static void main(String[] args) {
        System.out.println(maximumTop(new int[]{5, 2, 2, 4, 0, 6}, 4));
    }

    /**
     * k 次操作后最大的栈顶元素
     * 分为两种情况：
     * 1. 移除 k-1 个元素，然后将其中最大的元素放回栈顶
     * 2. 移除 k 个元素，num[k] 最为栈顶
     * 注意特殊场景：只有一个元素 && k 为奇数时，无论如何操作最后栈都是空的，直接返回 -1
     */
    public static int maximumTop(int[] nums, int k) {
        if ((k & 1) == 1 && nums.length == 1) {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < nums.length && i < k - 1; i++) {
            result = Math.max(result, nums[i]);
        }
        if (k < nums.length) {
            result = Math.max(result, nums[k]);
        }
        return result;
    }
}
