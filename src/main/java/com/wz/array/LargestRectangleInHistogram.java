package com.wz.array;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 * @link ../../../../resource/LargestRectangleInHistogram.jpg
 * Given height = [2,1,5,6,2,3]. The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * <p>
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));

        heights = new int[]{1, 1};
        System.out.println(largestRectangleArea(heights));
    }

    /**
     * 使用单调栈，就是栈内只存放单调递增或单调递减的序列
     * 遇到比栈中下标对应的值大的高度时，就将这个这个高度的下标压入栈中
     * 遇到比栈中下标对应值小的高度，就弹出该元素，并计算此时的面积，和result比较，取较大值
     * 假设当前位置是 i，弹出位置是 j，弹出后的栈顶是 k，那么计算的是 i 到 k 之间 j 能够覆盖的面积，等于 h[j] * (i-k-1)
     * 以 [2,1,5,6,2,3] 为例说明该过程：
     * i    stack       result
     * 0    [0]         0
     * 1    [1]         弹出 0，面积为 2
     * 2    [1,2]       2
     * 3    [1,2,3]     2
     * 4    [1,4]       弹出 3，面积为 6；继续弹出 2，面积为 10
     * 5    [1,4,5]
     * 栈不为空，继续做弹出操作，此时计算的是弹出元素 j 到结束位置的面积
     * stack    result
     * [1,4,5]  弹出 5，面积 3
     * [1,4]    弹出 4，面积 8
     * [1]      弹出 1，面积 6
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights.length <= 1) {
            return heights.length == 0 ? 0 : heights[0];
        }

        int result = 0;
        // 单增栈，保存元素数组下标，保持高度递增
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                result = Math.max(result, heights[stack.pop()] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
            stack.push(i);
        }
        // 此时数组已遍历完成，对栈中的剩余元素计算面积
        while (!stack.isEmpty()) {
            result = Math.max(result, heights[stack.pop()] * (stack.isEmpty() ?
                    heights.length : heights.length - stack.peek() - 1));
        }
        return result;
    }
}
