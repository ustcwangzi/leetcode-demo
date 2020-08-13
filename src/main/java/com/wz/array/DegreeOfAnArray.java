package com.wz.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a non-empty array of non-negative integers nums,
 * the degree of this array is defined as the maximum frequency of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 *
 * Example 1:
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 *
 * Example 2:
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 */
public class DegreeOfAnArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 3, 1};
        System.out.println(findShortestSubArray(nums));

        nums = new int[]{1, 2, 2, 3, 1, 4, 2};
        System.out.println(findShortestSubArray(nums));
    }

    /**
     * 数组的度是指数组中出现次数最多元素的个数。寻找最小连续子数组，使得子数组的度与原数组的度相同。返回子数组的长度。
     * 也就是要找到出现频次最多的几个元素，哪个距离最近
     * 用Map<Integer, List<Integer>>来保存元素及其出现的位置，同时记录最大频次
     * 遍历map，对于出现频次最大的元素，计算最小距离
     */
    public static int findShortestSubArray(int[] nums) {
        // key:num, value:indexs
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        // 最大频次
        int maxFrequency = 0;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexList = indexMap.get(nums[i]);
            if (indexList == null) {
                indexList = new ArrayList<>();
                indexMap.put(nums[i], indexList);
            }
            indexList.add(i);
            maxFrequency = Math.max(maxFrequency, indexList.size());
        }

        int result = nums.length;
        for (Map.Entry<Integer, List<Integer>> entry : indexMap.entrySet()) {
            if (entry.getValue().size() == maxFrequency) {
                // 计算最小距离
                result = Math.min(result, entry.getValue().get(entry.getValue().size() - 1) - entry.getValue().get(0) + 1);
            }
        }

        return result;
    }
}
