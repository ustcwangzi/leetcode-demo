package com.wz.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * Constraints:
 * 1. 1 <= n <= 20
 * 2. 1 <= k <= n
 */
public class Combinations {
    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }

    /**
     * 是 {@link Permutations} 的简化版
     * {@link Permutations} 对结果的顺序有要求(即不同顺序是不同结果)，而本题不同顺序只要包含的元素一样，是相同的结果
     * {@link Permutations} 每次需要从 0 开始遍历，本题每次从 start 开始遍历即可
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(n, k, 1, result, new ArrayList<>(k));
        return result;
    }

    private static void dfs(int n, int k, int start, List<List<Integer>> result, List<Integer> group) {
        if (group.size() == k) {
            result.add(new ArrayList<>(group));
            return;
        }
        for (int i = start; i <= n; i++) {
            group.add(i);
            dfs(n, k, i + 1, result, group);
            group.remove(group.size() - 1);
        }
    }
}
