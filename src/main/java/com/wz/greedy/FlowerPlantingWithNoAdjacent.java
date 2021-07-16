package com.wz.greedy;

import java.util.*;

/**
 * You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional path
 * between garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers.
 * All gardens have at most 3 paths coming into or leaving it.
 * Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.
 * Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden.
 * The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.
 *
 * Example 1:
 * Input: n = 3, paths = [[1,2],[2,3],[3,1]]
 * Output: [1,2,3]
 * Explanation:
 * Gardens 1 and 2 have different types.
 * Gardens 2 and 3 have different types.
 * Gardens 3 and 1 have different types.
 * Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].
 *
 * Example 2:
 * Input: n = 4, paths = [[1,2],[3,4]]
 * Output: [1,2,1,2]
 *
 * Constraints:
 * 1. 1 <= n <= 10^4
 * 2. 0 <= paths.length <= 2 * 10^4
 * 3. paths[i].length == 2
 * 4. 1 <= xi, yi <= n
 * 5. xi != yi
 * 6. Every garden has at most 3 paths coming into or leaving it.
 */
public class FlowerPlantingWithNoAdjacent {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(gardenNoAdj(3, new int[][]{{1, 2}, {2, 3}, {3, 1}})));
        System.out.println(Arrays.toString(gardenNoAdj(4, new int[][]{{1, 2}, {3, 4}})));
    }

    /**
     * n 个花园标号 1 到 n，paths 标记了哪些花园是相连的，每个花园最多只能连通三个其他花园
     * 现在要给每个花园选择一种颜色的花来种，可供选择的颜色只有四种，编号 1 到 4，要求相连的花园不能种相同颜色的花
     * 贪心算法
     * 为了快速知道每个花园都和其他哪些花园相连，需要建立一个无向图，由于花园编号从 1 开始，因此建立图时统一进行减一操作
     * 然后遍历每个花园，由于知道了其相邻的花园，就可以知道它们花的颜色。使用布尔数组 colors 来标记某种颜色是否被使用
     * 将相邻的花园对应的颜色全部标记为 true，然后从颜色 4 开始往前遍历，只要某种颜色没有被使用，就赋值给当前花园即可
     */
    public static int[] gardenNoAdj(int n, int[][] paths) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] path : paths) {
            graph.get(path[0] - 1).add(path[1] - 1);
            graph.get(path[1] - 1).add(path[0] - 1);
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] color = new boolean[5];
            // 相邻花园已使用的颜色全部标记为 true，代表本次不能选择
            for (int neighbor : graph.get(i)) {
                color[result[neighbor]] = true;
            }
            // 选一个没用过的颜色进行使用
            for (int j = 4; j > 0; j--) {
                if (!color[j]) {
                    result[i] = j;
                }
            }
        }
        return result;
    }
}
