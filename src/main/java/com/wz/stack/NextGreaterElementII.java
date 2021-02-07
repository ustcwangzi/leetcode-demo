package com.wz.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element.
 * The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 */
public class NextGreaterElementII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 1})));
    }

    /**
     * 单调栈，与 {@link NextGreaterElementI} 类似
     * 只是本题是循环数组，因此需要遍历两倍数组的长度，发现当前元素 cur 大于栈顶位置的元素，则更新栈顶位置元素右侧第一个大于自己的元素为 cur
     * 注意获取当前元素 cur 时需要对长度进行取余，另外只有 i 小于 n 时才进行入栈操作，超过 n 的部分只是为了给之前栈中的元素找较大值
     */
    public static int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = 0; i < n * 2; i++) {
            int cur = nums[i % n];
            while (!stack.isEmpty() && cur > nums[stack.peek()]) {
                result[stack.pop()] = cur;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return result;
    }
}
