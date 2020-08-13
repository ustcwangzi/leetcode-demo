package com.wz.array;

import com.wz.common.TreeNode;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        TreeNode treeNode = buildTree(preOrder, inOrder);
        TreeNode.traversalPreOrder(treeNode);
    }

    /**
     * 先序遍历为：根左右，中序遍历为：左根右
     * 1. 先序遍历中第一个为整棵树的根节点
     * 2. 中序遍历中根节点是左子树右子树的分割点
     * 利用该规律找到中序遍历的分割点，逐步建立整棵树
     */
    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        return buildTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    private static TreeNode buildTree(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootValue = preOrder[preStart];
        int rootIndexAtInOrder = 0;
        // 找到根节点在左子树中的位置
        for (int i = inStart; i <= inEnd; i++) {
            if (rootValue == inOrder[i]) {
                rootIndexAtInOrder = i;
                break;
            }
        }

        // 左子树长度
        int leftLength = rootIndexAtInOrder - inStart;

        TreeNode root = new TreeNode(rootValue);
        // 建立左子树
        root.left = buildTree(preOrder, preStart + 1, preStart + leftLength, inOrder, inStart, rootIndexAtInOrder - 1);
        // 建立右子树
        root.right = buildTree(preOrder, preStart + leftLength + 1, preEnd, inOrder, rootIndexAtInOrder + 1, inEnd);
        return root;
    }
}
