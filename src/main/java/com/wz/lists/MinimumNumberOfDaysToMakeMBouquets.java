package com.wz.lists;

import java.util.Arrays;

/**
 * Given an integer array bloomDay, an integer m and an integer k.
 * We need to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
 * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
 * Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.
 *
 * Example 1:
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 * Explanation: Let's see what happened in the first three days. x means flower bloomed and _ means flower didn't bloom in the garden.
 * We need 3 bouquets each should contain 1 flower.
 * After day 1: [x, _, _, _, _]   // we can only make one bouquet.
 * After day 2: [x, _, _, _, x]   // we can only make two bouquets.
 * After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
 *
 * Example 2:
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 * Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers.
 * We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
 * Example 3:
 *
 * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * Output: 12
 * Explanation: We need 2 bouquets each should have 3 flowers.
 * Here's the garden after the 7 and 12 days:
 * After day 7: [x, x, x, x, _, x, x]
 * We can make one bouquet of the first three flowers that bloomed.
 * We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
 * After day 12: [x, x, x, x, x, x, x]
 * It is obvious that we can make two bouquets in different ways.
 */
public class MinimumNumberOfDaysToMakeMBouquets {
    public static void main(String[] args) {
        int[] bloomDay = new int[]{1, 10, 3, 10, 2};
        System.out.println(minDays(bloomDay, 3, 1));

        System.out.println(minDays(bloomDay, 3, 2));

        bloomDay = new int[]{7, 7, 7, 7, 12, 7, 7};
        System.out.println(minDays(bloomDay, 2, 3));
    }

    /**
     * 需要制作 m 束花，每个花束需要使用相邻的 k 朵花，花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 盛开，求制作 m 束花需要等待的最少天数
     * 首先需要的全部花为 m * k，若该值大于 n，则无论怎样都不能满足需求，直接返回 -1
     * 接下来答案区间是已知的，必在 [1, max{bloomDay[0...n]}] 区间中，因此可使用二分法解决
     * 对于每个 mid，查找满足连续 k 盛开花朵数可制作的花束个数 flowerCount，如果 flowerCount >= m，则满足要求，继续尝试更少天数
     * 否则，说明不满足要求，接着尝试更多的天数
     */
    public static int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        int left = 0, right = Arrays.stream(bloomDay).max().getAsInt();
        while (left <= right) {
            int mid = (left + right) / 2;
            // 花束个数、连续的盛开花朵数，每增加一个花束，盛开花朵数置0
            int flowerCount = 0, dayCount = 0;
            for (int day : bloomDay) {
                if (day <= mid) {
                    dayCount++;
                    // 满足连续的要求，可制作一个花束
                    if (dayCount == k) {
                        flowerCount++;
                        dayCount = 0;
                    }
                } else {
                    dayCount = 0;
                }
            }

            if (flowerCount >= m) {
                // 满足要求，尝试更少天数
                right = mid - 1;
            } else {
                // 不满足要求，尝试更多天数
                left = mid + 1;
            }
        }
        return left;
    }
}
