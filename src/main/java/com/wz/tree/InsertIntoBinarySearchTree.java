package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
 * Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 *
 * Example 1:
 *          4
 *        /   \
 *       2     7
 *      / \
 *     1   3
 *
 *         4
 *       /    \
 *      2      7
 *     / \    /
 *    1   3  5
 *
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 * Explanation: Another accepted tree is:
 *         5
 *       /   \
 *      2     7
 *     / \
 *    1   3
 *         \
 *          4
 *
 * Constraints:
 * 1. The number of nodes in the tree will be in the range [0, 10^4].
 * 2. -10^8 <= Node.val <= 10^8
 * 3. All the values Node.val are unique.
 * 4. -10^8 <= val <= 10^8
 * 5. It's guaranteed that val does not exist in the original BST.
 */
public class InsertIntoBinarySearchTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode root = new TreeNode(4, left, new TreeNode(7));
        TreeNode.traversalPreOrder(insertIntoBST(root, 5));
    }

    /**
     * 递归实现
     * 如果当前节点值大于 val，则将其插入左子树，否则将其插入右子树
     */
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    /**
     * 非递归实现
     */
    public static TreeNode insertIntoBST2(TreeNode root, int val) {
        TreeNode node = root, newNode = new TreeNode(val);
        if (node == null) {
            return newNode;
        }
        while (node != null) {
            if (node.val > val) {
                // 插入左子树
                if (node.left == null) {
                    node.left = newNode;
                    break;
                }
                node = node.left;
            } else {
                // 插入右子树
                if (node.right == null) {
                    node.right = newNode;
                    break;
                }
                node = node.right;
            }
        }
        return root;
    }
}
