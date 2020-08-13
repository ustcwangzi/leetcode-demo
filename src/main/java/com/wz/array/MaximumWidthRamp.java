package com.wz.array;

import java.util.Stack;

/**
 * Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j]. The width of such a ramp is j - i.
 * Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
 *
 * Example 1:
 *
 * Input: [6,0,8,2,1,5]
 * Output: 4
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
 *
 * Example 2:
 * Input: [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 */
public class MaximumWidthRamp {
    public static void main(String[] args) {
        int[] A = new int[]{6, 0, 8, 2, 1, 5};
        System.out.println(maxWidthRamp(A));

        A = new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
        System.out.println(maxWidthRamp(A));
    }

    /**
     * 使用单调递减栈，如果遇到一个数字，比栈顶元素更小，那么就入栈；
     * 否则就在栈里找到第一个小于等于它的元素，此时的距离就是最远距离
     */
    public static int maxWidthRamp(int[] A) {
        int result = 0;
        Stack<Integer> queue = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            if (queue.isEmpty() || A[queue.peek()] > A[i]) {
                queue.add(i);
            }
        }
        for (int i = A.length - 1; i >= 0; i--) {
            while (!queue.isEmpty() && A[queue.peek()] <= A[i]) {
                result = Math.max(result, i - queue.pop());
            }
        }

        return result;
    }
}
