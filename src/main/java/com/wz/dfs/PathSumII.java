package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Given the below binary tree and sum = 22,
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSumII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(8);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(11, new TreeNode(7), new TreeNode(2));
        right.left = new TreeNode(13);
        right.right = new TreeNode(4, new TreeNode(5), new TreeNode(1));
        System.out.println(pathSum(root, 22));
    }

    /**
     * DFS
     * 思路与 {@link com.wz.array.Subsets} 类似
     * 每遍历到一个节点，先判断节点是否为空以及节点值是否已经超过 target，若是则直接返回
     * 否则，将该节点加入 path 中，若找到一个满足条件的路径，则将 path 加入到结果集中
     * 然后，继续遍历左右子树，发现满足条件则返回上一个节点，同时将该节点从 path 中移除
     */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(root, sum, result, new LinkedList<>());
        return result;
    }

    private static void dfs(TreeNode root, int target, List<List<Integer>> result, List<Integer> path) {
        if (root == null || root.val > target) {
            return;
        }
        path.add(root.val);

        if (root.left == null && root.right == null && root.val == target) {
            result.add(new ArrayList<>(path));
        }

        dfs(root.left, target - root.val, result, path);
        dfs(root.right, target - root.val, result, path);
        path.remove(path.size() - 1);
    }
}
