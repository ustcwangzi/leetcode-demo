package com.wz.lists;

import java.util.Arrays;

/**
 * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts
 * where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly,
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided
 * in the arrays horizontalCuts and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 * Example 1:
 * @see ../../../../resource/MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts.jpg
 * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * Output: 4
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
 */
public class MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts {
    public static void main(String[] args) {
        int[] horizontalCuts = new int[]{1, 2, 4};
        int[] verticalCuts = new int[]{1, 3};
        System.out.println(maxArea(5, 4, horizontalCuts, verticalCuts));
    }

    /**
     * 找出切割线中 最大行间隔 和 最大列间隔，两者相乘就是最大矩形面积
     * 对 行列的切割线 进行排序，然后遍历求出 行列的切割间隔 的最大值，最后求行列最大值的乘积
     */
    public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.parallelSort(horizontalCuts);
        Arrays.parallelSort(verticalCuts);

        int hlen = horizontalCuts.length, vlen = verticalCuts.length;
        // h可能只切了一次
        int maxh = Math.max(horizontalCuts[0], h - horizontalCuts[hlen - 1]);
        // v可能只切了一次
        int maxv = Math.max(verticalCuts[0], w - verticalCuts[vlen - 1]);

        for (int i = 1; i < hlen; i++) {
            maxh = Math.max(maxh, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        for (int i = 1; i < vlen; i++) {
            maxv = Math.max(maxv, verticalCuts[i] - verticalCuts[i - 1]);
        }

        return (int) ((long) maxh * maxv % 1000000007);
    }
}
