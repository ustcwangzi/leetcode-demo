package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the
 * original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 * 1. The left subtree of a node contains only nodes with keys less than the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than the node's key.
 * 3. Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 * @link ../../../../resource/ConvertBSTToGreaterTree.jpg
 * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 10^4].
 * 2. -10^4 <= Node.val <= 10^4
 * 3. All the values in the tree are unique.
 * 4. root is guaranteed to be a valid binary search tree.
 */
public class ConvertBSTToGreaterTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1, new TreeNode(0), new TreeNode(2, null, new TreeNode(3)));
        TreeNode right = new TreeNode(6, new TreeNode(5), new TreeNode(7, null, new TreeNode(8)));
        TreeNode root = new TreeNode(4, left, right);
        TreeNode.traversalPreOrder(convertBST(root));
    }

    /**
     * 每一个节点的新值，都等于该节点的值加上其所有右侧节点的值的和
     * 因此，可以按照 右-根-左 的顺序来对树进行一个遍历，在遍历过程中，记录每个节点的和，用于给下一个节点的值更新
     */
    public static TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    private static int dfs(TreeNode root, int sum) {
        if (root == null) {
            return sum;
        }

        root.val += dfs(root.right, sum);
        return dfs(root.left, root.val);
    }
}
