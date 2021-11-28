package com.wz.hash;

import java.util.*;

/**
 * There is a rectangular brick wall in front of you with n rows of bricks. The ith row has some number of bricks
 * each of the same height (i.e., one unit) but they can be of different widths. The total width of each row is the same.
 * Draw a vertical line from the top to the bottom and cross the least bricks. If your line goes through the edge of a brick,
 * then the brick is not considered as crossed. You cannot draw a line just along one of the two vertical edges of the wall,
 * in which case the line will obviously cross no bricks.
 * Given the 2D array wall that contains the information about the wall,
 * return the minimum number of crossed bricks after drawing such a vertical line.
 *
 * Example 1:
 * @link ../../../../resource/BrickWall.jpg
 * Input: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
 * Output: 2
 *
 * Example 2:
 * Input: wall = [[1],[1],[1]]
 * Output: 3
 *
 * Constraints:
 * 1. n == wall.length
 * 2. 1 <= n <= 10^4
 * 3. 1 <= wall[i].length <= 10^4
 * 4. 1 <= sum(wall[i].length) <= 2 * 10^4
 * 5. sum(wall[i]) is the same for each row i.
 * 6. 1 <= wall[i][j] <= 2^31 - 1
 */
public class BrickWall {
    public static void main(String[] args) {
        List<List<Integer>> wall = new LinkedList<>();
        wall.add(Arrays.asList(1, 2, 2, 1));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 2));
        wall.add(Arrays.asList(2, 4));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 1, 1));
        System.out.println(leastBricks(wall));
    }

    /**
     * 一个砖头墙壁，每一层由不同的长度的砖头组成，选一个地方从上往下把墙劈开，使得被劈开的砖头个数最少
     * 最坏的情况为每层都被破坏，选择从缝隙处劈开时不会破坏砖头，因此选择的应该是缝隙出现次数最多的地方
     * 使用 map 保存每个缝隙出现的位置及其出现次数，假设出现最多次数为 max，则说明 max 块砖不会破坏，其余层对应位置会被破坏，即 n-max
     */
    public static int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxFrequency = 0;
        for (List<Integer> cur : wall) {
            int sum = 0;
            for (int j = 0; j < cur.size() - 1; j++) {
                sum += cur.get(j);
                // 缝隙位置及其出现次数
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                // 更新最大次数
                maxFrequency = Math.max(maxFrequency, map.get(sum));
            }
        }

        return wall.size() - maxFrequency;
    }
}
