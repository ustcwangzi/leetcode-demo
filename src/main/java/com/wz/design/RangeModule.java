package com.wz.design;

import java.util.Map;
import java.util.TreeMap;

/**
 * A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.
 *
 * A half-open interval [left, right) denotes all the real numbers x where left <= x < right.
 *
 * Implement the RangeModule class:
 * - RangeModule() Initializes the object of the data structure.
 * - void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
 * - boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
 * - void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).
 *
 * Example 1:
 * Input
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * Output
 * [null, null, null, true, false, true]
 * Explanation
 * RangeModule rangeModule = new RangeModule();
 * rangeModule.addRange(10, 20);
 * rangeModule.removeRange(14, 16);
 * rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
 * rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
 * rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)
 *
 * Constraints:
 * 1. 1 <= left < right <= 10^9
 * 2. At most 10^4 calls will be made to addRange, queryRange, and removeRange.
 */
public class RangeModule {
    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(10, 20);
        rangeModule.removeRange(14, 16);
        System.out.println(rangeModule.queryRange(10, 14));
        System.out.println(rangeModule.queryRange(13, 15));
        System.out.println(rangeModule.queryRange(16, 17));
    }

    TreeMap<Integer, Integer> map;

    public RangeModule() {
        map = new TreeMap<>();
    }

    /**
     * 使用 TreeMap 保存左右端点，加入时，判断左端点是否落在了已存在的区间中，若是则扩大左端点，右端点也是类似操作
     * 将扩大之后的左右端点加入 map 中，然后从 map 中移除 [left, right)，保证 map 中的区间不重叠
     */
    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> startEntry = map.floorEntry(left), endEntry = map.floorEntry(right);
        // 向左扩大
        if (startEntry != null && startEntry.getValue() >= left) {
            left = startEntry.getKey();
        }
        if (endEntry != null && endEntry.getValue() >= right) {
            right = endEntry.getValue();
        }
        map.put(left, right);
        map.subMap(left, false, right, true).clear();
    }

    /**
     * 从 map 中查询是否有命中的区间即可
     */
    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = map.floorEntry(left);
        return entry != null && entry.getValue() >= right;
    }

    /**
     * 分别查找左右端点落在的区间，然后将左右侧剩余的区间加入 map 中，然后从 map 中移除 [left, right)
     */
    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> startEntry = map.floorEntry(left), endEntry = map.floorEntry(right);
        // 剩余区间
        if (startEntry != null && startEntry.getValue() > left) {
            map.put(startEntry.getKey(), left);
        }
        if (endEntry != null && endEntry.getValue() > right) {
            map.put(right, endEntry.getValue());
        }
        map.subMap(left, true, right, false).clear();
    }
}
