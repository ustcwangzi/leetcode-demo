package com.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 *
 * Example 1:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 *
 * Example 2:
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. nums[i] is either 0 or 1.
 */
public class ContiguousArray {
    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0, 1, 0}));
    }

    /**
     * 求出 0 和 1 的个数相等的最长连续子序列
     * Map + 累加和
     * 数组中只包含 0 和 1，将 0 看作 -1 的话，如果 sum[i...j] == 0，则说明 nums[i...j] 包含的 0 和 1 的个数相等
     * a + b == c，若 a == c，则 b == 0，其中 a、b、c 均代表连续子序列之和
     * 可使用累加和快速求出连续子序列之和，使用 map 保存累加和及其第一次出现的下标 i，当再次出现时，说明中间的 0 和 1 的个数相等
     * 以 [1,1,0,0] 为例说明该过程：
     * i    sum     map                 result
     * 0    1       (0,-1)(1,0)         0
     * 1    2       (0,-1)(1,0)(2,1)    0
     * 2    1       (0,-1)(1,0)(2,1)    1 再次出现，2-0 = 2
     * 3    0       (0,-1)(1,0)(2,1)    0 再次出现，3-(-1) = 4
     */
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        // 为了不漏过开始的 [0,1] 或 [1,0]
        map.put(0, -1);
        int sum = 0, result = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(sum)) {
                result = Math.max(result, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return result;
    }
}
