package com.wz.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given an integer array nums and an integer k. You are asked to distribute this array into k subsets of equal size such that there are no two equal elements in the same subset.
 * A subset's incompatibility is the difference between the maximum and minimum elements in that array.
 * Return the minimum possible sum of incompatibilities of the k subsets after distributing the array optimally, or return -1 if it is not possible.
 * A subset is a group integers that appear in the array with no particular order.
 *
 * Example 1:
 * Input: nums = [1,2,1,4], k = 2
 * Output: 4
 * Explanation: The optimal distribution of subsets is [1,2] and [1,4].
 * The incompatibility is (2-1) + (4-1) = 4.
 * Note that [1,1] and [2,4] would result in a smaller sum, but the first subset contains 2 equal elements.
 *
 * Example 2:
 * Input: nums = [6,3,8,1,3,1,2,2], k = 4
 * Output: 6
 * Explanation: The optimal distribution of subsets is [1,2], [2,3], [6,8], and [1,3].
 * The incompatibility is (2-1) + (3-2) + (8-6) + (3-1) = 6.
 *
 * Example 3:
 * Input: nums = [5,3,3,6,3,3], k = 3
 * Output: -1
 * Explanation: It is impossible to distribute nums into 3 subsets where no two elements are equal in the same subset.
 *
 * Constraints:
 * 1. 1 <= k <= nums.length <= 16
 * 2. nums.length is divisible by k
 * 3. 1 <= nums[i] <= nums.length
 */
public class MinimumIncompatibility {
    public static void main(String[] args) {
        System.out.println(new MinimumIncompatibility().minimumIncompatibility(new int[]{1, 2, 1, 4}, 2));
        System.out.println(new MinimumIncompatibility().minimumIncompatibility(new int[]{6, 3, 8, 1, 3, 1, 2, 2}, 4));
    }

    private int min = Integer.MAX_VALUE;
    private int bucketSize = 0;

    /**
     * 将数组分为 k 组，求最小的 impact 之和，直接 DFS，使用 visited 避免重复计算
     */
    public int minimumIncompatibility(int[] nums, int k) {
        this.bucketSize = nums.length / k;
        List<Set<Integer>> buckets = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            buckets.add(new HashSet<>());
        }

        dfs(nums, 0, buckets, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void dfs(int[] nums, int i, List<Set<Integer>> buckets, int sum) {
        if (i >= nums.length) {
            min = Math.min(min, sum);
            return;
        }

        Set<Set<Integer>> visited = new HashSet<>();
        for (Set<Integer> bucket : buckets) {
            if (bucket.contains(nums[i]) || bucket.size() == bucketSize || visited.contains(bucket)) {
                continue;
            }

            int impact = computeImpact(bucket, nums[i]);
            sum += impact;
            // 只有 sum < min 时才需要继续 DFS
            if (sum < min) {
                bucket.add(nums[i]);
                dfs(nums, i + 1, buckets, sum);
                bucket.remove(nums[i]);
            }
            sum -= impact;
            visited.add(bucket);
        }
    }

    private static int computeImpact(Set<Integer> set, int num) {
        if (set.size() == 0) {
            return 0;
        }
        if (set.size() == 1) {
            return Math.abs(num - set.iterator().next());
        }

        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for (int value : set) {
            low = Math.min(low, value);
            high = Math.max(high, value);
        }
        if (num < low) {
            return low - num;
        }
        if (num > high) {
            return num - high;
        }
        return 0;
    }
}
