package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Return any binary tree that matches the given preorder and postorder traversals.
 * Values in the traversals pre and post are distinct positive integers.
 *
 * Example 1:
 * Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 *
 * Note:
 * 1. 1 <= pre.length == post.length <= 30
 * 2. pre[] and post[] are both permutations of 1, 2, ..., pre.length.
 * 3. It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public static void main(String[] args) {
        TreeNode.traversalPreOrder(constructFromPrePost(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1}));
    }

    /**
     * 与 {@link com.wz.array.ConstructBinaryTreeFromPreorderAndInorderTraversal} 类似
     * 先序遍历为：根左右，后序遍历为：左右根
     * 可以发现 先序遍历中根节点的下一个节点 就是 左子树的根节点，同时也是该左子树后序遍历的最后一个节点
     * 可利用该规律找到后序遍历的左右子树分割的，逐步建立整棵树
     * 以题目中的例子为例，刚开始时根节点是 1，下一个节点是 2，也就是左子树的根节点是 2
     * 找到 2 的后序遍历中的下标为 2，可以求出左子树长度为 3
     * 因此将先序遍历划分为 [2,4,5][3,6,7]，后序遍历划分为 [4,5,2][6,7,3]
     */
    public static TreeNode constructFromPrePost(int[] pre, int[] post) {
        return buildTree(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    private static TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == preEnd) {
            return root;
        }

        // 左子树的根节点
        int leftRootIndexAtPost = 0;
        for (int i = postStart; i <= postEnd; i++) {
            // 先序遍历中根节点的下一个节点
            if (post[i] == pre[preStart + 1]) {
                leftRootIndexAtPost = i;
                break;
            }
        }

        // 左子树长度
        int leftLength = leftRootIndexAtPost - postStart + 1;

        // 构建左右子树
        root.left = buildTree(pre, preStart + 1, preStart + leftLength, post, postStart, leftRootIndexAtPost);
        root.right = buildTree(pre, preStart + leftLength + 1, preEnd, post, leftRootIndexAtPost + 1, postEnd - 1);
        return root;
    }
}
