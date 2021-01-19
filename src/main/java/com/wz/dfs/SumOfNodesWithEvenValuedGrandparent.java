package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.
 * (A grandparent of a node is the parent of its parent, if it exists.)
 * If there are no nodes with an even-valued grandparent, return 0.
 *
 * Example 1:
 * @see ../../../../resource/SumOfNodesWithEvenValuedGrandparent.jpg
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 *
 * Constraints:
 * 1. The number of nodes in the tree is between 1 and 10^4.
 * 2. The value of nodes is between 1 and 100.
 */
public class SumOfNodesWithEvenValuedGrandparent {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(7, new TreeNode(2, new TreeNode(9), null), new TreeNode(7, new TreeNode(1), new TreeNode(4)));
        TreeNode right = new TreeNode(8, new TreeNode(1), new TreeNode(3, null, new TreeNode(5)));
        TreeNode root = new TreeNode(6, left, right);
        System.out.println(sumEvenGrandparent(root));
    }

    private static int sum = 0;

    /**
     * DFS
     * 递归时记录当前的父节点和祖父节点
     * 如果当前祖父节点的值为偶数，则更新答案，然后递归左子节点和右子节点，在递归时，将父节点更新到祖父节点上，新的父节点为当前节点
     */
    public static int sumEvenGrandparent(TreeNode root) {
        dfs(root, null, null);
        return sum;
    }

    private static void dfs(TreeNode root, TreeNode parent, TreeNode grandParent) {
        if (root == null) {
            return;
        }
        if (grandParent != null && grandParent.val % 2 == 0) {
            sum += root.val;
        }
        dfs(root.left, root, parent);
        dfs(root.right, root, parent);
    }
}
