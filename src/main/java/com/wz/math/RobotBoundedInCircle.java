package com.wz.math;

/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degress to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 * Example 1:
 * Input: "GGLLGG"
 * Output: true
 * Explanation:
 * The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 *
 * Example 2:
 * Input: "GG"
 * Output: false
 * Explanation:
 * The robot moves north indefinitely.
 */
public class RobotBoundedInCircle {
    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGG"));
        System.out.println(isRobotBounded("GG"));
    }

    /**
     * 有circle条件：走一圈停在 (0,0)，或者最后方向变了
     * 创建一个长度为4的dirs二维数组，分别表示上，右，下，左四个方向坐标的增量，
     * 如果遇到R，往右走，i = i + 1；遇到L，往左走，i = i + 3。
     * 遍历结束的时候判断，如果机器人能回到原点（0，0）或者i > 0则说明可以回到原点，否则就不能。
     */
    public static boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, i = 0;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int j = 0; j < instructions.length(); j++) {
            if (instructions.charAt(j) == 'R') {
                i = (i + 1) % 4;
            } else if (instructions.charAt(j) == 'L') {
                i = (i + 3) % 4;
            } else {
                x += dirs[i][0];
                y += dirs[i][1];
            }
        }

        return x == 0 && y == 0 || i > 0;
    }
}
