package com.wz.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are assigned to put some amount of boxes onto one truck. You are given a 2D array boxTypes,
 * where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 * 1. numberOfBoxesi is the number of boxes of type i.
 * 2. numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck.
 * You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 * Return the maximum total number of units that can be put on the truck.
 *
 * Example 1:
 * Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 * Output: 8
 * Explanation: There are:
 * - 1 box of the first type that contains 3 units.
 * - 2 boxes of the second type that contain 2 units each.
 * - 3 boxes of the third type that contain 1 unit each.
 * You can take all the boxes of the first and second types, and one box of the third type.
 * The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
 *
 * Example 2:
 * Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 * Output: 91
 *
 * Constraints:
 * 1. 1 <= boxTypes.length <= 1000
 * 2. 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
 * 3. 1 <= truckSize <= 10^6
 */
public class MaximumUnitsOnTruck {
    public static void main(String[] args) {
        System.out.println(maximumUnits(new int[][]{{1, 3}, {2, 2,}, {3, 1}}, 4));
        System.out.println(maximumUnits(new int[][]{{5, 10}, {2, 5}, {4, 7}, {3, 9}}, 10));
    }

    /**
     * 按照 Unit 对数组进行排序，优先选择 Unit 更大的箱子
     */
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.parallelSort(boxTypes, Comparator.comparingInt(o -> o[1]));
        int result = 0;
        for (int i = boxTypes.length - 1; i >= 0 && truckSize > 0; i--) {
            int[] cur = boxTypes[i];
            if (truckSize >= cur[0]) {
                result += cur[0] * cur[1];
                truckSize -= cur[0];
            } else {
                result += truckSize * cur[1];
                truckSize = 0;
            }
        }
        return result;
    }
}
