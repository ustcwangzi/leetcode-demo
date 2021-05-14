package com.wz.greedy;

import java.util.Stack;

public class MaximumSubarrayMinProduct {
    public static void main(String[] args) {

    }

    public static int maxSumMinProduct(int[] nums) {
        int n = nums.length, mod = 1000000007;
        int[] preSum = new int[n + 1];
        Stack<Integer> stack = new Stack<>();
        long result = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int sum = preSum[stack.pop()+1] - preSum[stack.firstElement()];
                result = Math.max(result, 1 * (stack.isEmpty() ? 1 : stack.firstElement()));
            }
            stack.push(i);
        }

        return (int) (result % mod);
    }
}
