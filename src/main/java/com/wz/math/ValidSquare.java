package com.wz.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 *
 * Example:
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 *
 * Note:
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * Input points have no order.
 */
public class ValidSquare {
    public static void main(String[] args) {
        System.out.println(validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{0, 1}));
    }

    /**
     * 正方形的四条边相等，两条对角线相等，满足这两个条件的四边形一定是正方形。
     * 只需要对四个点，两两之间算距离，如果计算出某两个点之间距离为0，说明两点重合了，直接返回 false，
     * 如果不为0，就将距离保存下来，最后如果只得到了两个不同的距离长度，那么就说明是正方形了
     */
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> set = new HashSet<>();
        int[][] matrix = new int[][]{p1, p2, p3, p4};
        for (int i = 0; i < 4; ++i) {
            for (int j = i + 1; j < 4; ++j) {
                int x1 = matrix[i][0], y1 = matrix[i][1], x2 = matrix[j][0], y2 = matrix[j][1];
                int dist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                if (dist == 0) return false;
                set.add(dist);
            }
        }
        return set.size() == 2;
    }
}
