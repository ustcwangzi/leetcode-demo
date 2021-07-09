package com.wz.sort;

import java.util.Arrays;

/**
 * You are playing a video game where you are defending your city from a group of n monsters.
 * You are given a 0-indexed integer array dist of size n, where dist[i] is the initial distance in meters of the ith monster from the city.
 * The monsters walk toward the city at a constant speed. The speed of each monster is given to you in an integer array speed of size n,
 * where speed[i] is the speed of the ith monster in meters per minute.
 * The monsters start moving at minute 0. You have a weapon that you can choose to use at the start of every minute, including minute 0.
 * You cannot use the weapon in the middle of a minute. The weapon can eliminate any monster that is still alive.
 * You lose when any monster reaches your city. If a monster reaches the city exactly at the start of a minute,
 * it counts as a loss, and the game ends before you can use your weapon in that minute.
 * Return the maximum number of monsters that you can eliminate before you lose, or n if you can eliminate all the monsters before they reach the city.
 *
 * Example 1:
 * Input: dist = [1,3,4], speed = [1,1,1]
 * Output: 3
 * Explanation:
 * At the start of minute 0, the distances of the monsters are [1,3,4], you eliminate the first monster.
 * At the start of minute 1, the distances of the monsters are [X,2,3], you don't do anything.
 * At the start of minute 2, the distances of the monsters are [X,1,2], you eliminate the second monster.
 * At the start of minute 3, the distances of the monsters are [X,X,1], you eliminate the third monster.
 * All 3 monsters can be eliminated.
 *
 * Example 2:
 * Input: dist = [1,1,2,3], speed = [1,1,1,1]
 * Output: 1
 * Explanation:
 * At the start of minute 0, the distances of the monsters are [1,1,2,3], you eliminate the first monster.
 * At the start of minute 1, the distances of the monsters are [X,0,1,2], so you lose.
 * You can only eliminate 1 monster.
 *
 * Example 3:
 * Input: dist = [3,2,4], speed = [5,3,2]
 * Output: 1
 * Explanation:
 * At the start of minute 0, the distances of the monsters are [3,2,4], you eliminate the first monster.
 * At the start of minute 1, the distances of the monsters are [X,0,2], so you lose.
 * You can only eliminate 1 monster.
 *
 * Constraints:
 * 1. n == dist.length == speed.length
 * 2. 1 <= n <= 10^5
 * 3. 1 <= dist[i], speed[i] <= 10^5
 */
public class EliminateMaximumNumberOfMonsters {
    public static void main(String[] args) {
        System.out.println(eliminateMaximum(new int[]{1, 3, 4}, new int[]{1, 1, 1}));
        System.out.println(eliminateMaximum(new int[]{3, 2, 4}, new int[]{5, 3, 2}));
    }

    /**
     * 排序 + 贪心
     * dist 为怪物离城市的距离，speed 为怪物的速度，每次可以消灭一个怪物，求在游戏结束前可以消灭多少怪物
     * 先计算每个怪物到达城市的时间记录在 cost 数组中，然后对 cost 进行排序，花费时间 time 和当前消灭的怪物 index 初始化为 0
     * 当 time 小于怪物到达城市的时间时，消灭的怪物数+1，否则直接结束
     */
    public static int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        double[] cost = new double[n];
        for (int i = 0; i < n; i++) {
            cost[i] = (double) dist[i] / speed[i];
        }
        Arrays.parallelSort(cost);

        int time = 0, index = 0;
        while (index < n && time++ < cost[index]) {
            index++;
        }
        return index;
    }
}
