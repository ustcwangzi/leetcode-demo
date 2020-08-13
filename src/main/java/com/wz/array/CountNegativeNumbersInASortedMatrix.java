package com.wz.array;

/**
 * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
 * Return the number of negative numbers in grid.
 *
 * Example 1:
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 *
 * Example 2:
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 *
 * Example 3:
 * Input: grid = [[1,-1],[-1,-1]]
 * Output: 3
 *
 * Example 4:
 * Input: grid = [[-1]]
 * Output: 1
 */
public class CountNegativeNumbersInASortedMatrix {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}
        };
        System.out.println(countNegatives(grid));

        grid = new int[][]{
                {3, 2}, {1, 0}
        };
        System.out.println(countNegatives(grid));

        grid = new int[][]{
                {1, -1}, {-1, -1}
        };
        System.out.println(countNegatives(grid));

        grid = new int[][]{
                {-1}
        };
        System.out.println(countNegatives(grid));
    }

    /**
     * 用二分查找找到每一行第一个负数下标 index，那么本行的负数个数就是 n - index
     */
    public static int countNegatives(int[][] grid) {
        int n = grid[0].length;
        int result = 0;
        for (int[] array : grid) {
            int index = findFirstNegativeIndex(array);
            result += n - index;
        }
        return result;
    }

    private static int findFirstNegativeIndex(int[] array) {
        int left = 0, right = array.length - 1;
        if (array[right] >= 0) {
            return array.length;
        }
        if (array[left] < 0) {
            return left;
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
