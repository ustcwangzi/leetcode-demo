package com.wz.bfs;

import com.wz.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, new TreeNode(9), right);
        System.out.println(zigzagLevelOrder(root));
    }

    /**
     * 与 {@link BinaryTreeLevelOrderTraversal} 类似
     * 只是在遍历时增加一个方向标志 forward，用以表示本次节点值加到层次遍历结果的尾部还是头部
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean forward = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> levelResult = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (forward) {
                    levelResult.add(cur.val);
                } else {
                    levelResult.addFirst(cur.val);
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            forward = !forward;
            result.add(new LinkedList<>(levelResult));
        }
        return result;
    }
}
