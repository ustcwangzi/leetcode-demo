package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 *
 * Example 1:
 * @see ../../../../resource/FindLargestValueInEachTreeRow.jpg
 * Input: root = [1,3,2,5,3,null,9]
 * Output: [1,3,9]
 *
 * Constraints:
 * 1. The number of nodes in the tree will be in the range [0, 10^4].
 * 2. -2^31 <= Node.val <= 2^31 - 1
 */
public class FindLargestValueInEachTreeRow {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3, new TreeNode(5), new TreeNode(3));
        root.right = new TreeNode(2, null, new TreeNode(9));
        System.out.println(largestValues(root));
    }

    /**
     * BFS
     * 思路与 {@link PopulatingNextRightPointersInEachNodeII} 类似
     * 层次遍历，将每一层的最大节点值加入结果中
     */
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每一层的节点数
            int size = queue.size(), curMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 当前层最大值
                curMax = Math.max(curMax, cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            result.add(curMax);
        }
        return result;
    }
}
