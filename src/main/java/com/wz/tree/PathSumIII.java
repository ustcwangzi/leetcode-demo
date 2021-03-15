package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 */
public class PathSumIII {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3, new TreeNode(3), new TreeNode(-2));
        TreeNode right1 = new TreeNode(2, null, new TreeNode(1));
        TreeNode left = new TreeNode(5, left1, right1);
        TreeNode right = new TreeNode(-3, null, new TreeNode(11));
        TreeNode root = new TreeNode(10, left, right);
        System.out.println(pathSum(root, 8));
        System.out.println(pathSum2(root, 8));
    }

    /**
     * 方案一，从任意节点出发向下节点值之和等于 sum，即满足条件，因此可以对根、左、右分别进行 DFS
     * 每次 DFS 过程中计算累加和
     */
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return dfs1(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private static int dfs1(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        if (sum == root.val) {
            count++;
        }
        count += dfs1(root.left, sum - root.val);
        count += dfs1(root.right, sum - root.val);
        return count;
    }

    /**
     * 方案二，对整体进行 DFS，使用 List 收集路径，每加入一个节点，就从该节点向上计算累加和
     */
    public static int pathSum2(TreeNode root, int sum) {
        return dfs2(root, sum, new ArrayList<>());
    }

    private static int dfs2(TreeNode root, int sum, List<Integer> path) {
        if (root == null) {
            return 0;
        }

        path.add(root.val);
        int count = 0, curSum = 0, size = path.size();
        while (--size >= 0) {
            curSum += path.get(size);
            if (curSum == sum) {
                count++;
            }
        }

        count += dfs2(root.left, sum, path);
        count += dfs2(root.right, sum, path);
        path.remove(path.size() - 1);
        return count;
    }
}
