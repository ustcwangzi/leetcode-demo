package com.wz.math;

/**
 * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner,
 * and (x2, y2) are the coordinates of its top-right corner.
 * Two rectangles overlap if the area of their intersection is positive.
 * To be clear, two rectangles that only touch at the corner or edges do not overlap.
 * Given two (axis-aligned) rectangles, return whether they overlap.
 *
 * Example 1:
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * Output: true
 *
 * Example 2:
 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * Output: false
 *
 * Notes:
 * Both rectangles rec1 and rec2 are lists of 4 integers.
 * All coordinates in rectangles will be between -10^9 and 10^9.
 */
public class RectangleOverlap {
    public static void main(String[] args) {
        System.out.println(isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
    }

    /**
     * 两个矩形重叠主要有这四种情况：
     * 1）两个矩形在矩形1的右上角重叠：
     *            ____________________x4,y4
     *           |                   |
     *    _______|______x2,y2        |
     *   |       |______|____________|
     *   |      x3,y3   |
     *   |______________|
     *  x1,y1
     * 满足的条件为：x1 < x4 && x3 < x2 && y1 < y4 && y3 < y2
     * 2）两个矩形在矩形1的左上角重叠：
     *    ___________________  x4,y4
     *   |                   |
     *   |            _______|____________x2,y2
     *   |___________|_______|           |
     * x3,y3         |                   |
     *               |___________________|
     *             x1,y1
     * 满足的条件为：x3 < x2 && x1 < x4 && y1 < y4 && y3 < y2
     * 3）两个矩形在矩形1的左下角重叠：
     *            ____________________x2,y2
     *           |                   |
     *    _______|______x4,y4        |
     *   |       |______|____________|
     *   |      x1,y1   |
     *   |______________|
     *  x3,y3
     * 满足的条件为：x3 < x2 && x1 < x4 && y3 < y2 && y1 < y4
     * 4）两个矩形在矩形1的右下角重叠：
     *    ___________________  x2,y2
     *   |                   |
     *   |            _______|____________x4,y4
     *   |___________|_______|           |
     * x1,y1         |                   |
     *               |___________________|
     *             x3,y3
     * 满足的条件为：x1 < x4 && x3 < x2 && y3 < y2 && y1 < y4
     *
     * 仔细观察可以发现，上面四种情况的满足条件其实都是相同的，只不过顺序调换了位置
     */
    public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return rec1[0] < rec2[2] && rec2[0] < rec1[2] && rec1[1] < rec2[3] && rec2[1] < rec1[3];
    }
}
