package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, null, new TreeNode(5));
        root.left = new TreeNode(3, null, new TreeNode(4));
        System.out.println(rightSideView(root));
    }

    /**
     * BFS
     * 思路与 {PopulatingNextRightPointersInEachNodeII} 类似
     * 层次遍历，将每一层的最右节点值加入结果中
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每一层的节点个数
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 每一层的最右侧节点
                if (i == size - 1) {
                    result.add(cur.val);
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
