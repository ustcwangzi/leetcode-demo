package com.wz.other;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Design a data structure to find the frequency of a given value in a given subarray.
 * The frequency of a value in a subarray is the number of occurrences of that value in the subarray.
 * Implement the RangeFreqQuery class:
 * RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
 * 1. int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
 * 2. A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray
 *    that contains the elements of nums between indices left and right (inclusive).
 *
 * Example 1:
 * Input
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * Output
 * [null, 1, 2]
 * Explanation
 * RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
 * rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
 * rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.
 *
 * Constraints:
 * 1. 1 <= arr.length <= 10^5
 * 2. 1 <= arr[i], value <= 10^4
 * 3. 0 <= left <= right < arr.length
 * 4. At most 10^5 calls will be made to query
 */
public class RangeFreqQuery {
    public static void main(String[] args) {
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        System.out.println(rangeFreqQuery.query(1, 2, 4));
        System.out.println(rangeFreqQuery.query(0, 11, 33));
    }

    Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();

    /**
     * 使用 map 保存每个元素在 [0...i] 范围内出现次数，然后可以借鉴 preSum 的思路求出指定范围内的出现次数
     * value 在 [left...right] 的出现次数为 count[0...right] - count[0...left] + 1
     */
    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new TreeMap<>());
            map.get(arr[i]).put(i, map.get(arr[i]).size() + 1);
        }
    }

    public int query(int left, int right, int value) {
        if (!map.containsKey(value)) {
            return 0;
        }
        TreeMap<Integer, Integer> count = map.get(value);
        // 大于等于 left 的最小值、小于等于 right 的最大值
        Integer start = count.ceilingKey(left), end = count.floorKey(right);
        if (start == null || end == null) {
            return 0;
        }
        return count.get(end) - count.get(start) + 1;
    }
}
