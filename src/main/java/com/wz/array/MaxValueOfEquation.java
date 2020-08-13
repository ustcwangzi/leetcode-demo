package com.wz.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array points containing the coordinates of points on a 2D plane, sorted by the x-values,
 * where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.
 * Find the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.
 * It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.
 *
 * Example 1:
 * Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * Output: 4
 * Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4.
 * Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
 * No other pairs satisfy the condition, so we return the max of 4 and 1.
 *
 * Example 2:
 * Input: points = [[0,0],[3,0],[9,2]], k = 3
 * Output: 3
 * Explanation: Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
 */
public class MaxValueOfEquation {
    public static void main(String[] args) {
        int[][] points = new int[][]{
                {1, 3}, {2, 0}, {5, 10}, {6, -10}
        };
        System.out.println(findMaxValueOfEquation(points, 1));

        points = new int[][]{
                {0, 0}, {3, 0}, {9, 2}
        };
        System.out.println(findMaxValueOfEquation(points, 3));
    }

    /**
     * 因为题目保证数据 xi < xj，所以不等式变形为 (yi - xi) + xj + yj，就变成了找当前点之前横坐标差值不超过 k 的最大的 yi - xi
     * 使用单调队列，单调队列里每个 Node 存储两个数据： yi - xi 和 xi，队列里维护满足横坐标差值不超过 k 下 yi - xi 的最大值
     */
    public static int findMaxValueOfEquation(int[][] points, int k) {
        int result = Integer.MIN_VALUE;
        Deque<Node> deque = new LinkedList<>();
        for (int[] point : points) {
            Node node = new Node(point[1] - point[0], point[0]);
            // 把横坐标差值超过 k 出队，因为不可能和后面的坐标组成新的结果
            while (!deque.isEmpty() && node.x - deque.peekFirst().x > k) {
                deque.pollFirst();
            }
            if (!deque.isEmpty()) {
                result = Math.max(result, point[0] + point[1] + deque.peekFirst().diff);
            }
            // 把差值较小的出队，因为不可能组成更大的结果，只允许差值更大的 Node 入队
            while (!deque.isEmpty() && deque.peekLast().diff < node.diff) {
                deque.pollLast();
            }
            deque.addLast(node);
        }

        return result;
    }

    private static class Node {
        private int diff;
        private int x;

        public Node(int diff, int x) {
            this.diff = diff;
            this.x = x;
        }
    }
}
