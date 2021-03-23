package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high,
 * trim the tree so that all its elements lies in [low, high]. Trimming the tree should not change the
 * relative structure of the elements that will remain in the tree (i.e., any node's descendant should remain a descendant).
 * It can be proven that there is a unique answer.
 * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
 *
 * Example 1:
 * @see ../../../../resource/TrimBinarySearchTree1.jpg
 * Input: root = [1,0,2], low = 1, high = 2
 * Output: [1,null,2]
 *
 * Example 2:
 * @see ../../../../resource/TrimBinarySearchTree2.jpg
 * Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 * Output: [3,2,null,1]
 *
 * Constraints:
 * 1. The number of nodes in the tree in the range [1, 10^4].
 * 2. 0 <= Node.val <= 10^4
 * 3. The value of each node in the tree is unique.
 * 4. root is guaranteed to be a valid binary search tree.
 * 5. 0 <= low <= high <= 10^4
 */
public class TrimBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(0), new TreeNode(2));
        TreeNode.traversalPreOrder(trimBST(root, 1, 2));

        System.out.println();

        TreeNode left = new TreeNode(0, null, new TreeNode(2, new TreeNode(1), null));
        root = new TreeNode(3, left, new TreeNode(4));
        TreeNode.traversalPreOrder(trimBST(root, 1, 3));
    }

    /**
     * 递归
     * 如果 root 的值比 low 小，说明 root 以及其所有左节点都不在这个区间内，向右边搜索
     * 如果 root 的值比 high 大，说明 root 以及其所有右节点都不在这个区间内，向左边搜索
     * 如果 root 正好在这个区间内，那么分别对它的左右子树进行裁剪
     */
    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }

        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
