package com.wz.binarysearch;

import java.util.Arrays;

/**
 * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
 * Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].
 * You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
 * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
 *
 * Example 1:
 * @link ../../../../resource/FindPeakElementII1.jpg
 * Input: mat = [[1,4],[3,2]]
 * Output: [0,1]
 * Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
 *
 * Example 2:
 * @link ../../../../resource/FindPeakElementII2.jpg
 * Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * Output: [1,1]
 * Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
 *
 * Constraints:
 * 1. m == mat.length
 * 2. n == mat[i].length
 * 3. 1 <= m, n <= 500
 * 4. 1 <= mat[i][j] <= 105
 * 5. No two adjacent cells are equal.
 */
public class FindPeakElementII {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {10, 20, 15},
                {21, 30, 14},
                {7, 16, 32}
        };
        System.out.println(Arrays.toString(findPeakGrid(mat)));
    }

    /**
     * 二分查找，与 {@link com.wz.array.FindPeakElement} 类似
     * 只是在进行行下标的判断时，需要先查找到最大元素所在列，然后再该列上进行二分查找
     */
    public static int[] findPeakGrid(int[][] mat) {
        int m = mat.length, left = 0, right = m - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int col = maxCol(mat[mid]);
            if ((mid == 0 || mat[mid - 1][col] <= mat[mid][col]) && (mid == m - 1 || mat[mid + 1][col] <= mat[mid][col])) {
                return new int[]{mid, col};
            }
            if (mid > 0 && mat[mid - 1][col] >= mat[mid][col]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{};
    }

    private static int maxCol(int[] array) {
        int maxValue = array[0], maxCol = 0;
        for (int i = 1; i < array.length; i++) {
            if (maxValue < array[i]) {
                maxValue = array[i];
                maxCol = i;
            }
        }
        return maxCol;
    }
}
