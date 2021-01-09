package com.wz.dfs;

import java.util.Arrays;

/**
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel
 * of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels
 * (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
 * At the end, return the modified image.
 *
 * Example 1:
 * Input:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 *
 * Note:
 * 1. The length of image and image[0] will be in the range [1, 50].
 * 2. The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 * 3. The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 */
public class FloodFill {
    public static void main(String[] args) {
        int[][] image = new int[][]{
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int[][] result = floodFill(image, 1, 1, 2);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * DFS
     * 如果越界或者当前颜色跟起始颜色不同，直接返回，否则就给当前位置赋上新的颜色，然后对周围四个点继续调用递归函数
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private static void dfs(int[][] image, int i, int j, int color, int newColor) {
        int m = image.length, n = image[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] != color) {
            return;
        }
        image[i][j] = newColor;
        dfs(image, i + 1, j, color, newColor);
        dfs(image, i, j + 1, color, newColor);
        dfs(image, i - 1, j, color, newColor);
        dfs(image, i, j - 1, color, newColor);
    }
}
