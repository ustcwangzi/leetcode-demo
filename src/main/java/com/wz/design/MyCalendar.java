package com.wz.design;

import java.util.TreeMap;

/**
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 * Your class will have the method, book(int start, int end). Formally,
 * this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully
 * without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * Example 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 */
public class MyCalendar {
    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();
        System.out.println(calendar.book(10, 20));
        System.out.println(calendar.book(15, 25));
        System.out.println(calendar.book(20, 30));
    }

    private final TreeMap<Integer, Integer> map;

    public MyCalendar() {
        map = new TreeMap<>();
    }

    /**
     * 要求区间不可重叠，那么对于有序区间更容易查找是否发生重叠，因此使用TreeMap来保存start、end
     * 新加入的区间，先获取其左区间，和左区间的终止位置比较是否有重叠，然后获取其右区间，和右区间的起始位置比较是否有重叠
     */
    public boolean book(int start, int end) {
        // 和左区间比较
        Integer left = map.floorKey(start);
        if (left != null && map.get(left) > start) {
            return false;
        }

        // 和右区间比较
        Integer right = map.ceilingKey(start);
        if (right != null && right < end) {
            return false;
        }

        map.put(start, end);
        return true;
    }
}
