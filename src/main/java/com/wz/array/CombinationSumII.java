package com.wz.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * <p>
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class CombinationSumII {
    public static void main(String[] args) {
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum(candidates, 8));

        candidates = new int[]{2, 5, 2, 1, 2};
        System.out.println(combinationSum(candidates, 5));
    }

    /**
     * 思路与{@link CombinationSum}类似，只是元素不能重复使用
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(candidates);
        combinationDFS(candidates, target, 0, new LinkedList<>(), result);
        return result;
    }

    /**
     * DFS
     */
    private static void combinationDFS(int[] candidates, int target, int start,
                                       List<Integer> path, List<List<Integer>> result) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            result.add(new LinkedList<>(path));
        } else {
            for (int i = start; i < candidates.length; i++) {
                // 防止结果重复，所以本轮搜索跳过相同的元素
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                path.add(candidates[i]);
                // 同一个位置只能选取一次，因此递归时候下标加1，避免重复搜索自己的情况
                combinationDFS(candidates, target - candidates[i], i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}
