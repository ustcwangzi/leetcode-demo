package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Input:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * Output: ["1->2->5", "1->3"]
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePaths {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3));
        System.out.println(binaryTreePaths(root));
    }

    /**
     * DFS
     * 思路与 {@link PathSumII} 类似
     * 使用 path 收集所有的路径，存在 paths 中，然后遍历 paths 将路径组成最终结果
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<List<Integer>> paths = new LinkedList<>();
        dfs(root, paths, new ArrayList<>());

        List<String> result = new ArrayList<>(paths.size());
        for (List<Integer> path : paths) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                builder.append(path.get(i));
                if (i != path.size() - 1) {
                    builder.append("->");
                }
            }
            result.add(builder.toString());
        }

        return result;
    }

    private static void dfs(TreeNode root, List<List<Integer>> paths, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            paths.add(new LinkedList<>(path));
        }
        dfs(root.left, paths, path);
        dfs(root.right, paths, path);
        path.remove(path.size() - 1);
    }
}
