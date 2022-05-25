package com.wz.design;

import java.util.Map;
import java.util.TreeMap;

/**
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
 * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 * Implement the MyCalendarTwo class:
 * 1. MyCalendarTwo() Initializes the calendar object.
 * 2. boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking.
 *    Otherwise, return false and do not add the event to the calendar.
 *
 * Example 1:
 * Input
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, true, true, true, false, true, true]
 * Explanation
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // return True, The event can be booked.
 * myCalendarTwo.book(50, 60); // return True, The event can be booked.
 * myCalendarTwo.book(10, 40); // return True, The event can be double booked.
 * myCalendarTwo.book(5, 15);  // return False, The event ca not be booked, because it would result in a triple booking.
 * myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
 * myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 * Constraints:
 * 1. 0 <= start < end <= 10^9
 * 2. At most 1000 calls will be made to book.
 */
public class MyCalendarTwo {
    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        System.out.println(myCalendarTwo.book(10, 20));
        System.out.println(myCalendarTwo.book(50, 60));
        System.out.println(myCalendarTwo.book(10, 40));
        System.out.println(myCalendarTwo.book(5, 15));
        System.out.println(myCalendarTwo.book(5, 10));
        System.out.println(myCalendarTwo.book(25, 55));
    }

    private final TreeMap<Integer, Integer> map;

    public MyCalendarTwo() {
        map = new TreeMap<>();
        // 保证 floorKey、floorEntry 不会获取到 null
        map.put(-1, 0);
    }

    /**
     * 是对 {@link MyCalendar} 的扩展，依然使用 TreeMap，每个区间加入时，判断 [left, right) 内是否存在出现 2 次以上的区间
     * 若没有，则将 start、end 加入 TreeMap 中，同时将 [start, end) 内的区间出现次数加一
     */
    public boolean book(int start, int end) {
        // subMap 获取的是 [left, right)
        for (Map.Entry<Integer, Integer> entry : map.subMap(map.floorKey(start), end).entrySet()) {
            if (entry.getValue() >= 2) {
                return false;
            }
        }

        map.put(start, map.floorEntry(start).getValue());
        map.put(end, map.floorEntry(end).getValue());

        for (Map.Entry<Integer, Integer> entry : map.subMap(start, end).entrySet()) {
            entry.setValue(entry.getValue() + 1);
        }
        return true;
    }
}
