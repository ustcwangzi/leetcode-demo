package com.wz.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * Constraints:
 * 1. 1 <= nums.length <= 8
 * 2. -10 <= nums[i] <= 10
 */
public class PermutationsII {
    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1, 2, 2}));
    }

    /**
     * 思路与 {@link Permutations}, {@link com.wz.array.SubsetsII} 类似
     * 使用一个 boolean 数组来标志性当前位置是否已经被使用过，若已使用，则跳过该值
     * 如果当前的值为重复值，则只有前面的值已被使用，当前值才可以被使用，这样确保了只有第一个出现的重复值可以加入结果中
     * 例如 [1,1,2]，当第一个 1 被加入结果中时，可以继续使用第二个 1 作为元素加入结果中从而生成 [1,1,2]
     * 同理，当试图将第二个 1 作为第一个元素加入结果中时，只要第一个 1 还没有被使用过，则不可以使用第二个 1，因此 [1,1,2] 不会重复出现
     * 其实，这个算法保证了所有重复的数字在结果集中的顺序和在原输入数组中的顺序是相同的，从而避免了重复
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.parallelSort(nums);
        dfs(nums, new boolean[nums.length], result, new ArrayList<>(nums.length));
        return result;
    }

    private static void dfs(int[] nums, boolean[] visited, List<List<Integer>> result, List<Integer> group) {
        if (group.size() == nums.length) {
            result.add(new ArrayList<>(group));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            // 下一个重复值只有在前一个重复值被使用的情况下才可以使用
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            visited[i] = true;
            group.add(nums[i]);
            dfs(nums, visited, result, group);
            visited[i] = false;
            group.remove(group.size() - 1);
        }
    }
}
