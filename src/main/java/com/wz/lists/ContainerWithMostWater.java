package com.wz.lists;

import java.util.Arrays;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 *
 * @see ../../../../resource/ContainerWithMostWater.jpg
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * <p>
 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }

    /**
     * 分别计算每个height[i]向左和向右能扩展的最大面积
     * 以向左为例，找到左边第一个高度大于等于自己的那个下标
     */
    public static int maxArea(int[] height) {
        int[] leftArea = new int[height.length];
        int[] rightArea = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                if (height[j] >= height[i]) {
                    leftArea[i] = height[i] * (i - j);
                    break;
                }
            }
            for (int j = height.length - 1; j > i; j--) {
                if (height[j] >= height[i]) {
                    rightArea[i] = height[i] * (j - i);
                    break;
                }
            }
        }

        int maxLeft = Arrays.stream(leftArea).max().getAsInt();
        int maxRight = Arrays.stream(rightArea).max().getAsInt();
        return Math.max(maxLeft, maxRight);
    }

}
