package com.wz.binarysearch;

import java.util.Arrays;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 * Constraints:
 * 1. 1 <= piles.length <= 10^4
 * 2. piles.length <= h <= 10^9
 * 3. 1 <= piles[i] <= 10^9
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] piles = new int[]{30, 11, 23, 4, 20};
        System.out.println(minEatingSpeed(piles, 5));
        System.out.println(minEatingSpeed(piles, 6));
    }

    /**
     * 二分查找
     * 在 h 时间内吃完所有香蕉，一个小时内不能换堆，求出最小的吃速
     * 吃速最小是 1，最大是每次吃一堆，也就是所有堆中最大的香蕉个数，因此吃速范围为 [1...max{piles}]
     * 使用二分查找来求解，对于每个 mid，求出吃完所有香蕉所需时间 cost，若 cost 大于 h，则说明时间不够，将 left 赋值为 mid+1
     * 否则说明时间是符合要求的，将 right 赋值为 mid，缩小范围
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = Arrays.stream(piles).max().getAsInt();
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 吃完所有香蕉所需时间
            int cost = 0;
            for (int pile : piles) {
                cost += pile % mid == 0 ? pile / mid : pile / mid + 1;
            }
            if (cost <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
