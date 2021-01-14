package com.wz.dfs;

/**
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
 * return the largest possible number of stones that can be removed.
 *
 * Example 1:
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0].
 * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 *
 * Example 2:
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Explanation: One way to make 3 moves is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,0].
 * 2. Remove stone [2,0] because it shares the same column as [0,0].
 * 3. Remove stone [0,2] because it shares the same row as [0,0].
 * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 *
 * Constraints:
 * 1. 1 <= stones.length <= 1000
 * 2. 0 <= xi, yi <= 104
 * 3. No two stones are at the same coordinate point.
 */
public class MostStonesRemovedWithSameRowOrColumn {
    public static void main(String[] args) {
        int[][] stones = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        System.out.println(removeStones(stones));
    }

    /**
     * 并查集 Union-Find
     * 对于属于同一个群组的石子，总有办法能按顺序移除到只剩一个石子，所以总共有多少个群组，最终就会剩下多少个石子，
     * 最大的移除个数就是总石子个数减去群组个数，因此问题就变成了找不同群组的个数
     * 一般来说，UF 算法的思路是每个个体先初始化为不同的群组，然后遍历有关联的两个个体，如果发现其 getRoot 不同，则手动将二者加入一个群组
     * 先将每个石子都当作是一个群组，这样就初始化了 n 个群组，分别在 root 数组中进行初始化，然后遍历任意两个石子组合，
     * 若这两个石子的横纵坐标有一个相同的话，说明属于同一个群组，将二者关联上，关联之前要分别对其调用 getRoot 函数，找到其最终的祖先结点。
     * 更新结束之后统计还剩几个群组，即 root 数组中的结点还是初始值时，说明是一个群组的祖先结点，count+1，最终返回 n - count 即可
     */
    public static int removeStones(int[][] stones) {
        int count = 0, n = stones.length;
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 同行或同列，合并到同一群组中
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    root[getRoot(root, i)] = getRoot(root, j);
                }
            }
        }

        // 剩余的群组个数
        for (int i = 0; i < n; i++) {
            count += root[i] == i ? 1 : 0;
        }
        return n - count;
    }

    private static int getRoot(int[] root, int i) {
        return i == root[i] ? i : getRoot(root, root[i]);
    }
}
