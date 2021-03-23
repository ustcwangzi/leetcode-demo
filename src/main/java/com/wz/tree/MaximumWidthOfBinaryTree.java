package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree.
 * The maximum width of a tree is the maximum width among all levels.
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
 * where the null nodes between the end-nodes are also counted into the length calculation.
 * It is guaranteed that the answer will in the range of 32-bit signed integer.
 *
 * Example 1:
 * Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 *
 * Example 2:
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 *
 * Example 3:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 *
 * Example 4:
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 *
 * Constraints:
 * The given binary tree will have between 1 and 3000 nodes.
 */
public class MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(3, new TreeNode(5), new TreeNode(3));
        TreeNode right = new TreeNode(2, null, new TreeNode(9));
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(widthOfBinaryTree(root));

        root = new TreeNode(1, left, null);
        System.out.println(widthOfBinaryTree(root));
    }

    /**
     * 最后的宽度是按照满二叉树来进行计算的，也就是需要得到每层最左边的节点编号和最右边的节点编号，就可以得到结果
     * 既然是满二叉树，那么编号是父节点为 i，左子节点为 2 * i + 1，右子节点为 2 * i + 2
     */
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        int result = 0;
        root.val = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    cur.left.val = 2 * cur.val + 1;
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    cur.right.val = 2 * cur.val + 2;
                    queue.offer(cur.right);
                }
                min = Math.min(min, cur.val);
                max = Math.max(max, cur.val);
            }
            result = Math.max(result, max - min + 1);
        }
        return result;
    }
}
