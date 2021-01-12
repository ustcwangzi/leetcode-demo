package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 * @see ../../../../resource/LeafSimilarTrees.jpg
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 * Example 1:
 * @see ../../../../resource/LeafSimilarTrees1.jpg
 * @see ../../../../resource/LeafSimilarTrees2.jpg
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 *
 * Constraints:
 * 1. The number of nodes in each tree will be in the range [1, 200].
 * 2. Both of the given trees will have values in the range [0, 200].
 */
public class LeafSimilarTrees {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(5);
        left1.left = new TreeNode(6);
        left1.right = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        TreeNode right1 = new TreeNode(1, new TreeNode(9), new TreeNode(8));
        TreeNode root1 = new TreeNode(3, left1, right1);
        TreeNode left2 = new TreeNode(5, new TreeNode(6), new TreeNode(7));
        TreeNode right2 = new TreeNode(1);
        right2.left = new TreeNode(4);
        right2.right = new TreeNode(2, new TreeNode(9), new TreeNode(8));
        TreeNode root2 = new TreeNode(3, left2, right2);
        System.out.println(leafSimilar(root1, root2));
    }

    /**
     * 先序遍历找到所有的叶节点，然后判断两者是否相等
     */
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        StringBuilder leaf1 = new StringBuilder(), leaf2 = new StringBuilder();
        preOrder(root1, leaf1);
        preOrder(root2, leaf2);
        return leaf1.toString().equals(leaf2.toString());
    }

    private static void preOrder(TreeNode root, StringBuilder result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            // 使用  $ 分割每个叶节点，因为节点值存在两位数和三位数的情况
            result.append(root.val).append("$");
            return;
        }
        preOrder(root.left, result);
        preOrder(root.right, result);
    }
}
