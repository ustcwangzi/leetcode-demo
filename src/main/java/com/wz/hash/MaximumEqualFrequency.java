package com.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of positive integers, return the longest possible length of an array prefix of nums,
 * such that it is possible to remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.
 * If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of ocurrences (0).
 *
 * Example 1:
 * Input: nums = [2,2,1,1,5,3,3,5]
 * Output: 7
 * Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4] = 5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
 *
 * Example 2:
 * Input: nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * Output: 13
 *
 * Constraints:
 * 1. 2 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^5
 */
public class MaximumEqualFrequency {
    public static void main(String[] args) {
        System.out.println(maxEqualFreq(new int[]{1, 2, 3}));
        System.out.println(maxEqualFreq(new int[]{1, 1, 2, 2, 3}));
        System.out.println(maxEqualFreq(new int[]{1, 1, 2, 2, 2}));
    }

    /**
     * 移除一个元素后使得所有数字的出现次数均相等，必须要删除一个数字，不能不删除，也不能删除超过一个，会有三种情况：
     * 1. 每个元素都只出现一次，删除任意元素都可以，结果就是 n-1
     * 2. 有一个元素只出现一次，其他元素出现次数相等且大于 1，那么移除该元素即可，如 [1, 1, 2, 2, 3]，移除 3
     * 3. 出现次数最多的元素只有一个，其他元素出现次数为最大次数减一，移除一个出现次数最大的元素即可，如 [1, 1, 2, 2, 2] 移除 2
     * 情况一，只需要使用 countMap 记录每个元素出现次数，并记录最大出现次数 max，若 max == 1 说明每个元素都只出现一次
     * 情况二，除了用 countMap 记录元素出现次数，还需要使用 freqMap 记录每个 count 出现的次数，若 max * freqMap[max] == i，说明满足情况二
     * 情况三，和情况二类似，只是判断条件不同，需要满足 (max-1) * (freqMap[max-1] + 1) == i，加一是因为对最大次数减一之后，相当于增加了 max-1 的出现次数
     */
    public static int maxEqualFreq(int[] nums) {
        // num -> count, count -> freq
        Map<Integer, Integer> countMap = new HashMap<>(), freqMap = new HashMap<>();
        int result = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = countMap.getOrDefault(nums[i], 0);
            countMap.put(nums[i], count + 1);
            max = Math.max(max, count + 1);

            freqMap.put(count, freqMap.getOrDefault(count, 0) - 1);
            freqMap.put(count + 1, freqMap.getOrDefault(count + 1, 0) + 1);

            if (max * freqMap.get(max) == i || (max - 1) * (freqMap.getOrDefault(max - 1, 0) + 1) == i || max == 1) {
                result = i + 1;
            }
        }
        return result;
    }
}
