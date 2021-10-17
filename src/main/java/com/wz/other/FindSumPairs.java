package com.wz.other;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two integer arrays nums1 and nums2. You are tasked to implement a data structure that supports queries of two types:
 * Add a positive integer to an element of a given index in the array nums2.
 * Count the number of pairs (i, j) such that nums1[i] + nums2[j] equals a given value (0 <= i < nums1.length and 0 <= j < nums2.length).
 * Implement the FindSumPairs class:
 * 1. FindSumPairs(int[] nums1, int[] nums2) Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
 * 2. void add(int index, int val) Adds val to nums2[index], i.e., apply nums2[index] += val.
 * 3. int count(int tot) Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
 *
 * Example 1:
 * Input
 * ["FindSumPairs", "count", "add", "count", "count", "add", "add", "count"]
 * [[[1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]], [7], [3, 2], [8], [4], [0, 1], [1, 1], [7]]
 * Output
 * [null, 8, null, 2, 1, null, null, 11]
 *
 * Explanation
 * FindSumPairs findSumPairs = new FindSumPairs([1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]);
 * findSumPairs.count(7);  // return 8; pairs (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) make 2 + 5 and pairs (5,1), (5,5) make 3 + 4
 * findSumPairs.add(3, 2); // now nums2 = [1,4,5,4,5,4]
 * findSumPairs.count(8);  // return 2; pairs (5,2), (5,4) make 3 + 5
 * findSumPairs.count(4);  // return 1; pair (5,0) makes 3 + 1
 * findSumPairs.add(0, 1); // now nums2 = [2,4,5,4,5,4]
 * findSumPairs.add(1, 1); // now nums2 = [2,5,5,4,5,4]
 * findSumPairs.count(7);  // return 11; pairs (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) make 2 + 5 and pairs (5,3), (5,5) make 3 + 4
 *
 * Constraints:
 * 1. 1 <= nums1.length <= 1000
 * 2. 1 <= nums2.length <= 10^5
 * 3. 1 <= nums1[i] <= 10^9
 * 4. 1 <= nums2[i] <= 10^5
 * 5. 0 <= index < nums2.length
 * 6. 1 <= val <= 10^5
 * 7. 1 <= tot <= 10^9
 * 8. At most 1000 calls are made to add and count each.
 */
public class FindSumPairs {
    public static void main(String[] args) {
        FindSumPairs findSumPairs = new FindSumPairs(new int[]{1, 1, 2, 2, 2, 3}, new int[]{1, 4, 5, 2, 5, 4});
        System.out.println(findSumPairs.count(7));
        findSumPairs.add(3, 2);
        System.out.println(findSumPairs.count(8));
        System.out.println(findSumPairs.count(4));
        findSumPairs.add(0, 1);
        findSumPairs.add(1, 1);
        System.out.println(findSumPairs.count(7));
    }

    private final Map<Integer, Integer> map;
    private final int[] num1, nums2;

    /**
     * 使用 map 记录 nums2 中每个元素及其出现次数
     * add 时，将原值的出现次数减一，同时更新新值的出现次数
     * count 时，遍历 nums1，从 map 中查找 tot - num 的出现次数累加到结果中
     */
    public FindSumPairs(int[] nums1, int[] nums2) {
        this.map = new HashMap<>();
        this.num1 = nums1;
        this.nums2 = nums2;
        for (int num : nums2) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }

    public void add(int index, int val) {
        int old = nums2[index];
        map.put(old, map.get(old) - 1);
        nums2[index] += val;
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        int result = 0;
        for (int num : num1) {
            result += map.getOrDefault(tot - num, 0);
        }
        return result;
    }
}
