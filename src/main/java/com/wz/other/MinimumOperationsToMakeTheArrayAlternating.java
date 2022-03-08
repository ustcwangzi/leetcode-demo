package com.wz.other;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed array nums consisting of n positive integers.
 * The array nums is called alternating if:
 * 1. nums[i - 2] == nums[i], where 2 <= i <= n - 1.
 * 2. nums[i - 1] != nums[i], where 1 <= i <= n - 1.
 * In one operation, you can choose an index i and change nums[i] into any positive integer.
 * Return the minimum number of operations required to make the array alternating.
 *
 * Example 1:
 * Input: nums = [3,1,3,2,4,3]
 * Output: 3
 * Explanation:
 * One way to make the array alternating is by converting it to [3,1,3,1,3,1].
 * The number of operations required in this case is 3.
 * It can be proven that it is not possible to make the array alternating in less than 3 operations.
 *
 * Example 2:
 * Input: nums = [1,2,2,2,2]
 * Output: 2
 * Explanation:
 * One way to make the array alternating is by converting it to [1,2,1,2,1].
 * The number of operations required in this case is 2.
 * Note that the array cannot be converted to [2,2,2,2,2] because in this case nums[0] == nums[1] which violates the conditions of an alternating array.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^5
 */
public class MinimumOperationsToMakeTheArrayAlternating {
    public static void main(String[] args) {
        System.out.println(minimumOperations(new int[]{1, 2, 2, 2, 2}));
    }

    /**
     * 题目可以转换为找到数组中出现次数最高的两个元素，其他元素全部需要改变，又因为题目要求元素交替出现，因此统计出现次数时需要区分奇偶位置
     * 如果 oddTop[0] != evenTop[0]，可以直接选用，剩余要改变的个数为 n - oddMax - evenMax
     * 否则只能选择其中一个的最大值，剩余要改变的个数为 n - oddMax - evenSecond 或 n - oddSecond - evenMax
     */
    public static int minimumOperations(int[] nums) {
        Map<Integer, Integer> oddMap = new HashMap<>(), evenMap = new HashMap<>();
        oddMap.put(0, 0);
        evenMap.put(0, 0);
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                evenMap.put(nums[i], evenMap.getOrDefault(nums[i], 0) + 1);
            } else {
                oddMap.put(nums[i], oddMap.getOrDefault(nums[i], 0) + 1);
            }
        }

        int[] oddTop = getTopTwo(oddMap), evenTop = getTopTwo(evenMap);
        // 两个值不同，可以直接使用
        if (oddTop[0] != evenTop[0]) {
            return nums.length - oddMap.get(oddTop[0]) - evenMap.get(evenTop[0]);
        }
        // 两个值相同，只能选一个的 top1 和另一个的 top2
        return nums.length - Math.max(oddMap.get(oddTop[0]) + evenMap.get(evenTop[1]), oddMap.get(oddTop[1]) + evenMap.get(evenTop[0]));
    }

    private static int[] getTopTwo(Map<Integer, Integer> map) {
        int top1 = 0, top2 = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > map.get(top1)) {
                top2 = top1;
                top1 = entry.getKey();
            } else if (entry.getValue() > map.get(top2)) {
                top2 = entry.getKey();
            }
        }
        return new int[]{top1, top2};
    }
}
