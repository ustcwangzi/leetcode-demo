package com.wz.heap;

import java.util.PriorityQueue;

/**
 * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
 * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
 * While moving from building i to building i+1 (0-indexed),
 * If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
 * If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
 * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
 *
 * Example 1:
 * Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 * Output: 4
 * Explanation: Starting at building 0, you can follow these steps:
 * - Go to building 1 without using ladders nor bricks since 4 >= 2.
 * - Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
 * - Go to building 3 without using ladders nor bricks since 7 >= 6.
 * - Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
 * It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
 *
 * Constraints:
 * 1. 1 <= heights.length <= 105
 * 2. 1 <= heights[i] <= 106
 * 3. 0 <= bricks <= 109
 * 4. 0 <= ladders <= heights.length
 */
public class FurthestBuildingYouCanReach {
    public static void main(String[] args) {
        System.out.println(furthestBuilding(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1));
    }

    /**
     * 小根堆
     * 梯子可以忽略高度，优先使用梯子，当梯子用完时，将之前使用梯子的差值最小的改用砖块，因为替换最小的，所以使用小根堆存储差值
     */
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < heights.length - 1; i++) {
            if (heights[i + 1] <= heights[i]) {
                continue;
            }
            int diff = heights[i + 1] - heights[i];
            queue.add(diff);
            // 梯子已用完
            if (queue.size() > ladders) {
                bricks -= queue.poll();
                if (bricks < 0) {
                    return i;
                }
            }
        }
        return heights.length - 1;
    }
}
