package com.wz.bfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 * Example 1:
 * @link ../../../../resource/CousinsInBinaryTree1.jpg
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 *
 * Example 2:
 * @link ../../../../resource/CousinsInBinaryTree2.jpg
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 *
 * Example 3:
 * @link ../../../../resource/CousinsInBinaryTree3.jpg
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 *
 * Constraints:
 * 1. The number of nodes in the tree will be between 2 and 100.
 * 2. Each node has a unique integer value from 1 to 100.
 */
public class CousinsInBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, null, new TreeNode(4));
        TreeNode right = new TreeNode(3, null, new TreeNode(5));
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(isCousins(root, 5, 4));
    }

    /**
     * BFS
     * 遍历每一层时记录 x 和 y 的父节点 xParent、yParent，然后判断两者是否相等即可，不同层时一定有一个为 null
     */
    public static boolean isCousins(TreeNode root, int x, int y) {
        if (x == root.val || y == root.val) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode xParent = null, yParent = null;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                    if (cur.left.val == x) {
                        xParent = cur;
                    }
                    if (cur.left.val == y) {
                        yParent = cur;
                    }
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    if (cur.right.val == x) {
                        xParent = cur;
                    }
                    if (cur.right.val == y) {
                        yParent = cur;
                    }
                }

                if (xParent != null && yParent != null && xParent != yParent) {
                    return true;
                }
            }
        }
        return false;
    }
}
