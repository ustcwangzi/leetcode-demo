package com.wz.design;

import java.util.Map;
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

    private final TreeMap<Integer, Integer> map;

    public MyCalendarTwo2() {
        map = new TreeMap<>();
    }

    /**
     * 扫描线 + TreeMap
     * 把 start、end 当成独立的事件全部打散，按照时间排序，对 start 加一，对 end 减一，然后遍历 TreeMap，判断每个重叠区间个数
     * TreeMap 会自动排序，遍历时是按时间顺序的，最先遍历到的一定是一个起始时间，加上其映射值，一定是个正数，
     * 如果此时只有一个区间（即刚加进来的区间），那么先遍历到 start，cur += 1，然后遍历到 end，cur -= 1，最后 cur == 0，没有重叠
     * 举例说明，假设 TreeMap 中已经加入了一个区间 [3, 5)，就有下面的映射：
     * 3 -> 1
     * 5 -> -1
     * 此时加入区间 [3, 8)，映射为：
     * 3 -> 2
     * 5 -> -1
     * 8 -> -1
     * 最先遍历到 3，cur 为 2，即两个事件有重叠，是允许的，然后遍历5、8，分别减去1，最终又变成 0 了，始终 cur 没有超过 2，符合题意
     * 再加入一个新的区间 [1, 4)，那么此时的映射为：
     * 1 -> 1
     * 3 -> 2
     * 4 -> -1
     * 5 -> -1
     * 8 -> -1
     * 先遍历到 1，cur为1，然后遍历到 3，此时 cur 为 3，有三个事件重叠，这个区间不能加入，还原其 start 和 end 的操作，然后返回 false
     */
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int cur = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            cur += entry.getValue();
            if (cur > 2) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}
