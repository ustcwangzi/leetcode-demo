package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 *
 * Example 1:
 * @see ../../../../resource/FindBottomLeftTreeValue1.jpg
 * Input: root = [2,1,3]
 * Output: 1
 *
 * Example 2:
 * @see ../../../../resource/FindBottomLeftTreeValue2.jpg
 * Input: root = [1,2,3,4,null,5,6,null,null,7]
 * Output: 7
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 10^4].
 * 2. -2^31 <= Node.val <= 2^31 - 1
 */
public class FindBottomLeftTreeValue {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(findBottomLeftValue(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(4), null);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5, new TreeNode(7), null);
        root.right.right = new TreeNode(6);
        System.out.println(findBottomLeftValue(root));
    }

    /**
     * BFS
     * 思路与 {@link PopulatingNextRightPointersInEachNodeII} 类似
     * 层次遍历，将每一层的最左侧节点值更新为结果，遍历结束返回即可
     */
    public static int findBottomLeftValue(TreeNode root) {
        int result = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每一层的节点个数
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 每一层最左侧节点
                if (i == 0) {
                    result = cur.val;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return result;
    }
}
