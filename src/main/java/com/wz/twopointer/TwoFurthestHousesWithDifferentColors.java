package com.wz.twopointer;

/**
 * There are n houses evenly lined up on the street, and each house is beautifully painted.
 * You are given a 0-indexed integer array colors of length n, where colors[i] represents the color of the ith house.
 * Return the maximum distance between two houses with different colors.
 * The distance between the ith and jth houses is abs(i - j), where abs(x) is the absolute value of x.
 *
 * Example 1:
 * @link ../../../../resource/TwoFurthestHousesWithDifferentColors1.jpg
 * Input: colors = [1,1,1,6,1,1,1]
 * Output: 3
 * Explanation: In the above image, color 1 is blue, and color 6 is red.
 * The furthest two houses with different colors are house 0 and house 3.
 * House 0 has color 1, and house 3 has color 6. The distance between them is abs(0 - 3) = 3.
 * Note that houses 3 and 6 can also produce the optimal answer.
 *
 * Example 2:
 * @link ../../../../resource/TwoFurthestHousesWithDifferentColors2.jpg
 * Input: colors = [1,8,3,8,3]
 * Output: 4
 * Explanation: In the above image, color 1 is blue, color 8 is yellow, and color 3 is green.
 * The furthest two houses with different colors are house 0 and house 4.
 * House 0 has color 1, and house 4 has color 3. The distance between them is abs(0 - 4) = 4.
 *
 * Example 3:
 * Input: colors = [0,1]
 * Output: 1
 * Explanation: The furthest two houses with different colors are house 0 and house 1.
 * House 0 has color 0, and house 1 has color 1. The distance between them is abs(0 - 1) = 1.
 *
 * Constraints:
 * 1. n == colors.length
 * 2. 2 <= n <= 100
 * 3. 0 <= colors[i] <= 100
 * 4. Test data are generated such that at least two houses have different colors.
 */
public class TwoFurthestHousesWithDifferentColors {
    public static void main(String[] args) {
        System.out.println(maxDistance(new int[]{1, 1, 1, 6, 1, 1, 1}));
        System.out.println(maxDistance(new int[]{1, 8, 3, 8, 3}));
    }

    /**
     * i 从左向右遍历，j 从右向左遍历，若 colors[i] != colors[j]，则更新最大距离，同时本次 j 遍历结束，因为其他结果不可能比它更大
     * 若当前结果已大于 n-1-i，则直接返回结果，因为其他结果不可能更大
     */
    public static int maxDistance(int[] colors) {
        int result = 0;
        for (int i = 0; i < colors.length - 1; i++) {
            for (int j = colors.length - 1; j > i; j--) {
                if (colors[i] != colors[j]) {
                    result = Math.max(result, j - i);
                    break;
                }
            }
            if (result > colors.length - 1 - i) {
                return result;
            }
        }
        return result;
    }
}
