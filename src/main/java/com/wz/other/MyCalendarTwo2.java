package com.wz.other;

import java.util.TreeMap;

/**
 * {@link MyCalendarTwo}
 */
public class MyCalendarTwo2 {
    public static void main(String[] args) {
        MyCalendarTwo2 myCalendarTwo = new MyCalendarTwo2();
        System.out.println(myCalendarTwo.book(10, 20));
        System.out.println(myCalendarTwo.book(50, 60));
        System.out.println(myCalendarTwo.book(10, 40));
        System.out.println(myCalendarTwo.book(5, 15));
        System.out.println(myCalendarTwo.book(5, 10));
        System.out.println(myCalendarTwo.book(25, 55));
    }

    private final TreeMap<Integer, Integer> map, overlap;

    public MyCalendarTwo2() {
        map = new TreeMap<>();
        overlap = new TreeMap<>();
    }

    /**
     * 使用两个 TreeMap，一个 TreeMap 来专门存重叠区间，另一个 TreeMap 来存完整的区间
     * 先遍历重叠区间，因为能在这里出现的区间，都已经是出现两次了，如果当前新的区间跟重叠区间有交集，说明三个事件重叠，直接返回 false
     * 否则遍历完整区间，如果有交集，那么应该算出重叠区间并且加入重叠区间中，最后将新区间加入完整区间中
     */
    public boolean book(int start, int end) {
        Integer left = overlap.floorKey(end - 1);
        if (left != null && overlap.get(left) > start) {
            return false;
        }

        left = map.floorKey(end - 1);
        while (left != null && map.get(left) > start) {
            overlap.put(Math.max(left, start), Math.min(map.get(left), end));
            start = Math.min(left, start);
            end = Math.max(map.get(left), end);
            map.remove(left);
            left = map.floorKey(end - 1);
        }
        map.put(start, end);
        return true;
    }
}
