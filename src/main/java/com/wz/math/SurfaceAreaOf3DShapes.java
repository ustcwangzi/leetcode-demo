package com.wz.math;

/**
 * On a N * N grid, we place some 1 * 1 * 1 cubes.
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
 * Return the total surface area of the resulting shapes.
 *
 * Example 1:
 * Input: [[2]]
 * Output: 10
 *
 * Example 2:
 * Input: [[1,2],[3,4]]
 * Output: 34
 */
public class SurfaceAreaOf3DShapes {
    public static void main(String[] args) {
        System.out.println(surfaceArea(new int[][]{{2}}));
        System.out.println(surfaceArea(new int[][]{{1,2},{3,4}}));
    }

    /**
     * NxN个格子中，用1x1x1的立方体堆叠，grid[i][j]表示坐标格上堆叠的立方体个数，求这个3D多边形的表面积
     * 把每个柱体的表面积加起来（grid[i][j] * 4 ，4表示四个侧面，2表示上下两个面），然后减去重叠的部分即可。
     * 重叠的部分为x方向（或y方向）上相邻柱体中较小的grid值
     */
    public static int surfaceArea(int[][] grid) {
        int n = grid.length, result = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    result += 4 * grid[i][j] + 2;
                }
                if (i > 0) {
                    result -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
                }
                if (j > 0) {
                    result -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
                }
            }
        }
        return result;
    }
}
