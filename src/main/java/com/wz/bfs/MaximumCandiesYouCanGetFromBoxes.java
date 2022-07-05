package com.wz.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have n boxes labeled from 0 to n - 1. You are given four arrays: status, candies, keys, and containedBoxes where:
 * - status[i] is 1 if the ith box is open and 0 if the ith box is closed,
 * - candies[i] is the number of candies in the ith box,
 * - keys[i] is a list of the labels of the boxes you can open after opening the ith box.
 * - containedBoxes[i] is a list of the boxes you found inside the ith box.
 * You are given an integer array initialBoxes that contains the labels of the boxes you initially have.
 * You can take all the candies in any open box and you can use the keys in it to open new boxes and you also can use the boxes you find in it.
 * Return the maximum number of candies you can get following the rules above.
 *
 * Example 1:
 * Input: status = [1,0,1,0], candies = [7,5,4,100], keys = [[],[],[1],[]], containedBoxes = [[1,2],[3],[],[]], initialBoxes = [0]
 * Output: 16
 * Explanation: You will be initially given box 0. You will find 7 candies in it and boxes 1 and 2.
 * Box 1 is closed and you do not have a key for it so you will open box 2. You will find 4 candies and a key to box 1 in box 2.
 * In box 1, you will find 5 candies and box 3 but you will not find a key to box 3 so box 3 will remain closed.
 * Total number of candies collected = 7 + 4 + 5 = 16 candy.
 *
 * Example 2:
 * Input: status = [1,0,0,0,0,0], candies = [1,1,1,1,1,1], keys = [[1,2,3,4,5],[],[],[],[],[]], containedBoxes = [[1,2,3,4,5],[],[],[],[],[]], initialBoxes = [0]
 * Output: 6
 * Explanation: You have initially box 0. Opening it you can find boxes 1,2,3,4 and 5 and their keys.
 * The total number of candies will be 6.
 *
 * Constraints:
 * 1. n == status.length == candies.length == keys.length == containedBoxes.length
 * 1 <= n <= 1000
 * status[i] is either 0 or 1.
 * 1 <= candies[i] <= 1000
 * 0 <= keys[i].length <= n
 * 0 <= keys[i][j] < n
 * All values of keys[i] are unique.
 * 0 <= containedBoxes[i].length <= n
 * 0 <= containedBoxes[i][j] < n
 * All values of containedBoxes[i] are unique.
 * Each box is contained in one box at most.
 * 0 <= initialBoxes.length <= n
 * 0 <= initialBoxes[i] < n
 */
public class MaximumCandiesYouCanGetFromBoxes {
    public static void main(String[] args) {
        System.out.println(maxCandies(new int[]{1, 0, 1, 0}, new int[]{7, 5, 4, 100}, new int[][]{{}, {}, {1}, {}},
                new int[][]{{1, 2}, {3}, {}, {}}, new int[]{0}));
    }

    /**
     * 维护两个列表，一个是待开启的box列表，一个是当前拥有的key的列表
     * 每次遍历这两个列表，如果遇到箱子能开启，就开启，并且把新开启箱子得到的key和box再放入对应的列表中
     * 如果遇到无法开启列表中任何一个箱子的情况，则表示已经没有箱子可开启
     */
    public static int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[status.length];
        for (int initialBox : initialBoxes) {
            queue.offer(initialBox);
            visited[initialBox] = true;
            for (int key : keys[initialBox]) {
                status[key] = 1;
            }
        }

        int result = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result += candies[cur];
            for (int key : keys[cur]) {
                status[key] = 1;
            }

            if (containedBoxes[cur].length <= 0) {
                continue;
            }

            for (int next : containedBoxes[cur]) {
                for (int key : keys[next]) {
                    status[key] = 1;
                }
            }
            for (int next : containedBoxes[cur]) {
                if (visited[next] || status[next] == 0) {
                    continue;
                }
                queue.offer(next);
                visited[next] = true;
            }
        }
        return result;
    }
}
