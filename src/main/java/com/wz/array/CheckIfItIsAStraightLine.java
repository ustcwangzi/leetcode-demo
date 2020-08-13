package com.wz.array;

/**
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
 * Check if these points make a straight line in the XY plane.
 *
 * Example 1:
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 *
 * Example 2:
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 */
public class CheckIfItIsAStraightLine {

    public static void main(String[] args) {
        int[][] coordinates = new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}
        };
        System.out.println(checkStraightLine(coordinates));

        coordinates = new int[][]{
                {1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}
        };
        System.out.println(checkStraightLine(coordinates));

        coordinates = new int[][]{
                {0, 0}, {0, 1}, {0, -1}
        };
        System.out.println(checkStraightLine(coordinates));
    }

    /**
     * 都和第一个点进行求比较，斜率不用则不能组成一条线
     */
    public static boolean checkStraightLine(int[][] coordinates) {
        int dx = coordinates[1][0] - coordinates[0][0], dy = coordinates[1][1] - coordinates[0][1];
        for (int i = 1; i < coordinates.length; i++) {
            if ((coordinates[i][0] - coordinates[0][0]) * dy != dx * (coordinates[i][1] - coordinates[0][1])) {
                return false;
            }
        }
        return true;
    }
}
