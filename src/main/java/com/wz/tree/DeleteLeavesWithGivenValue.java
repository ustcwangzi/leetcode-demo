package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given a binary tree root and an integer target, delete all the leaf nodes with value target.
 * Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the value target,
 * it should also be deleted (you need to continue doing that until you can't).
 *
 * Example 1:
 *         1                 1            1
 *       /   \             /   \           \
 *      2     3     ->    2     3    ->     3
 *     /     / \                 \           \
 *    2     2   4                 4           4
 * Input: root = [1,2,3,2,null,2,4], target = 2
 * Output: [1,null,3,null,4]
 * Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left).
 * After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).
 *
 * Constraints:
 * 1. 1 <= target <= 1000
 * 2. The given binary tree will have between 1 and 3000 nodes.
 * 3. Each node's value is between [1, 1000].
 */
public class DeleteLeavesWithGivenValue {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(2), null);
        TreeNode right = new TreeNode(3, new TreeNode(2), new TreeNode(4));
        TreeNode root = new TreeNode(1, left, right);
        TreeNode.traversalPreOrder(removeLeafNodes(root, 2));
    }

    /**
     * 递归后序遍历，值为 target 时进行删除
     */
    public static TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }

        return root;
    }

}
