package com.wz.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There is a donuts shop that bakes donuts in batches of batchSize. They have a rule where they must serve all of the donuts
 * of a batch before serving any donuts of the next batch. You are given an integer batchSize and an integer array groups,
 * where groups[i] denotes that there is a group of groups[i] customers that will visit the shop. Each customer will get exactly one donut.
 * When a group visits the shop, all customers of the group must be served before serving any of the following groups.
 * A group will be happy if they all get fresh donuts. That is, the first customer of the group does not receive a donut that was left over from the previous group.
 * You can freely rearrange the ordering of the groups. Return the maximum possible number of happy groups after rearranging the groups.
 *
 * Example 1:
 * Input: batchSize = 3, groups = [1,2,3,4,5,6]
 * Output: 4
 * Explanation: You can arrange the groups as [6,2,4,5,1,3]. Then the 1st, 2nd, 4th, and 6th groups will be happy.
 *
 * Example 2:
 * Input: batchSize = 4, groups = [1,3,2,5,2,2,1,6]
 * Output: 4
 *
 * Constraints:
 * 1. 1 <= batchSize <= 9
 * 2. 1 <= groups.length <= 30
 * 3. 1 <= groups[i] <= 10^9
 */
public class MaximumNumberOfGroupsGettingFreshDonuts {
    public static void main(String[] args) {
        System.out.println(maxHappyGroups(3, new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(maxHappyGroups(4, new int[]{1, 3, 2, 5, 2, 2, 1, 6}));
    }

    /**
     * 有一个甜甜圈商店，每批次都烤 batchSize 个甜甜圈，这个店铺有个规则，就是在烤一批新的甜甜圈时，之前所有甜甜圈都必须已经全部销售完毕
     * 当有一批顾客来到商店时，他们所有人都必须在下一批顾客来之前购买完甜甜圈。如果一批顾客中第一位顾客得到的甜甜圈不是上一组剩下的，那么这一组人都会很开心
     * DFS 计算所有可能的顾客排列情况，再使用 map 记录已计算的组合形式，减少重复计算
     */
    public static int maxHappyGroups(int batchSize, int[] groups) {
        // 各余数对应的组数
        int[] nums = new int[batchSize];
        for (int group : groups) {
            nums[group % batchSize]++;
        }
        // 余数为 0 的组是开心的
        return nums[0] + dfs(batchSize, nums, new HashMap<>(), 0, groups.length - nums[0]);
    }

    private static int dfs(int batchSize, int[] nums, Map<String, Integer> map, int lastNum, int leftNum) {
        // 全部顾客遍历完成
        if (leftNum == 0) {
            return 0;
        }
        String key = Arrays.toString(nums);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        // 上一批顾客买完了一批甜甜圈，当前组是开心的
        boolean happy = lastNum % batchSize == 0;
        int result = 0;
        // 遍历所有可能的排列
        for (int i = 1; i < batchSize; i++) {
            if (nums[i] > 0) {
                nums[i]--;
                result = Math.max(result, dfs(batchSize, nums, map, lastNum + i, leftNum - 1));
                nums[i]++;
                // 当前顾客刚好买完一批甜甜圈，提前结束循环
                if ((i + lastNum) % batchSize == 0) {
                    break;
                }
            }
        }

        result += happy ? 1 : 0;
        map.put(key, result);
        return result;
    }
}
