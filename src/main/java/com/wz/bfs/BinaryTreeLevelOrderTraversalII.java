package com.wz.bfs;

import com.wz.common.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, new TreeNode(9), right);
        System.out.println(levelOrderBottom(root));
    }

    /**
     * 与 {@link BinaryTreeLevelOrderTraversal} 类似
     * 只是每次将层次遍历的结果加到结果集的头部
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        Deque<List<Integer>> deque = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelResult = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelResult.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            deque.addFirst(levelResult);
        }
        return new ArrayList<>(deque);
    }
}
