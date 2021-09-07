package com.wz.other;

import java.util.TreeSet;

/**
 * There is an exam room with n seats in a single row labeled from 0 to n - 1.
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.
 * If there are multiple such seats, they sit in the seat with the lowest number. If no one is in the room, then the student sits at seat number 0.
 * Design a class that simulates the mentioned exam room.
 * Implement the ExamRoom class:
 * 1. ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
 * 2. int seat() Returns the label of the seat at which the next student will set.
 * 3. void leave(int p) Indicates that the student sitting at seat p will leave the room. It is guaranteed that there will be a student sitting at seat p.
 *
 * Example 1:
 * Input
 * ["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
 * [[10], [], [], [], [], [4], []]
 * Output
 * [null, 0, 9, 4, 2, null, 5]
 * Explanation
 * ExamRoom examRoom = new ExamRoom(10);
 * examRoom.seat(); // return 0, no one is in the room, then the student sits at seat number 0.
 * examRoom.seat(); // return 9, the student sits at the last seat number 9.
 * examRoom.seat(); // return 4, the student sits at the last seat number 4.
 * examRoom.seat(); // return 2, the student sits at the last seat number 2.
 * examRoom.leave(4);
 * examRoom.seat(); // return 5, the student sits at the last seat number 5.
 *
 * Constraints:
 * 1. 1 <= n <= 10^9
 * 2. It is guaranteed that there is a student sitting at seat p.
 * 3. At most 10^4 calls will be made to seat and leave.
 */
public class ExamRoom {
    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        System.out.println(examRoom.seat());
        System.out.println(examRoom.seat());
        System.out.println(examRoom.seat());
        System.out.println(examRoom.seat());
        examRoom.leave(4);
        System.out.println(examRoom.seat());
    }

    private final TreeSet<Integer> set;
    private final int n;

    public ExamRoom(int n) {
        this.n = n;
        this.set = new TreeSet<>();
    }

    /**
     * 有个考场，每个考生入座的时候都要尽可能的跟左右两边的人距离保持最大，当最大距离相同的时候，考生坐在座位编号较小的那个位置
     * 可以使用数组表示所有座位，0 代表没人，1 代表有人，然后遍历数组，找到连续 0 最多的中间位置进行入座，每次都需要大量遍历，会 TLE
     * 改进就是只关心有人坐的位置，找到最大位置进行入座，使用 TreeSet 保存 1 的位置，因为它是有序的，计算位置距离时比较方便
     */
    public int seat() {
        if (set.isEmpty()) {
            set.add(0);
            return 0;
        }

        int maxDistance = set.first(), seatId = 0;
        int pre = maxDistance;
        // 计算两个位置直接的距离，获取最大距离
        for (int cur : set) {
            int curDistance = (cur - pre) / 2;
            if (maxDistance < curDistance) {
                maxDistance = curDistance;
                seatId = (cur + pre) / 2;
            }
            pre = cur;
        }
        // 判断是不是可以坐在最后一个位置
        if (n - 1 - pre > maxDistance) {
            seatId = n - 1;
        }
        set.add(seatId);
        return seatId;
    }

    public void leave(int p) {
        set.remove(p);
    }
}
