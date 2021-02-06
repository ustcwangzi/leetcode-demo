package com.wz.stack;

import java.util.Stack;

/**
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
 * such that i < j < k and nums[i] < nums[k] < nums[j].
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 * Follow up: The O(n^2) is trivial, could you come up with the O(n logn) or the O(n) solution?
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 *
 * Example 2:
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 *
 * Example 3:
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 *
 * Constraints:
 * 1. n == nums.length
 * 2. 1 <= n <= 10^4
 * 3. -10^9 <= nums[i] <= 10^9
 */
public class Find132Pattern {
    public static void main(String[] args) {
        System.out.println(find132pattern(new int[]{1, 2, 3, 4}));
        System.out.println(find132pattern(new int[]{-1, 3, 2, 0}));
    }

    /**
     * 单调栈
     * 用 cur 记录模式中的 '2'，单调栈记录模式中的 '3'，然后从后向前遍历数组，找模式中的 '1'
     * 如果发现 cur > nums[i]，则说明找到了 '1'，直接返回 true
     * 如果 nums[i] 大于栈顶元素，则出栈并赋值给 cur，直到 nums[i] 小于等于栈顶，将 nums[i] 入栈，也就是只允许更小的元素入栈
     * 以 [2,4,2,3,5]，后三个数都不会进入 while 循环，那么栈中的数字为 5,3,2，此时 cur 还是 MIN_VALUE，
     * 当遍历到 4 的时候，4 大于栈顶元素 2，那么 2 出栈，且 cur = 2；此时继续 while 循环，因为 4 还是大于新栈顶元素 3，
     * 此时 3 出栈，且 cur = 3；现在栈顶元素是 5，while 循环结束，将 4 入栈。下一个数字 2，小于 cur，则找到符合要求的序列 [2,4,3]
     */
    public static boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int cur = Integer.MIN_VALUE, n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (cur > nums[i]) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                cur = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
