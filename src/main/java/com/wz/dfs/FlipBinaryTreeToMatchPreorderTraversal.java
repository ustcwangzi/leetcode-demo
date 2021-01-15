package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree with N nodes, each node has a different value from {1, ..., N}.
 * A node in this binary tree can be flipped by swapping the left child and the right child of that node.
 * Consider the sequence of N values reported by a preorder traversal starting from the root. Call such a sequence of N values the voyage of the tree.
 * (Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)
 * Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.
 * If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.
 * If we cannot do so, then return the list [-1].
 *
 * Example 1:
 * @see ../../../../resource/FlipBinaryTreeToMatchPreorderTraversal1.jpg
 * Input: root = [1,2], voyage = [2,1]
 * Output: [-1]
 *
 * Example 2:
 * @see ../../../../resource/FlipBinaryTreeToMatchPreorderTraversal2.jpg
 * Input: root = [1,2,3], voyage = [1,3,2]
 * Output: [1]
 *
 * Note:
 * 1 <= N <= 100
 */
public class FlipBinaryTreeToMatchPreorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        int[] voyage = new int[]{1, 3, 2};
        System.out.println(flipMatchVoyage(root, voyage));
    }

    /**
     * DFS
     * 若当前节点为空，直接返回 true，若当前节点值不等于数组中的对应位置的值，直接返回 false，因为只能调换子节点位置，当前节点位置不会改变。
     * 此时若左子结点存在，且左子结点值不等于数组中对应位置的值，此时应该尝试进行翻转，先将当前节点值存入 result 中，
     * 然后先对右子节点调用 DFS，之后再对左子节点调用 DFS，这样就相当于完成了调换左右子节点的操作。
     * 否则就按原顺序分别对左右子节点调用 DFS 即可，若最终返回 true，则返回 result，否则返回只有 -1 的数组
     */
    public static List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> result = new LinkedList<>();
        int[] index = {0};
        if (dfs(root, voyage, index, result)) {
            return result;
        }
        result = new LinkedList<>();
        result.add(-1);
        return result;
    }

    private static boolean dfs(TreeNode root, int[] voyage, int[] index, List<Integer> result) {
        if (root == null) {
            return true;
        }
        if (root.val != voyage[index[0]]) {
            return false;
        }

        index[0]++;
        if (root.left != null && root.left.val != voyage[index[0]]) {
            result.add(root.val);
            return dfs(root.right, voyage, index, result) && dfs(root.left, voyage, index, result);
        }
        return dfs(root.left, voyage, index, result) && dfs(root.right, voyage, index, result);
    }
}
