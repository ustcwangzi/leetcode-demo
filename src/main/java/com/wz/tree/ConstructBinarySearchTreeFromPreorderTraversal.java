package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.Arrays;

/**
 * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
 * It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
 * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val,
 * and any descendant of Node.right has a value strictly greater than Node.val.
 * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.
 *
 * Example 1:
 *          8
 *       /     \
 *      5      10
 *     / \      \
 *    1   7     12
 * Input: preorder = [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 *
 * Constraints:
 * 1. 1 <= preorder.length <= 100
 * 2. 1 <= preorder[i] <= 10^8
 * 3. All the values of preorder are unique.
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    public static void main(String[] args) {
        TreeNode.traversalPreOrder(bstFromPreorder(new int[]{8, 5, 1, 7, 10, 12}));
    }

    /**
     * 与 {@link com.wz.array.ConstructBinaryTreeFromPreorderAndInorderTraversal} 类似
     * BST 的中序遍历是有序的，给出了先序遍历的结果，就相当于同时给了 先序遍历 和 总序遍历
     */
    public static TreeNode bstFromPreorder(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.parallelSort(inorder);
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootValue = preorder[preStart];
        // 根节点在左子树中的位置
        int rootIndexAtInOrder = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                rootIndexAtInOrder = i;
                break;
            }
        }

        // 左子树节点个数
        int leftLength = rootIndexAtInOrder - inStart;

        TreeNode root = new TreeNode(rootValue);
        root.left = buildTree(preorder, preStart + 1, preStart + leftLength, inorder, inStart, rootIndexAtInOrder - 1);
        root.right = buildTree(preorder, preStart + leftLength + 1, preEnd, inorder, rootIndexAtInOrder + 1, inEnd);
        return root;
    }
}
