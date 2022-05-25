package com.wz.design;

import java.util.Map;
import java.util.TreeMap;

/**
 * A k-booking happens when k events have some non-empty intersection (i.e., there is some time that is common to all k events.)
 * You are given some events [start, end), after each given event, return an integer k representing the maximum k-booking between all the previous events.
 * Implement the MyCalendarThree class:
 * - MyCalendarThree() Initializes the object.
 * - int book(int start, int end) Returns an integer k representing the largest integer such that there exists a k-booking in the calendar.
 *
 * Example 1:
 * Input
 * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, 1, 1, 2, 3, 3, 3]
 * Explanation
 * MyCalendarThree myCalendarThree = new MyCalendarThree();
 * myCalendarThree.book(10, 20); // return 1, The first event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
 * myCalendarThree.book(50, 60); // return 1, The second event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
 * myCalendarThree.book(10, 40); // return 2, The third event [10, 40) intersects the first event, and the maximum k-booking is a 2-booking.
 * myCalendarThree.book(5, 15); // return 3, The remaining events cause the maximum K-booking to be only a 3-booking.
 * myCalendarThree.book(5, 10); // return 3
 * myCalendarThree.book(25, 55); // return 3
 *
 * Constraints:
 * 1. 0 <= start < end <= 10^9
 * 2. At most 400 calls will be made to book.
 */
public class MyCalendarThree {
    public static void main(String[] args) {
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        System.out.println(myCalendarThree.book(10, 20));
        System.out.println(myCalendarThree.book(50, 60));
        System.out.println(myCalendarThree.book(10, 40));
        System.out.println(myCalendarThree.book(5, 15));
        System.out.println(myCalendarThree.book(5, 10));
        System.out.println(myCalendarThree.book(25, 55));
    }

    private final TreeMap<Integer, Integer> map;

    public MyCalendarThree() {
        map = new TreeMap<>();
    }

    /**
     * 扫描线 + TreeMap，是对 {@link MyCalendarTwo2} 的扩展，思路一致
     * 只是那题要求重叠区间不能超过两个，本题直接计算最大的重叠区间
     */
    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int cur = 0, result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            cur += entry.getValue();
            result = Math.max(result, cur);
        }
        return result;
    }
}
