package com.wz.dfs;

import java.util.*;

/**
 * You are given an array of n integers, nums, where there are at most 50 unique values in the array.
 * You are also given an array of m customer order quantities, quantity, where quantity[i] is the amount of integers the ith customer ordered.
 * Determine if it is possible to distribute nums such that:
 * - The ith customer gets exactly quantity[i] integers,
 * - The integers the ith customer gets are all equal, and
 * - Every customer is satisfied.
 * Return true if it is possible to distribute nums according to the above conditions.
 *
 * Example 1:
 * Input: nums = [1,2,3,4], quantity = [2]
 * Output: false
 * Explanation: The 0th customer cannot be given two different integers.
 *
 * Example 2:
 * Input: nums = [1,2,3,3], quantity = [2]
 * Output: true
 * Explanation: The 0th customer is given [3,3]. The integers [1,2] are not used.
 *
 * Example 3:
 * Input: nums = [1,1,2,2], quantity = [2,2]
 * Output: true
 * Explanation: The 0th customer is given [1,1], and the 1st customer is given [2,2].
 *
 * Constraints:
 * 1. n == nums.length
 * 2. 1 <= n <= 10^5
 * 3. 1 <= nums[i] <= 1000
 * 4. m == quantity.length
 * 5. 1 <= m <= 10
 * 6. 1 <= quantity[i] <= 10^5
 * 7. There are at most 50 unique values in nums.
 */
public class DistributeRepeatingIntegers {
    public static void main(String[] args) {
        System.out.println(canDistribute(new int[]{1, 2, 3, 4}, new int[]{2}));
        System.out.println(canDistribute(new int[]{1, 1, 2, 2}, new int[]{2, 2}));
    }

    /**
     * 并不关心分配的数字具体值，只需要满足个数要求，因此使用 count[] 记录数字出现次数
     * 然后对 quantity[] 排序，从大到小进行 DFS，满足不了最多的需求可以快速失败
     */
    public static boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int[] count = new int[freq.size()];
        int i = 0;
        for (int value : freq.values()) {
            count[i++] = value;
        }

        Arrays.parallelSort(quantity);
        // 从需求量最大的开始分配
        return dfs(count, quantity, quantity.length - 1);
    }

    private static boolean dfs(int[] count, int[] quantity, int index) {
        if (index == -1) {
            return true;
        }

        Set<Integer> used = new HashSet<>();
        // 尝试进行分配
        for (int i = 0; i < count.length; i++) {
            if (count[i] >= quantity[index] && used.add(count[i])) {
                // 满足 index-顾客
                count[i] -= quantity[index];
                if (dfs(count, quantity, index - 1)) {
                    return true;
                }
                // 还原
                count[i] += quantity[index];
            }
        }
        return false;
    }
}
