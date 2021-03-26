package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.Stack;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.
 *
 * Example 1:
 *        4
 *      /   \
 *     2     6
 *    / \
 *   1   3
 * Input: root = [4,2,6,1,3]
 * Output: 1
 *
 * Example 2:
 *      1
 *    /   \
 *   0     48
 *        /  \
 *       12  49
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [2, 100].
 * 2. 0 <= Node.val <= 10^5
 */
public class MinimumDistanceBetweenBSTNodes {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode root = new TreeNode(4, left, new TreeNode(6));
        System.out.println(minDiffInBST(root));

        TreeNode right = new TreeNode(48, new TreeNode(12), new TreeNode(49));
        root = new TreeNode(1, new TreeNode(0), right);
        System.out.println(minDiffInBST(root));
    }

    /**
     * 非递归先序遍历
     * 与 {@link MinimumAbsoluteDifferenceInBST} 一样
     */
    public static int minDiffInBST(TreeNode root) {
        int min = Integer.MAX_VALUE, pre = Integer.MAX_VALUE;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node == null) {
                node = stack.pop();
                if (pre != Integer.MAX_VALUE) {
                    min = Math.min(min, node.val - pre);
                }
                pre = node.val;
                node = node.right;
            } else {
                stack.push(node);
                node = node.left;
            }
        }
        return min;
    }
}
