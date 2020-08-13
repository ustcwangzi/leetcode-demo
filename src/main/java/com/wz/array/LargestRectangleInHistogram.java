package com.wz.array;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 * @see ../../../../resource/TrappingRainWater.jpg
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
     * 遇到比栈中下标对应值小的高度，就计算此时的面积，和result比较，取较大值
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
