package com.wz.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        System.out.println(combinationSum(candidates, 7));

        candidates = new int[]{2, 3, 5};
        System.out.println(combinationSum(candidates, 8));
    }

    /**
     * 典型的DFS并且返回路径的问题，为避免重复搜索，在开始前先堆数组进行排序
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(candidates);
        combinationDFS(candidates, target, 0, new LinkedList<>(), result);
        return result;
    }

    /**
     * DFS
     *
     * @param target 目标和的差值
     * @param start  搜索的开始位置
     * @param path   记录路径
     * @param result 最终结果
     */
    private static void combinationDFS(int[] candidates, int target, int start,
                                       List<Integer> path, List<List<Integer>> result) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            // 为避免后续path更新影响结果，这里每次需要新建一个列表
            result.add(new LinkedList<>(path));
        } else {
            for (int i = start; i < candidates.length; i++) {
                path.add(candidates[i]);
                combinationDFS(candidates, target - candidates[i], i, path, result);
                // 目标和不符合，将path的最新值去除，然后尝试下一个元素
                path.remove(path.size() - 1);
            }
        }
    }
}
