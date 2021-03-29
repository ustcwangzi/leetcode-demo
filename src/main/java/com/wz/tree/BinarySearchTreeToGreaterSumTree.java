package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the
 * original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 *
 * Example 1:
 * @see ../../../../resource/ConvertBSTToGreaterTree.jpg
 * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 100].
 * 2. 0 <= Node.val <= 100
 * 3. All the values in the tree are unique.
 * 4. root is guaranteed to be a valid binary search tree.
 */
public class BinarySearchTreeToGreaterSumTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1, new TreeNode(0), new TreeNode(2, null, new TreeNode(3)));
        TreeNode right = new TreeNode(6, new TreeNode(5), new TreeNode(7, null, new TreeNode(8)));
        TreeNode root = new TreeNode(4, left, right);
        TreeNode.traversalPreOrder(new BinarySearchTreeToGreaterSumTree().bstToGst(root));
    }

    /**
     * 与 {@link ConvertBSTToGreaterTree} 一样，采用 右-根-左 的方式进行遍历
     */
    public TreeNode bstToGst(TreeNode root) {
        dfs(root);
        return root;
    }

    private int sum = 0;

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(root.right);
        sum += root.val;
        root.val = sum;
        dfs(root.left);
    }
}
