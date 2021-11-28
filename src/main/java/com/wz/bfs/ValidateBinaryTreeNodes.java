package com.wz.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
 * return true if and only if all the given nodes form exactly one valid binary tree.
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 *
 * Example 1:
 * @link ../../../../resource/ValidateBinaryTreeNodes1.jpg
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * Output: true
 *
 * Example 2:
 * @link ../../../../resource/ValidateBinaryTreeNodes2.jpg
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * Output: false
 *
 * Example 3:
 * @link ../../../../resource/ValidateBinaryTreeNodes3.jpg
 * Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * Output: false
 *
 * Example 4:
 * @link ../../../../resource/ValidateBinaryTreeNodes4.jpg
 * Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
 * Output: false
 *
 * Constraints:
 * 1. 1 <= n <= 10^4
 * 2. leftChild.length == rightChild.length == n
 * 3. -1 <= leftChild[i], rightChild[i] <= n - 1
 */
public class ValidateBinaryTreeNodes {
    public static void main(String[] args) {
        int[] leftChild = new int[]{1, -1, 3, -1}, rightChild = new int[]{2, -1, -1, -1};
        System.out.println(validateBinaryTreeNodes(4, leftChild, rightChild));

        leftChild = new int[]{1, -1, 3, -1};
        rightChild = new int[]{2, 3, -1, -1};
        System.out.println(validateBinaryTreeNodes(4, leftChild, rightChild));

        leftChild = new int[]{1, -1, -1, 4, -1, -1};
        rightChild = new int[]{2, -1, -1, 5, -1, -1};
        System.out.println(validateBinaryTreeNodes(6, leftChild, rightChild));
    }

    /**
     * BFS
     * 首先使用 inDegree 记录每个节点的入度，若某个节点入度大于1，则直接返回false，同时入度为 0 的节点只能有一个，并且是根节点
     * 然后从根节点开始进行层次遍历，遍历时记录遍历的节点个数 count，若 count 不等于 n，说明无法完全遍历，不是二叉树
     */
    public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                inDegree[leftChild[i]]++;
            }
            if (rightChild[i] != -1) {
                inDegree[rightChild[i]]++;
            }
        }

        int root = -1, count = 0;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] > 1) {
                return false;
            }
            if (inDegree[i] == 0) {
                root = i;
                count++;
            }
        }
        // 有且只能存在一个根节点
        if (root == -1 || count > 1) {
            return false;
        }

        count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            if (leftChild[cur] != -1) {
                queue.offer(leftChild[cur]);
            }
            if (rightChild[cur] != -1) {
                queue.offer(rightChild[cur]);
            }
        }
        return count == n;
    }
}
