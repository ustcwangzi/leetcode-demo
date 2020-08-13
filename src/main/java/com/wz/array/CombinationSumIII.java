package com.wz.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {
    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
        System.out.println(combinationSum3(3, 9));
    }

    /**
     * 利用DFS，递归遍历
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(k, n, 1, new LinkedList<>(), result);
        return result;
    }

    /**
     * @param k 剩余可选个数
     * @param target 目标值
     * @param start 开始值
     * @param group 记录本次结果
     * @param result 最终结果
     */
    private static void dfs(int k, int target, int start, List<Integer> group, List<List<Integer>> result) {
        if (target < 0) {
            return;
        }
        if (target == 0 && k == 0) {
            result.add(new ArrayList<>(group));
        }
        for (int i = start; i < 10; i++) {
            group.add(i);
            dfs(k - 1, target - i, i + 1, group, result);
            group.remove(group.size() - 1);
        }
    }
}
