package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 * The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.
 * Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 *
 * Example 1:
 * @see ../../../../resource/SmallestSubtreeWithAllTheDeepestNodes.jpg
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation: We return the node with value 2, colored in yellow in the diagram.
 * The nodes coloured in blue are the deepest nodes of the tree.
 * Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.
 *
 * Example 2:
 * Input: root = [0,1,3,null,2]
 * Output: [2]
 * Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the subtree of node 2 is the smallest.
 *
 * Constraints:
 * 1. The number of nodes in the tree will be in the range [1, 500].
 * 2. 0 <= Node.val <= 500
 * 3. The values of the nodes in the tree are unique.
 */
public class SmallestSubtreeWithAllTheDeepestNodes {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(5);
        left.left = new TreeNode(6);
        left.right = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        TreeNode right = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(subtreeWithAllDeepest(root).val);
    }

    /**
     * 包含所有最深结点的最小子树，最深的结点不一定只有两个，可能有很多个，比如对于一棵完全二叉树，即把例子图中的结点7和4去掉后，
     * 此时最深的结点就有四个，分别是 6，2，0，8，都包含这些结点的子树就是原树本身了，要返回根结点
     * 通过分析可以发现，子树的最大深度很重要，对于一棵完全二叉树来说，根结点的左右子树的最大深度一定是相同的，此时直接返回根结点即可。
     * 若左右子树的最大深度不同，则最深结点一定位于深度大的子树中，可以对其调用递归函数。
     * 所以先计算左右子树的最大深度，如果两者之差等于 0，直接返回当前节点，否则对深度较大的节点调用当前的递归函数
     */
    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        int diff = depth(root.left) - depth(root.right);
        if (diff == 0) {
            return root;
        }
        return subtreeWithAllDeepest(diff > 0 ? root.left : root.right);
    }

    private static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}
