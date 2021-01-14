package com.wz.dfs;

/**
 * In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.
 *
 * (Note that backslash characters are escaped, so a \ is represented as "\\".)
 *
 * Return the number of regions.
 *
 *
 *
 * Example 1:
 * Input:
 * [
 *   " /",
 *   "/ "
 * ]
 * Output: 2
 * Explanation: The 2x2 grid is as follows:
 * @see ../../../../resource/RegionsCutBySlashes1.jpg
 *
 * Example 2:
 * Input:
 * [
 *   " /",
 *   "  "
 * ]
 * Output: 1
 * Explanation: The 2x2 grid is as follows:
 * @see ../../../../resource/RegionsCutBySlashes2.jpg
 */
public class RegionsCutBySlashes {
    public static void main(String[] args) {
        System.out.println(regionsBySlashes(new String[]{" /", "/ "}));
    }

    /**
     * 并查集 Union-Find
     * 把每一小块又可以划分成四小小块，分别做上编号，上0下2左4右1，如果该小块不是在最左边或者最上边，则它的3肯定会和左边小块的1连接在一起，
     * 2肯定会和下面小块的0连接在一起，如果在一个小块中，“\”,则把小块中的01连一起，23连一起；如果“/”，则把03连一起，12连一起
     * 最后统计区域总数，即 root 数组中节点值还是初始值的个数
     */
    public static int regionsBySlashes(String[] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length();
        int[] root = new int[m * n * 4];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }

        for (int i = 0; i < m; i++) {
            char[] array = grid[i].toCharArray();
            for (int j = 0; j < n; j++) {
                int index = 4 * i * n + j * 4;
                if (array[j] == '\\') {
                    union(index, index + 1, root);
                    union(index + 2, index + 3, root);
                } else if (array[j] == '/') {
                    union(index, index + 3, root);
                    union(index + 2, index + 1, root);
                } else if (array[j] == ' ') {
                    union(index, index + 1, root);
                    union(index, index + 2, root);
                    union(index, index + 3, root);
                }

                if (i < m - 1) {
                    union(index + 2, index + 4 * n, root);
                }
                if (j < n - 1) {
                    union(index + 1, index + 4 + 3, root);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < root.length; i++) {
            if (root[i] == i) {
                count++;
            }
        }
        return count;
    }


    private static void union(int x, int y, int[] root) {
        int rootX = find(x, root), rootY = find(y, root);
        if (rootX != rootY) {
            root[rootX] = rootY;
        }
    }

    private static int find(int x, int[] root) {
        if (root[x] != x) {
            return root[x] = find(root[x], root);
        }
        return x;
    }
}
