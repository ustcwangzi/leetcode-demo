package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
 * and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example 1:
 *      1
 *    /   \
 *   2     3
 *  / \   /
 * 4   5 6
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 5 * 10^4].
 * 2. 0 <= Node.val <= 5 * 10^4
 * 3. The tree is guaranteed to be complete.
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        root.right = new TreeNode(3, new TreeNode(6), null);
        System.out.println(countNodes(root));
        System.out.println(countNodes2(root));
    }

    /**
     * 递归先序遍历，直接遍历每个节点
     */
    public static int countNodes(TreeNode root) {
        return root == null ? 0 : (1 + countNodes(root.left) + countNodes(root.right));
    }

    /**
     * 利用完全二叉树的条件，分别计算左子树和右子树的高度，如果两者相等，说明是满二叉树，结果是 2^h - 1
     * 否则不是满二叉树，节点个数为左子树的节点个数 加 右子树的节点个数 加 1
     */
    public static int countNodes2(TreeNode root) {
        int leftHeight = 0, rightHeight = 0;
        TreeNode left = root, right = root;
        while (left != null) {
            leftHeight++;
            left = left.left;
        }
        while (right != null) {
            rightHeight++;
            right = right.right;
        }

        if (leftHeight == rightHeight) {
            return (int) Math.pow(2, leftHeight) - 1;
        }
        return 1 + countNodes2(root.left) + countNodes2(root.right);
    }
}
