package com.wz.dfs;

/**
 * Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered
 * a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:
 * 1. perm[i] is divisible by i.
 * 2. i is divisible by perm[i].
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 *
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1,2]:
 *     - perm[1] = 1 is divisible by i = 1
 *     - perm[2] = 2 is divisible by i = 2
 * The second beautiful arrangement is [2,1]:
 *     - perm[1] = 2 is divisible by i = 1
 *     - i = 2 is divisible by perm[2] = 1
 *
 * Constraints:
 * 1 <= n <= 15
 */
public class BeautifulArrangement {
    public static void main(String[] args) {
        System.out.println(countArrangement(2));
    }

    public static int countArrangement(int n) {
        boolean[] visited = new boolean[n + 1];
        return dfs(n, 1, visited);
    }

    /**
     * DFS
     * 使用 visited 数组来记录是否已经访问过，用 pos 来标记已经生成的数字的个数，如果大于 n 了，说明已经找到了一组排列，返回 1
     * 在 for 循环中，i 应该从 1 开始，因为遍历 1 到 n 中的所有数字，如果该数字未被使用过，且满足和坐标之间的整除关系，
     * 那么首先标记该数字已被访问过，再调用下一个位置的递归函数，之后再将 i 恢复成初始状态
     */
    private static int dfs(int n, int pos, boolean[] visited) {
        if (pos == n + 1) {
            return 1;
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && (i % pos == 0 || pos % i == 0)) {
                visited[i] = true;
                result += dfs(n, pos + 1, visited);
                visited[i] = false;
            }
        }
        return result;
    }
}
