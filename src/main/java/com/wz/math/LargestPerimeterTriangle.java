package com.wz.math;

import java.util.Arrays;

/**
 * Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.
 * If it is impossible to form any triangle of non-zero area, return 0.
 *
 * Example 1:
 * Input: [2,1,2]
 * Output: 5
 *
 * Example 2:
 * Input: [1,2,1]
 * Output: 0
 */
public class LargestPerimeterTriangle {
    public static void main(String[] args) {
        System.out.println(largestPerimeter(new int[]{2, 1, 2}));
        System.out.println(largestPerimeter(new int[]{1, 2, 1}));
    }

    /**
     * 三角形的条件：两边之和>第三边。
     * 若要构成最大的三角形周长，只需要对数组排序，一直取出最大的三个值作为三角形的边，符合条件即可返回
     */
    public static int largestPerimeter(int[] A) {
        Arrays.parallelSort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }
}
