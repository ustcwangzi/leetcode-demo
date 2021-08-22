package com.wz.other;

import java.util.Arrays;

/**
 * A web developer needs to know how to design a web page's size. So, given a specific rectangular web page’s area,
 * your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:
 * The area of the rectangular web page you designed must equal to the given target area.
 * The width W should not be larger than the length L, which means L >= W.
 * The difference between length L and width W should be as small as possible.
 * Return an array [L, W] where L and W are the length and width of the web page you designed in sequence.
 *
 * Example 1:
 * Input: area = 4
 * Output: [2,2]
 * Explanation: The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1].
 * But according to requirement 2, [1,4] is illegal; according to requirement 3,  [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width W is 2.
 *
 * Example 2:
 * Input: area = 122122
 * Output: [427,286]
 *
 * Constraints:
 * 1 <= area <= 10^7
 */
public class ConstructTheRectangle {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructRectangle(4)));
        System.out.println(Arrays.toString(constructRectangle(122122)));
    }

    /**
     * 根据面积来求出矩形的长和宽，要求长和宽的差距尽量的小，那么就是说越接近正方形越好，对面积开方，如果得到的不是整数，说明不是正方形。
     * 那么取最近的一个整数，看此时能不能整除，如果不行，就自减1，再看能否整除。最坏的情况就是面积是质数，最后减到了1，那么返回结果即可
     */
    public static int[] constructRectangle(int area) {
        int len = (int) Math.sqrt(area);
        while (area % len != 0) {
            len--;
        }
        return new int[]{area / len, len};
    }
}
