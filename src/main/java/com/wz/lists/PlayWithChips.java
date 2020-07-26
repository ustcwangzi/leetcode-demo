package com.wz.lists;

/**
 * There are some chips, and the i-th chip is at position chips[i].
 * You can perform any of the two following types of moves any number of times (possibly zero) on any chip:
 * Move the i-th chip by 2 units to the left or to the right with a cost of 0.
 * Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
 * There can be two or more chips at the same position initially.
 * Return the minimum cost needed to move all the chips to the same position (any position).
 *
 * Example 1:
 * Input: chips = [1,2,3]
 * Output: 1
 * Explanation: Second chip will be moved to positon 3 with cost 1. First chip will be moved to position 3 with cost 0. Total cost is 1.
 *
 * Example 2:
 * Input: chips = [2,2,2,3,3]
 * Output: 2
 * Explanation: Both fourth and fifth chip will be moved to position two with cost 1. Total minimum cost will be 2.
 */
public class PlayWithChips {
    public static void main(String[] args) {
        int[] chips = new int[]{1, 2, 3};
        System.out.println(minCostToMoveChips(chips));

        chips = new int[]{2, 2, 2, 3, 3};
        System.out.println(minCostToMoveChips(chips));
    }

    /**
     * 给定芯片所处位置 chips[i], 芯片移动2个单位,成本为0; 芯片移动1个单位,成本为1. 获取将所有芯片移至同一位置(任何位置)所需的最低成本
     * 可以转换为：
     * 给了一个数组，其中元素都是大于等于1的正整数，可以对数组中的任意元素进行两种操作：
     * 将元素值加2或减2，成本为0；将元素值加1或减1，成本为1。这两种操作都可以进行多次，现在要将数组中的元素值全部变为一个值，求最低的成本
     * 这个问题本质上是计算数组中奇数和偶数的个数
     * 1. 如果数组元素全部为偶数，全变成2，成本为0
     * 2. 如果数组元素全部为奇数，全变成1，成本为0
     * 3. 如果奇数元素个数大于偶数元素个数，将偶数元素加1全变为奇数，成本是偶数元素的个数
     * 4. 如果奇数元素个数小于偶数元素个数，将奇数元素加1全变为偶数，成本是奇数元素的个数
     */
    public static int minCostToMoveChips(int[] chips) {
        int even = 0, odd = 0;
        for (int chip : chips) {
            if (chip % 2 == 0) {
                //偶数元素个数
                even++;
            } else {
                //奇数元素个数
                odd++;
            }
        }
        return Math.min(odd, even);
    }
}
