package com.wz.lists;

import com.wz.common.TreeNode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postOrder = new int[]{9, 15, 7, 20, 3};
        TreeNode treeNode = buildTree(inOrder, postOrder);
        TreeNode.traversalPreOrder(treeNode);
    }

    /**
     * 思路与{@link ConstructBinaryTreeFromPreorderAndInorderTraversal}类似
     * 中序遍历为：左根右，后序遍历为：左右根
     * 1. 后序遍历中最后一个为整棵树的根节点
     * 2. 中序遍历中根节点是左子树右子树的分割点
     * 利用该规律找到中序遍历的分割点，逐步建立整棵树
     */
    public static TreeNode buildTree(int[] inOrder, int[] postOrder) {
        return buildTree(inOrder, 0, inOrder.length - 1, postOrder, 0, postOrder.length - 1);
    }

    private static TreeNode buildTree(int[] inOrder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        int rootValue = postOrder[postEnd];
        int rootIndexAtInOrder = 0;
        // 找到分割点
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
        root.left = buildTree(inOrder, inStart, rootIndexAtInOrder - 1, postOrder, postStart, postStart + leftLength - 1);
        // 建立右子树
        root.right = buildTree(inOrder, rootIndexAtInOrder + 1, inEnd, postOrder, postStart + leftLength, postEnd - 1);
        return root;
    }
}
