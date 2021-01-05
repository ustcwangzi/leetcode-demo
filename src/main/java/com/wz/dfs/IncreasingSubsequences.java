package com.wz.dfs;

import java.util.*;

/**
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array,
 * and the length of an increasing subsequence should be at least 2.
 *
 * Example:
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *
 * Constraints:
 * 1. The length of the given array will not exceed 15.
 * 2. The range of integer in the given array is [-100,100].
 * 3. The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */
public class IncreasingSubsequences {
    public static void main(String[] args) {
        System.out.println(findSubsequences(new int[]{4, 6, 7, 7}));
    }

    /**
     * 与 {@link com.wz.array.Subsets} 类似
     * 需要考虑结果的重复，因此使用 Set 去重，另外在向 group 中添加元素时需要判断新加入的元素小于等于之前的最后一个元素
     */
    public static List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        dfs(nums, 0, result, new ArrayList<>());
        return new LinkedList<>(result);
    }

    private static void dfs(int[] nums, int start, Set<List<Integer>> result, List<Integer> group) {
        if (group.size() > 1) {
            result.add(new ArrayList<>(group));
        }
        for (int i = start; i < nums.length; i++) {
            if (group.size() == 0 || group.get(group.size() - 1) <= nums[i]) {
                group.add(nums[i]);
                dfs(nums, i + 1, result, group);
                group.remove(group.size() - 1);
            }
        }
    }
}
