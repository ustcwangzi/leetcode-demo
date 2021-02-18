package com.wz.heap;

import java.util.PriorityQueue;

/**
 * Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 *
 * Constraints:
 * 1. n == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= n <= 300
 * 4. -10^9 <= matrix[i][j] <= 10^9
 * 5. All the rows and columns of matrix are guaranteed to be sorted in non-degreasing order.
 * 6. 1 <= k <= n^2
 */
public class KthSmallestElementInSortedMatrix {
    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8));
    }

    /**
     * 与 {@link KthLargestElementInArray} 类似
     * 只是 {@link KthLargestElementInArray} 使用小根堆，而本题使用大根堆
     * 以最小的 k 个元素建立大根堆，堆顶是这 k 个元素中最大的
     * 遍历数组，若堆中元素个数小于 k，则直接加入堆中，继续遍历下一个元素
     * 否则，若当前元素小于堆顶，则弹出堆顶，将当前元素加入堆中，最后直接返回堆顶元素即可
     */
    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> Integer.compare(o2, o1));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (queue.size() < k) {
                    queue.offer(matrix[i][j]);
                    continue;
                }
                if (queue.peek() > matrix[i][j]) {
                    queue.poll();
                    queue.offer(matrix[i][j]);
                }
            }
        }
        return queue.peek();
    }
}
