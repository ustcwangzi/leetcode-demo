package com.wz.other;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle.
 * The bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).
 * Return true if all the rectangles together form an exact cover of a rectangular region.
 *
 * Example 1:
 * @link ../../../../resource/PerfectRectangle1.jpg
 * Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * Output: true
 * Explanation: All 5 rectangles together form an exact cover of a rectangular region.
 *
 * Example 2:
 * @link ../../../../resource/PerfectRectangle2.jpg
 * Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 * Output: false
 * Explanation: Because there is a gap between the two rectangular regions.
 *
 * Example 3:
 * @link ../../../../resource/PerfectRectangle3.jpg
 * Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * Output: false
 * Explanation: Because two of the rectangles overlap with each other.
 *
 * Constraints:
 * 1. 1 <= rectangles.length <= 2 * 10^4
 * 2. rectangles[i].length == 4
 * 3. -10^5 <= xi, yi, ai, bi <= 10^5
 */
public class PerfectRectangle {
    public static void main(String[] args) {
        System.out.println(isRectangleCover(new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}}));
        System.out.println(isRectangleCover(new int[][]{{1, 1, 2, 3}, {1, 3, 2, 4}, {3, 1, 4, 2}, {3, 2, 4, 4}}));
        System.out.println(isRectangleCover(new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {2, 2, 4, 4}}));
    }

    /**
     * 最大矩形面积需要等于所有小矩形面积之和
     * 所有的矩形顶点，有且只有四个边角是只出现一次，剩下的顶点要么出现两次，要么出现四次
     * 即最终的四个边角出现奇数次、其他顶点出现偶数次
     * 因此可以使用 set 做去重，二次出现时就从 set 中移除，那么最终 set 中只会剩下四个边角
     */
    public static boolean isRectangleCover(int[][] rectangles) {
        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE, x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
        int area = 0;
        Set<String> set = new HashSet<>();
        for (int[] rectangle : rectangles) {
            area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);

            String s1 = rectangle[0] + " " + rectangle[1], s2 = rectangle[0] + " " + rectangle[3],
                    s3 = rectangle[2] + " " + rectangle[1], s4 = rectangle[2] + " " + rectangle[3];
            // 二次出现时从 set 中移除
            if (!set.add(s1)) {
                set.remove(s1);
            }
            if (!set.add(s2)) {
                set.remove(s2);
            }
            if (!set.add(s3)) {
                set.remove(s3);
            }
            if (!set.add(s4)) {
                set.remove(s4);
            }

            // 能够到达的最大范围
            x1 = Math.min(x1, rectangle[0]);
            y1 = Math.min(y1, rectangle[1]);
            x2 = Math.max(x2, rectangle[2]);
            y2 = Math.max(y2, rectangle[3]);
        }

        // 只能剩下四个边角
        if (!set.contains(x1 + " " + y1) || !set.contains(x1 + " " + y2) || !set.contains(x2 + " " + y1) || !set.contains(x2 + " " + y2) || set.size() != 4) {
            return false;
        }
        // 面积之和需要等于最大矩形面积
        return area == (x2 - x1) * (y2 - y1);
    }
}
