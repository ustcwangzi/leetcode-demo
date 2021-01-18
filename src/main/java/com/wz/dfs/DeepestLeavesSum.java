package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, return the sum of values of its deepest leaves.
 *
 * Example 1:
 * @see ../../../../resource/DeepestLeavesSum.jpg
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 *
 * Constraints:
 * 1. The number of nodes in the tree is between 1 and 10^4.
 * 2. The value of nodes is between 1 and 100.
 */
public class DeepestLeavesSum {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4, new TreeNode(7), null), new TreeNode(5));
        TreeNode right = new TreeNode(3, null, new TreeNode(6, null, new TreeNode(8)));
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(deepestLeavesSum(root));
    }

    /**
     * BFS
     * 遍历每一层时先将 sum 置为 0，然后统计该层的累加和，最后的 sum 就是最后一层的累加和，也就是最终的结果
     */
    public static int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return sum;
    }
}
