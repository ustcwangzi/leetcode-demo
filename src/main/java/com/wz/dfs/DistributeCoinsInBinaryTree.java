package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * You are given the root of a binary tree with n nodes where each node in the tree has node.val coins and there are n coins total.
 * In one move, we may choose two adjacent nodes and move one coin from one node to another. (A move may be from parent to child, or from child to parent.)
 * Return the number of moves required to make every node have exactly one coin.
 *
 * Example 1:
 * @see ../../../../resource/DistributeCoinsInBinaryTree1.jpg
 * Input: root = [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 *
 * Example 2:
 * @see ../../../../resource/DistributeCoinsInBinaryTree2.jpg
 * Input: root = [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root [taking two moves].
 * Then, we move one coin from the root of the tree to the right child.
 */
public class DistributeCoinsInBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(0), new TreeNode(0));
        System.out.println(distributeCoins(root));
    }

    private static int result = 0;

    /**
     * 定义 DFS 函数，表示叶节点向父节点传递的 coin 个数，因此采用后续遍历
     * 那么 dfs(node) = dfs(node.left) + dfs(node.right) + node.val - 1 (减 1 是因为需要留一个 coin 在当前节点)
     * 而以当前节点为 root 需要移动的步数等于 abs(dfs(node.left)) + abs(dfs(node.right))，因此不论正负，需要占用移动次数
     */
    public static int distributeCoins(TreeNode root) {
        dfs(root);
        return result;
    }

    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        result += Math.abs(left) + Math.abs(right);
        return left + right + root.val - 1;
    }
}
