package com.wz.hash;

import java.util.Arrays;

/**
 * There are 8 prison cells in a row and each cell is either occupied or vacant.
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 * 1. If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * 2. Otherwise, it becomes vacant.
 * Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.
 * You are given an integer array cells where cells[i] == 1 if the ith cell is occupied and cells[i] == 0 if the ith cell is vacant, and you are given an integer n.
 * Return the state of the prison after n days (i.e., n such changes described above).
 *
 * Example 1:
 * Input: cells = [0,1,0,1,1,0,0,1], n = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation: The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 * Input: cells = [1,0,0,1,0,0,1,0], n = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 * Constraints:
 * 1. cells.length == 8
 * 2. cells[i] is either 0 or 1.
 * 3. 1 <= n <= 10^9
 */
public class PrisonCellsAfterNDays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7)));
    }

    /**
     * 若一个位置的左右两边的数字相同，则该位置的数字变为1，反之则变为0
     * 依次打印结果可以发现数组的状态是有规律的，以 14 作为一个周期，因此 n 等效于 (n-1)%14 + 1
     */
    public static int[] prisonAfterNDays(int[] cells, int n) {
        n = (n - 1) % 14 + 1;
        while (n-- > 0) {
            int[] cur = Arrays.copyOf(cells, cells.length);
            for (int i = 1; i < 7; i++) {
                cells[i] = cur[i - 1] == cur[i + 1] ? 1 : 0;
            }
            cells[0] = cells[7] = 0;
        }
        return cells;
    }
}
