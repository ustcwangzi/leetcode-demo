package com.wz.dynamicprogramming;

import java.util.Stack;

/**
 * Given an array arr of positive integers, consider all binary trees such that:
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 * (Recall that a node is a leaf if and only if it has 0 children.)
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
 * It is guaranteed this sum fits into a 32-bit integer.
 *
 * Example 1:
 * Input: arr = [6,2,4]
 * Output: 32
 * Explanation:
 * There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
 *     24            24
 *    /  \          /  \
 *   12   4        6    8
 *  /  \               / \
 * 6    2             2   4
 *
 * Constraints:
 * 1. 2 <= arr.length <= 40
 * 2. 1 <= arr[i] <= 15
 * 3. It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 */
public class MinimumCostTreeFromLeafValues {
    public static void main(String[] args) {
        System.out.println(mctFromLeafValues(new int[]{6, 2, 4}));
    }

    /**
     * 每次找最小的两个节点进行相乘
     * 维护一个递减排列的栈，在遍历 arr 时，若栈顶元素大于当前值则入栈，这样栈顶元素就是候选，至少能保证左邻居比它小。
     * 如果右邻居比它大（栈顶元素小于当前值），那么此栈顶元素就可以被选取出来相乘、并删除
     */
    public static int mctFromLeafValues(int[] arr) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int num : arr) {
            while (stack.peek() <= num) {
                int top = stack.pop();
                result += top * Math.min(stack.peek(), num);
            }
            stack.push(num);
        }
        while (stack.size() > 2) {
            result += stack.pop() * stack.peek();
        }
        return result;
    }

}
