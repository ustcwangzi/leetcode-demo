package com.wz.math;

/**
 * A boomerang is a set of 3 points that are all distinct and not in a straight line.
 * Given a list of three points in the plane, return whether these points are a boomerang.
 *
 * Example 1:
 * Input: [[1,1],[2,3],[3,2]]
 * Output: true
 *
 * Example 2:
 * Input: [[1,1],[2,2],[3,3]]
 * Output: false
 *
 * Note:
 * points.length == 3
 * points[i].length == 2
 * 0 <= points[i][j] <= 100
 */
public class ValidBoomerang {
    public static void main(String[] args) {
        System.out.println(isBoomerang(new int[][]{{1, 1}, {2, 3}, {3, 2}}));
        System.out.println(isBoomerang(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
    }

    /**
     * 要判断三个点是否能构成回旋镖，一是三个点各不相同，二是不能在一条直线上，即二维数组中三对坐标两两值不相等，且任意两对坐标的斜率不能相等。
     * 在计算两点的斜率时，如果分母为0或者分子为0，即这两个点可能与x轴、y轴平行，需要返回false。另外算斜率要用double类型，不能用int类型。
     */
    public static boolean isBoomerang(int[][] points) {
        int x1 = points[0][0], x2 = points[1][0], x3 = points[2][0];
        int y1 = points[0][1], y2 = points[1][1], y3 = points[2][1];
        // 坐标两两不等
        if ((x1 == x2 && y1 == y2) || (x2 == x3 && y2 == y3) || (x1 == x3 && y1 == y3)) {
            return false;
        }
        // 不能和x轴、y轴平行
        if ((x1 == x2 && x2 == x3) || (y1 == y2 && y2 == y3)) {
            return false;
        }
        // 计算斜率
        double slope = (y2 - y1) / ((double) x2 - x1);
        double slope2 = (y3 - y2) / ((double) x3 - x2);
        return slope != slope2;
    }
}
