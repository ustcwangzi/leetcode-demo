package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * For example, given the following tree:
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        root.right = new TreeNode(5, null, new TreeNode(6));
        flatten(root);
        TreeNode.traversalPreOrder(root);
    }

    /**
     * DFS
     * 找到最左子节点，然后把其父节点和右子节点断开，将左子结点连上父节点的右子节点上，再把原右子节点连到新右子节点的右子节点上，
     * 再回到上一父节点做相同操作，对于题目中给的例子，变换过程如下
     * 断开 4 和 3，将 3 作为 2 的右子节点，然后将 4 作为 3 的右子节点
     *      1
     *     / \
     *    2   5
     *     \   \
     *      3   6
     *       \
     *        4
     * 断开 5 和 2，将 2 作为 1 的右子节点，找到 2 的最右节点 4，然后将 5 作为 4 的右子节点
     *    1
     *     \
     *      2
     *       \
     *        3
     *         \
     *          4
     *           \
     *            5
     *             \
     *              6
     */
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        root.right = tmp;
    }
}
