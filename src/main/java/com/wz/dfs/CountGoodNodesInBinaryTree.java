package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 * Return the number of good nodes in the binary tree.
 *
 * Example 1:
 * @link ../../../../resource/CountGoodNodesInBinaryTree.jpg
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 */
public class CountGoodNodesInBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1, new TreeNode(3), null);
        TreeNode right = new TreeNode(4, new TreeNode(1), new TreeNode(5));
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(goodNodes(root));
    }

    /**
     * DFS
     * 遍历过程中记录之前的最大值 preMax，若当前节点值小于 preMax，则继续遍历左右子节点
     * 否则，将 preMax 更新为当前节点，然后继续遍历左右子节点
     */
    public static int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private static int dfs(TreeNode root, int preMax) {
        if (root == null) {
            return 0;
        }
        if (root.val < preMax) {
            return dfs(root.left, preMax) + dfs(root.right, preMax);
        } else {
            return 1 + dfs(root.left, root.val) + dfs(root.right, root.val);
        }
    }
}
