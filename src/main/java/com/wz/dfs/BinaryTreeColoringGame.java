package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Two players play a turn based game on a binary tree.  We are given the root of this binary tree,
 * and the number of nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.
 * Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.
 * The first player colors the node with value x red, and the second player colors the node with value y blue.
 * Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color
 * (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node
 * (either the left child, right child, or parent of the chosen node.)
 * If (and only if) a player cannot choose such a node in this way, they must pass their turn.
 * If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
 * You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.
 * If it is not possible, return false.
 *
 * Example 1:
 * @see ../../../../resource/BinaryTreeColoringGame.jpg
 * Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
 * Output: true
 * Explanation: The second player can choose the node with value 2.
 *
 *
 * Constraints:
 *
 * root is the root of a binary tree with n nodes and distinct node values from 1 to n.
 * n is odd.
 * 1 <= x <= n <= 100
 */
public class BinaryTreeColoringGame {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(4, new TreeNode(8), new TreeNode(9));
        TreeNode right1 = new TreeNode(5, new TreeNode(10), new TreeNode(11));
        TreeNode left = new TreeNode(2, left1, right1);
        TreeNode right = new TreeNode(3, new TreeNode(6), new TreeNode(7));
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(btreeGameWinningMove(root, 11, 3));
    }

    /**
     * 将二叉树以玩家A选择的节点为基准分为三部分：父亲部分、左孩子部分、右孩子部分。玩家B只有可能占据其中一部分，如果
     * 1、左子树的节点个数比其余地方的节点个数多
     * 2、右子树的节点个数比其余地方的节点个数多
     * 3、彻底放弃A的第一个染色节点的子树，其他区域的节点个数比A的第一个染色节点的子树的节点个数多
     * 就能获胜，除此以外都不能获胜
     */
    public static boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if (root == null) {
            return false;
        }

        if (root.val == x) {
            int leftCount = dfs(root.left);
            int rightCount = dfs(root.right);
            int parentCount = n - (leftCount + rightCount + 1);
            return (leftCount > rightCount + parentCount || rightCount > leftCount + parentCount || parentCount > leftCount + rightCount);
        }

        return btreeGameWinningMove(root.left, n, x) || btreeGameWinningMove(root.right, n, x);
    }

    private static int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return dfs(node.left) + dfs(node.right) + 1;
    }
}
