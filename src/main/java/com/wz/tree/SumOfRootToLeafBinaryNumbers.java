package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * You are given the root of a binary tree where each node has a value 0 or 1.
 * Each root-to-leaf path represents a binary number starting with the most significant bit.
 * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 * Return the sum of these numbers. The answer is guaranteed to fit in a 32-bits integer.
 *
 * Example 1:
 *        1
 *      /   \
 *     0     1
 *    / \   / \
 *   0   1 0   1
 * Input: root = [1,0,1,0,1,0,1]
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 1000].
 * 2. Node.val is 0 or 1.
 */
public class SumOfRootToLeafBinaryNumbers {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(0, new TreeNode(0), new TreeNode(1));
        TreeNode right = new TreeNode(1, new TreeNode(0), new TreeNode(1));
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(sumRootToLeaf(root));
    }

    /**
     * 与 {@link com.wz.dfs.PathSumII} 类似
     * 使用 path 收集根节点到叶节点的路径，转化为十进制存在 list 中，然后遍历 list 进行累加
     */
    public static int sumRootToLeaf(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        dfs(root, result, new StringBuilder());
        return result.stream().mapToInt(Integer::intValue).sum();
    }

    private static void dfs(TreeNode root, List<Integer> result, StringBuilder path) {
        if (root == null) {
            return;
        }

        path.append(root.val);
        if (root.left == null && root.right == null) {
            result.add(Integer.parseInt(path.toString(), 2));
        }

        dfs(root.left, result, path);
        dfs(root.right, result, path);
        path.deleteCharAt(path.length() - 1);
    }
}
