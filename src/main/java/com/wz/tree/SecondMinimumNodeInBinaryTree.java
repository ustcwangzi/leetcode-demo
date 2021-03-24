package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given a non-empty special binary tree consisting of nodes with the non-negative value,
 * where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes,
 * then this node's value is the smaller value among its two sub-nodes.
 * More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 * If no such second minimum value exists, output -1 instead.
 *
 * Example 1:
 *     2
 *   /   \
 *  2     5
 *       /  \
 *      5    7
 * Input: root = [2,2,5,null,null,5,7]
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 *
 * Example 2:
 *   2
 *  /  \
 * 2    2
 * Input: root = [2,2,2]
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 25].
 * 2. 1 <= Node.val <= 2^31 - 1
 * 3. root.val == min(root.left.val, root.right.val) for each internal node of the tree.
 */
public class SecondMinimumNodeInBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(2), new TreeNode(2));
        System.out.println(new SecondMinimumNodeInBinaryTree().findSecondMinimumValue(root));

        TreeNode right = new TreeNode(5, new TreeNode(5), new TreeNode(7));
        root = new TreeNode(2, new TreeNode(2), right);
        System.out.println(new SecondMinimumNodeInBinaryTree().findSecondMinimumValue(root));
    }

    private int min;
    private long second = Long.MAX_VALUE;

    /**
     * 使用全局变量记录最小值 min 和次小值 second，使用 DFS 对树进行遍历，更新 min 和 second
     */
    public int findSecondMinimumValue(TreeNode root) {
        min = root.val;
        dfs(root);
        return second == Long.MAX_VALUE ? -1 : (int) second;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        min = Math.min(min, root.val);
        if (root.val > min && root.val < second) {
            second = root.val;
        }

        dfs(root.left);
        dfs(root.right);
    }
}
