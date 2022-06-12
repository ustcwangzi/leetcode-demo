package com.wz.queue;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 * Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 * - lefti is the x coordinate of the left edge of the ith building.
 * - righti is the x coordinate of the right edge of the ith building.
 * - heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...].
 * Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list,
 * which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends.
 * Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline.
 * For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable;
 * the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 * Example 1:
 * @link ../../../../resource/TheSkylineProblem.jpg
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 *
 * Example 2:
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 *
 * Constraints:
 * 1. 1 <= buildings.length <= 10^4
 * 2. 0 <= lefti < righti <= 2^31 - 1
 * 3. 1 <= heighti <= 2^31 - 1
 * 4. buildings is sorted by lefti in non-decreasing order.
 */
public class TheSkylineProblem {
    public static void main(String[] args) {
        System.out.println(getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}}));
        System.out.println(getSkyline(new int[][]{{0, 2, 3}, {2, 5, 3}}));
    }

    /**
     * 就是要找到每次高度发生变化的点，同时相邻两个点的高度不能够相同
     * 将每个 build 拆分为左右两个顶点，左右顶点后续处理逻辑不同，因此左顶点的高度使用负数表示，然后按照横坐标进行排序
     * 再遍历这些顶点
     * - 遇到左顶点，将高度放入堆中
     * - 遇到右顶点，说明此 build 结束，将高度从堆中移除
     * 然后将此时堆顶和之前的高度做比较，不相等说明高度发生了变化，需要加入结果中
     *
     * 以 [[0,2,3],[2,5,3]] 为例说明这一过程
     * [[0,2,3],[2,5,3]] 拆分并排序之后为 [[0,-3],[2,-3],[2,3],[5,3]]
     * pre      0       3       3       3
     * height   [0,-3]  [2,-3]  [2,3]   [5,3]
     * queue    [0,3]   [0,3,3] [0,3]   [0]
     * result   [0,3]   [0,3]   [0,3]   [0,3],[5,3]
     */
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> heightList = new ArrayList<>();
        for (int[] build : buildings) {
            heightList.add(new int[]{build[0], -build[2]});
            heightList.add(new int[]{build[1], build[2]});
        }
        heightList.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });

        List<List<Integer>> result = new LinkedList<>();
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        queue.offer(0);
        int pre = 0;
        for (int[] height : heightList) {
            if (height[1] < 0) {
                queue.offer(-height[1]);
            } else {
                queue.remove(height[1]);
            }

            int top = queue.peek();
            if (top != pre) {
                result.add(Arrays.asList(height[0], top));
                pre = top;
            }
        }
        return result;
    }
}
