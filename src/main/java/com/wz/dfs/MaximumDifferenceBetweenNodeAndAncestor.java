package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary tree, find the maximum value V for which there exist different nodes
 * A and B where V = |A.val - B.val| and A is an ancestor of B.
 * A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.
 *
 * Example 1:
 * @see ../../../../resource/MaximumDifferenceBetweenNodeAndAncestor.jpg
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [2, 5000].
 * 2. 0 <= Node.val <= 105
 */
public class MaximumDifferenceBetweenNodeAndAncestor {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(3, new TreeNode(1), new TreeNode(6, new TreeNode(4), new TreeNode(7)));
        TreeNode right = new TreeNode(10, null, new TreeNode(14, new TreeNode(13), null));
        TreeNode root = new TreeNode(8, left, right);
        System.out.println(maxAncestorDiff(root));
    }

    private static int result = 0;

    /**
     * DFS
     * 记录遍历路径中最大和最小值，更新结果
     */
    public static int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return result;
    }

    private static void dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return;
        }
        min = Math.min(root.val, min);
        max = Math.max(root.val, max);
        dfs(root.left, min, max);
        dfs(root.right, min, max);
        result = Math.max(result, Math.abs(max - min));
    }
}
