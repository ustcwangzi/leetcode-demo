package com.wz.math;

/**
 * We have n chips, where the position of the ith chip is position[i].
 * We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:
 * position[i] + 2 or position[i] - 2 with cost = 0.
 * position[i] + 1 or position[i] - 1 with cost = 1.
 * Return the minimum cost needed to move all the chips to the same position.
 *
 * Example 1:
 * Input: position = [1,2,3]
 * Output: 1
 * Explanation: First step: Move the chip at position 3 to position 1 with cost = 0.
 * Second step: Move the chip at position 2 to position 1 with cost = 1.
 * Total cost is 1.
 *
 * Example 2:
 * Input: position = [2,2,2,3,3]
 * Output: 2
 * Explanation: We can move the two chips at poistion 3 to position 2. Each move has cost = 1. The total cost = 2.
 */
public class MinimumCostToMoveChipsToTheSamePosition {
    public static void main(String[] args) {
        System.out.println(minCostToMoveChips(new int[]{1, 2, 3}));
        System.out.println(minCostToMoveChips(new int[]{2, 2, 2, 3, 3}));
    }

    /**
     * 1. 偶数位置转移到任意偶数位置代价为0，同理奇数位置转移到任意奇数位置代价也为0
     * 2. 简化筹码位置，将所有偶数集中到2X位置，所有奇数集中到2X+1（或2X-1）位置
     * 3. 要想代价最小，就是移动数量最小的集合
     * 4. 综合分析，发现实际上就是统计奇数和偶数的个数，取小者
     */
    public static int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for (int p : position) {
            if ((p & 1) == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return Math.min(odd, even);
    }
}
