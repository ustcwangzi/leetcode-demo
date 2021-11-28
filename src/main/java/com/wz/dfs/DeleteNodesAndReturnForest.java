package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.*;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 *
 * Example 1:
 * @link ../../../../resource/DeleteNodesAndReturnForest.jpg
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 * Constraints:
 * 1. The number of nodes in the given tree is at most 1000.
 * 2. Each node has a distinct value between 1 and 1000.
 * 3. to_delete.length <= 1000
 * 4. to_delete contains distinct values between 1 and 1000.
 */
public class DeleteNodesAndReturnForest {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode right = new TreeNode(3, new TreeNode(6), new TreeNode(7));
        List<TreeNode> result = delNodes(new TreeNode(1, left, right), new int[]{3, 5});
        result.forEach(node -> {
            TreeNode.traversalPreOrder(node);
            System.out.println();
        });
    }

    /**
     * BFS
     * 用 set 存储所有需要删除的节点值，然后层次遍历每一个节点
     * 1. 如果当前节点需要被删除，则从结果集中移除该节点
     *    如果被删除的节点有孩子节点，需要把孩子节点加入结果集，因为它们已经没有父节点了，是自己这个子树的根节点
     * 2. 当前节点如果有左孩子和右孩子，则分别加入 queue，同时检查左右节点是否需要被删除，
     *    如果需要，在把它们放入 queue 之后需要断开和父节点的连接，注意需要加入 queue，不然后续节点无法再遍历到
     */
    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>(to_delete.length);
        for (int value : to_delete) {
            set.add(value);
        }

        List<TreeNode> result = new LinkedList<>();
        result.add(root);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (set.contains(cur.val)) {
                result.remove(cur);
                if (cur.left != null) {
                    result.add(cur.left);
                }
                if (cur.right != null) {
                    result.add(cur.right);
                }
            }

            if (cur.left != null) {
                queue.add(cur.left);
                if (set.contains(cur.left.val)) {
                    cur.left = null;
                }
            }
            if (cur.right != null) {
                queue.add(cur.right);
                if (set.contains(cur.right.val)) {
                    cur.right = null;
                }
            }
        }

        return result;
    }
}
