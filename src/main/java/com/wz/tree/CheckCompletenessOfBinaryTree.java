package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, determine if it is a complete binary tree.
 *
 * In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example 1:
 *      1
 *    /   \
 *   2     3
 *  / \   /
 * 4   5 6
 * Input: root = [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 *
 * Example 2:
 *      1
 *    /   \
 *   2     3
 *  / \     \
 * 4   5     7
 * Input: root = [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 100].
 * 2. 1 <= Node.val <= 1000
 */
public class CheckCompletenessOfBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode right = new TreeNode(3, new TreeNode(6), null);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(isCompleteTree(root));

        right = new TreeNode(3, null, new TreeNode(7));
        root = new TreeNode(1, left, right);
        System.out.println(isCompleteTree(root));
    }

    /**
     * 层次遍历，完全二叉树的空节点只能在最后一层左侧，使用 last 记录是否遍历到最后一个节点
     * 如果已经遍历到最后一个节点，那么后面不允许再出现不为空的节点
     */
    public static boolean isCompleteTree(TreeNode root) {
        boolean last = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    last = true;
                } else {
                    // 已遍历到最后一个合法节点，后面不允许再出现不为空的节点
                    if (last) {
                        return false;
                    }
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }
        return true;
    }
}
