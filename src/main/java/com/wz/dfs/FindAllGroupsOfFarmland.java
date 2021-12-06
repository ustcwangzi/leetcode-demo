package com.wz.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.
 * To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. These rectangular areas are called groups.
 * No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.
 * land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1).
 * Find the coordinates of the top left and bottom right corner of each group of farmland.
 * A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
 * Return a 2D array containing the 4-length arrays described above for each group of farmland in land.
 * If there are no groups of farmland, return an empty array. You may return the answer in any order.
 *
 * Example 1:
 * @link ../../../../resource/FindAllGroupsOfFarmland1.jpg
 * Input: land = [[1,0,0],[0,1,1],[0,1,1]]
 * Output: [[0,0,0,0],[1,1,2,2]]
 * Explanation:
 * The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
 * The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].
 *
 * Example 2:
 * @link ../../../../resource/FindAllGroupsOfFarmland2.jpg
 * Input: land = [[1,1],[1,1]]
 * Output: [[0,0,1,1]]
 * Explanation:
 * The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].
 *
 * Constraints:
 * 1. m == land.length
 * 2. n == land[i].length
 * 3. 1 <= m, n <= 300
 * 4. land consists of only 0's and 1's.
 * 5. Groups of farmland are rectangular in shape.
 */
public class FindAllGroupsOfFarmland {
    public static void main(String[] args) {
        int[][] result = findFarmland(new int[][]{{1, 0, 0}, {0, 1, 1}, {0, 1, 1}});
        Arrays.stream(result).forEach(array -> System.out.println(Arrays.toString(array)));
    }

    /**
     * 与{@link NumberOfIslands} 类似，遇到 farmland 时 DFS 寻找右下角的坐标
     */
    public static int[][] findFarmland(int[][] land) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 0) {
                    continue;
                }

                int[] bottomRight = new int[]{0, 0};
                dsf(land, i, j, bottomRight);
                list.add(new int[]{i, j, bottomRight[0], bottomRight[1]});
            }
        }

        int[][] result = new int[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private static void dsf(int[][] land, int i, int j, int[] bottomRight) {
        if (i < 0 || i >= land.length || j < 0 || j >= land[0].length || land[i][j] == 0) {
            return;
        }

        land[i][j] = 0;
        dsf(land, i + 1, j, bottomRight);
        bottomRight[0] = Math.max(bottomRight[0], i);
        dsf(land, i, j + 1, bottomRight);
        bottomRight[1] = Math.max(bottomRight[1], j);
    }
}
