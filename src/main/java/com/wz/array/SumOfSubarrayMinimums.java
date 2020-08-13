package com.wz.array;

import java.util.Stack;

/**
 * Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 *
 * Example 1:
 * Input: [3,1,2,4]
 * Output: 17
 * Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 */
public class SumOfSubarrayMinimums {
    public static void main(String[] args) {
        int[] A = new int[]{3, 1, 2, 4};
        System.out.println(sumSubarrayMins(A));
        System.out.println(sumSubarrayMins2(A));
    }

    /**
     * 关心的是以某个数字结尾时的子数组最小值之和，用 dp[i] 表示以数字 A[i] 结尾的所有子数组最小值之和，
     * 将 dp[0] 初始化为 A[0]，结果 result 也初始化为 A[0]。然后从第二个数字开始遍历：
     * 1. 若大于等于前一个数字，则当前 dp[i] 赋值为 dp[i-1]+A[i]，因为当前数字 A[i] 组成了新的子数组，
     *    同时由于 A[i] 不会影响最小值，所以要把之前的最小值之和再加一遍
     * 2. 假如小于前一个数字，就需要向前遍历，去找到第一个小于 A[i] 的位置j
     *    2.1 假如j小于0，表示前面所有数字都是小于 A[i] 的，累加和为 (i+1) x A[i]
     *    2.2 若j大于等于0，则需要分成两部分累加，dp[j] + (i-j)xA[i]，前面有 i-j 个以 A[i] 为结尾的子数组的最小值是 A[i]，
     *        还需要加上以 A[j] 为结尾的最小值之和
     */
    public static int sumSubarrayMins(int[] A) {
        int result = A[0], m = (int) (1e9 + 7);
        int[] dp = new int[A.length];
        dp[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] >= A[i - 1]) {
                dp[i] = dp[i - 1] + A[i];
            } else {
                int j = i - 1;
                while (j >= 0 && A[i] < A[j]) {
                    j--;
                }
                dp[i] = j < 0 ? (i + 1) * A[i] : (i - j) * A[i] + dp[j];
            }
            result = (result + dp[i]) % m;
        }

        return result;
    }

    /**
     * 实际上是要求 sum(A[i] * f(i))，其中 f(i) 是子数组的数量，A[i] 是最小值
     * 为了求f(i)需要求left[i]和right[i]。
     * left[i]：A[i]左边大于A[i]的个数
     * right[i]：A[i]右边大于等于A[i]的个数
     * f(i) = (left[i] + 1) * (right[i] + 1)
     * 计算 left[i] 和 right[i] 可以使用单调栈实现
     */
    public static int sumSubarrayMins2(int[] A) {
        int result = 0, mod = (int) 1e9 + 7;
        // 左边大于A[i]的个数
        int[] left = new int[A.length];
        // 右边大于等于A[i]的个数
        int[] right = new int[A.length];

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < A.length; i++) {
            while (stack.peek() != -1 && A[stack.peek()] > A[i]) {
                stack.pop();
            }
            left[i] = i - stack.peek();
            stack.push(i);
        }

        stack.clear();
        stack.push(A.length);
        for (int i = A.length - 1; i >= 0; i--) {
            while (stack.peek() != A.length && A[stack.peek()] >= A[i]) {
                stack.pop();
            }
            right[i] = stack.peek() - i;
            stack.push(i);
        }

        for (int i = 0; i < A.length; i++) {
            result = (result + A[i] * left[i] * right[i]) % mod;
        }
        return result;
    }
}
