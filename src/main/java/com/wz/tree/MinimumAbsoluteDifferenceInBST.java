package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 *
 * Example:
 * Input:
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * Output:
 * 1
 *
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 *
 * Note:
 * There are at least two nodes in this BST.
 */
public class MinimumAbsoluteDifferenceInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(3, new TreeNode(2), null));
        System.out.println(getMinimumDifference(root));
        System.out.println(getMinimumDifference2(root));
    }

    /**
     * 递归中序遍历收集路径 path，然后遍历 path 计算结果
     */
    public static int getMinimumDifference(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        inOrder(root, path);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < path.size() - 1; i++) {
            result = Math.min(result, path.get(i + 1) - path.get(i));
        }
        return result;
    }

    private static void inOrder(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }

        inOrder(root.left, path);
        path.add(root.val);
        inOrder(root.right, path);
    }

    /**
     * 非递归中序遍历，使用 pre 记录上一个节点，每遍历一个节点时直接计算当前结果，与 {@link FindModeInBinarySearchTree} 类似
     */
    public static int getMinimumDifference2(TreeNode root) {
        int result = Integer.MAX_VALUE;
        TreeNode cur = root, pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null) {
                    result = Math.min(result, cur.val - pre.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return result;
    }
}
