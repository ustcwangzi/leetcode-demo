package com.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty)
 * such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 * A subarray is defined as a contiguous block of elements in the array.
 *
 * Example 1:
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4],
 * and the sum of the remaining elements is 6, which is divisible by 6.
 *
 * Example 2:
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 * Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
 *
 * Example 3:
 * Input: nums = [1,2,3], p = 7
 * Output: -1
 * Explanation: There is no way to remove a subarray in order to get a sum divisible by 7.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^9
 * 3. 1 <= p <= 10^9
 */
public class MakeSumDivisibleByP {
    public static void main(String[] args) {
        System.out.println(minSubarray(new int[]{4, 3, 1, 2}, 6));
        System.out.println(minSubarray(new int[]{1, 2, 3}, 3));
    }

    /**
     * 前缀和 + Map
     * 移除最短子数组使得剩下的子数组之和能够被 p 整除，即寻找最短子数组使得 subSum%p == totalSum%p == target
     * cur == sum[0...j], pre = sum[0...i-1], sum[i...j] = cur-pre
     * (cur-pre)%p == target    ==>
     * cur%p - pre%p == target  ==>
     * pre%p = cur%p - target
     * 固定 j，求出当前的前缀和 cur，找到满足上述条件的 i（其前缀和为 pre）
     * 为快速找到 i，使用 map 存储每个前缀和对应的下标
     */
    public static int minSubarray(int[] nums, int p) {
        long total = 0;
        for (int num : nums) {
            total += num;
        }
        int target = (int) (total % p);
        if (target == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        long preSum = 0;
        int result = nums.length;
        for (int j = 0; j < nums.length; j++) {
            preSum += nums[j];
            int r = (int) (preSum % p);
            // +p 是避免 r < target
            int required = (r - target + p) % p;
            if (map.containsKey(required)) {
                int i = map.get(required) + 1;
                result = Math.min(result, j - i + 1);
            }
            map.put(r, j);
        }
        return result == nums.length ? -1 : result;
    }
}
