package com.wz.math;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 *
 * Example:
 * @see ../../../../resource/RectangleArea.jpg
 * Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 * Output: 45
 *
 * Note:
 * Assume that the total area is never beyond the maximum possible value of int.
 */
public class RectangleArea {
    public static void main(String[] args) {
        System.out.println(computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    /**
     * 先求出重叠区间的四个点 left，bottom，right，top 的值，然后再求出重叠区间的面积
     */
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E), right = Math.max(Math.min(C, G), left);
        int bottom = Math.max(B, F), top = Math.max(Math.min(D, H), bottom);
        return (C - A) * (D - B) - (right - left) * (top - bottom) + (G - E) * (H - F);
    }
}
