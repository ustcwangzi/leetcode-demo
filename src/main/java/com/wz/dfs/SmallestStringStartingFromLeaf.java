package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z':
 * a value of 0 represents 'a', a value of 1 represents 'b', and so on.
 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example,
 * "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
 *
 * Example 1:
 * @see ../../../../resource/SmallestStringStartingFromLeaf.jpg
 * Input: [0,1,2,3,4,3,4]
 * Output: "dba"
 */
public class SmallestStringStartingFromLeaf {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1, new TreeNode(3), new TreeNode(4));
        TreeNode right = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode root = new TreeNode(0, left, right);
        System.out.println(smallestFromLeaf(root));
    }

    private static String result = "~";

    /**
     * 与 {@link BinaryTreePaths} 类似
     * 还是找从根结点到叶结点的路径，只是在组成路径的时候，每次把字符加到最开始位置，每次遍历到叶节点时更新结果
     */
    public static String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return result;
    }

    private static void dfs(TreeNode root, StringBuilder path) {
        if (root == null) {
            return;
        }
        path.insert(0, (char) ('a' + root.val));
        if (root.left == null && root.right == null && path.toString().compareTo(result.toString()) < 0) {
            result = path.toString();
        }
        dfs(root.left, path);
        dfs(root.right, path);
        path.deleteCharAt(0);
    }
}
