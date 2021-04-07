package com.wz.array;

import java.util.Arrays;

/**
 * In universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket.
 * Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into
 * the baskets such that the minimum magnetic force between any two balls is maximum.
 * Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
 * Given the integer array position and the integer m. Return the required force.
 *
 * Example 1:
 * @see ../../../../resource/MagneticForceBetweenTwoBalls.jpg
 * Input: position = [1,2,3,4,7], m = 3
 * Output: 3
 * Explanation:
 * Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6].
 * The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
 *
 * Example 2:
 * Input: position = [5,4,3,2,1,1000000000], m = 2
 * Output: 999999999
 * Explanation: We can use baskets 1 and 1000000000.
 *
 * Constraints:
 * 1. n == position.length
 * 2. 2 <= n <= 10^5
 * 3. 1 <= position[i] <= 10^9
 * 4. All integers in position are distinct.
 * 5. 2 <= m <= position.length
 */
public class MagneticForceBetweenTwoBalls {
    public static void main(String[] args) {
        System.out.println(maxDistance(new int[]{1, 2, 3, 4, 7}, 3));
    }

    /**
     * 二分查找
     * 如果只考虑两个球，那么磁力最小为 1，最大为 positionMax - positionMin
     * 因此可以使用二分查找，针对每个 distance，判断是否能够将所有球放置完成
     */
    public static int maxDistance(int[] position, int m) {
        Arrays.parallelSort(position);

        // 磁力的最小、最大值
        int low = 1, high = position[position.length - 1] - position[0];
        int result = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(position, m, mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    /**
     * 当前 distance 下能否将 m 个球全部放置完成
     */
    private static boolean check(int[] position, int m, int distance) {
        // 记录上一个球的位置，同时第一个位置一定放球
        int pre = position[0];
        m--;

        for (int i = 1; i < position.length; i++) {
            if (position[i] - pre >= distance) {
                if (--m == 0) {
                    return true;
                }
                pre = position[i];
            }
        }

        return m == 0;
    }
}
