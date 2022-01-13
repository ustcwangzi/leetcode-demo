package com.wz.other;

/**
 * You want to water n plants in your garden with a watering can. The plants are arranged in a row and are labeled from 0 to n - 1
 * from left to right where the ith plant is located at x = i. There is a river at x = -1 that you can refill your watering can at.
 * Each plant needs a specific amount of water. You will water the plants in the following way:
 * 1. Water the plants in order from left to right.
 * 2. After watering the current plant, if you do not have enough water to completely water the next plant, return to the river to fully refill the watering can.
 * 3. You cannot refill the watering can early.
 * You are initially at the river (i.e., x = -1). It takes one step to move one unit on the x-axis.
 * Given a 0-indexed integer array plants of n integers, where plants[i] is the amount of water the ith plant needs,
 * and an integer capacity representing the watering can capacity, return the number of steps needed to water all the plants.
 *
 * Example 1:
 * Input: plants = [2,2,3,3], capacity = 5
 * Output: 14
 * Explanation: Start at the river with a full watering can:
 * - Walk to plant 0 (1 step) and water it. Watering can has 3 units of water.
 * - Walk to plant 1 (1 step) and water it. Watering can has 1 unit of water.
 * - Since you cannot completely water plant 2, walk back to the river to refill (2 steps).
 * - Walk to plant 2 (3 steps) and water it. Watering can has 2 units of water.
 * - Since you cannot completely water plant 3, walk back to the river to refill (3 steps).
 * - Walk to plant 3 (4 steps) and water it.
 * Steps needed = 1 + 1 + 2 + 3 + 3 + 4 = 14.
 *
 * Example 2:
 * Input: plants = [1,1,1,4,2,3], capacity = 4
 * Output: 30
 * Explanation: Start at the river with a full watering can:
 * - Water plants 0, 1, and 2 (3 steps). Return to river (3 steps).
 * - Water plant 3 (4 steps). Return to river (4 steps).
 * - Water plant 4 (5 steps). Return to river (5 steps).
 * - Water plant 5 (6 steps).
 * Steps needed = 3 + 3 + 4 + 4 + 5 + 5 + 6 = 30.
 *
 * Constraints:
 * 1. n == plants.length
 * 2. 1 <= n <= 1000
 * 3. 1 <= plants[i] <= 10^6
 * 4. max(plants[i]) <= capacity <= 10^9
 */
public class WateringPlants {
    public static void main(String[] args) {
        System.out.println(wateringPlants(new int[]{2, 2, 3, 3}, 5));
        System.out.println(wateringPlants(new int[]{1, 1, 1, 4, 2, 3}, 4));
    }

    /**
     * 按照题意，逐步计算即可，使用 cur 记录当前剩余水量，遍历 plants[]，若当前水量不够，则需要返回取水，走过的步数为 2*i（一来一回）
     * 然后从当前水量中扣去当前植物需要浇的水，同时移动到下一个位置
     */
    public static int wateringPlants(int[] plants, int capacity) {
        int result = 0, cur = capacity;
        for (int i = 0; i < plants.length; i++) {
            if (cur < plants[i]) {
                result += 2 * i;
                cur = capacity;
            }
            cur -= plants[i];
            result++;
        }
        return result;
    }
}
