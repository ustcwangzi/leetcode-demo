package com.wz.binarysearch;

import java.util.Arrays;

/**
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 *
 * Example 1:
 * Input: houses = [1,2,3], heaters = [2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 *
 * Example 2:
 * Input: houses = [1,2,3,4], heaters = [1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 *
 * Example 3:
 * Input: houses = [1,5], heaters = [2]
 * Output: 3
 *
 * Constraints:
 * 1. 1 <= houses.length, heaters.length <= 3 * 10^4
 * 2. 1 <= houses[i], heaters[i] <= 10^9
 */
public class Heaters {
    public static void main(String[] args) {
        System.out.println(findRadius(new int[]{1, 2, 3}, new int[]{2}));
        System.out.println(findRadius(new int[]{1, 5}, new int[]{2}));
    }

    /**
     * 设计一个固定温暖半径的标准加热器，以加热所有房屋
     * 将房子、加热器都先排序，然后利用两层循环，外层遍历房子，内层遍历加热器，每次获取到房子，就去比较对应位置的左右加热器
     * 如果右边的加热器的位置值绝对值小于左边的，就继续向右比较，也就是取当前房子在左右加热器之间的最小半径
     * 然后将当前的最小半径和上一次比较获得的最小半径取最大值，因为要覆盖所有的房子
     */
    public static int findRadius(int[] houses, int[] heaters) {
        if (houses == null || heaters == null) {
            return 0;
        }
        Arrays.parallelSort(houses);
        Arrays.parallelSort(heaters);

        int result = Integer.MIN_VALUE;
        int i = 0, j = 0, m = houses.length, n = heaters.length;
        while (i < m) {
            // 取当前房子在左右加热器之间的最小半径
            while (j < n - 1 && Math.abs(heaters[j + 1] - houses[i]) <= Math.abs(heaters[j] - houses[i])) {
                j++;
            }
            result = Math.max(result, Math.abs(heaters[j] - houses[i]));
            i++;
        }
        return result;
    }
}
