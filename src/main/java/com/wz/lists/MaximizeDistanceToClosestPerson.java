package com.wz.lists;

/**
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 * There is at least one empty seat, and at least one person sitting.
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * Return that maximum distance to closest person.
 *
 * Constraints:
 * 2 <= seats.length <= 20000
 * seats contains only 0s or 1s, at least one 0, and at least one 1.
 *
 * Example 1:
 * Input: [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 *
 * Example 2:
 * Input: [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat, the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 */
public class MaximizeDistanceToClosestPerson {
    public static void main(String[] args) {
        int[] seats = new int[]{1, 0, 0, 0, 1, 0, 1};
        System.out.println(maxDistToClosest(seats));

        seats = new int[]{1, 0, 0, 0};
        System.out.println(maxDistToClosest(seats));

        seats = new int[]{0, 0, 0, 1};
        System.out.println(maxDistToClosest(seats));
    }

    /**
     * 双指针，左指针 start 开始是0，右指针是循环的index，右指针遇到1就计算与左指针的距离，计算完以后左指针变成现在的index的下一个位置
     * 如果alex坐到两个1中间，则最近的距离是那两个人的index差除以2，
     * 如果第一个和最后一个座位是空位0，则alex可以坐到这个空位上，使得此时的距离最大。最后对所有的距离取最大
     */
    public static int maxDistToClosest(int[] seats) {
        int start = 0, result = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (start == 0) {
                    // 第一个位置
                    result = Math.max(result, i - start);
                } else {
                    // 中间位置
                    result = Math.max(result, (i - start + 1) / 2);
                }
                start = i + 1;
            }
        }

        // 最后一个位置
        return Math.max(result, seats.length - start);
    }
}
