package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two binary search trees root1 and root2.
 * Return a list containing all the integers from both trees sorted in ascending order.
 *
 * Example 1:
 *     2          1
 *   /   \      /   \
 *  1     4    0     3
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 *
 * Example 2:
 * Input: root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * Output: [-10,0,0,1,2,5,7,10]
 *
 * Constraints:
 * 1. Each tree has at most 5000 nodes.
 * 2. Each node's value is between [-10^5, 10^5].
 */
public class AllElementsInTwoBinarySearchTrees {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(4));
        TreeNode root2 = new TreeNode(1, new TreeNode(0), new TreeNode(3));
        System.out.println(getAllElements(root1, root2));
    }

    /**
     * 中序遍历收集遍历结果，然后双指针进行合并
     */
    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> path1 = new ArrayList<>(), path2 = new ArrayList<>();
        inorder(root1, path1);
        inorder(root2, path2);
        return merge(path1, path2);
    }

    private static void inorder(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }

        inorder(root.left, path);
        path.add(root.val);
        inorder(root.right, path);
    }

    private static List<Integer> merge(List<Integer> path1, List<Integer> path2) {
        List<Integer> result = new ArrayList<>(path1.size() + path2.size());
        int i = 0, j = 0;
        while (i < path1.size() && j < path2.size()) {
            if (path1.get(i) < path2.get(j)) {
                result.add(path1.get(i++));
            } else {
                result.add(path2.get(j++));
            }
        }
        while (i < path1.size()) {
            result.add(path1.get(i++));
        }
        while (j < path2.size()) {
            result.add(path2.get(j++));
        }
        return result;
    }
}
