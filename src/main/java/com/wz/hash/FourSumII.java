package com.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
 * 1. 0 <= i, j, k, l < n
 * 2. nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * Example 1:
 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * Output: 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * Example 2:
 * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * Output: 1
 *
 * Constraints:
 * 1. n == nums1.length
 * 2. n == nums2.length
 * 3. n == nums3.length
 * 4. n == nums4.length
 * 5. 1 <= n <= 200
 * 6. -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 */
public class FourSumII {
    public static void main(String[] args) {
        System.out.println(fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
    }

    /**
     * 是对 {@link com.wz.array.TwoSum} 的延伸
     * 使用两个 Map 分别记录 AB 和 CD 两两元素之和及其出现次数，然后遍历其中一个 Map，在另一个 Map 中寻找相反数的出现次数
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count1 = nums1[i] + nums2[j], count2 = nums3[i] + nums4[j];
                map1.put(count1, map1.getOrDefault(count1, 0) + 1);
                map2.put(count2, map2.getOrDefault(count2, 0) + 1);
            }
        }

        int result = 0;
        // 在 map2 中寻找 map1.value 出现次数
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            result += entry.getValue() * map2.getOrDefault(-entry.getKey(), 0);
        }
        return result;
    }
}
