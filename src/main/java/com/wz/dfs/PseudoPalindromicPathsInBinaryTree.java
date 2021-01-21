package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be
 * pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 *
 * Example 1:
 * @see ../../../../resource/PseudoPalindromicPathsInBinaryTree.jpg
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes:
 * the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are
 * pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome)
 * and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 */
public class PseudoPalindromicPathsInBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(3, new TreeNode(3), new TreeNode(1));
        TreeNode right = new TreeNode(1, null, new TreeNode(1));
        TreeNode root = new TreeNode(2, left, right);
        System.out.println(pseudoPalindromicPaths(root));
    }

    /**
     * DFS
     * 要构成伪回文串，需要满足一个条件，出现次数为奇数的数字最多只能有一个，所以，只需要在遍历过程中，记录路径中每个数字出现的次数即可
     * 使用 count[] 记录数字出现次数，使用 result[] 记录最终的结果
     */
    public static int pseudoPalindromicPaths(TreeNode root) {
        int[] count = new int[1];
        dfs(root, new int[10], count);
        return count[0];
    }

    private static void dfs(TreeNode root, int[] count, int[] result) {
        if (root == null) {
            return;
        }
        count[root.val]++;
        if (root.left == null && root.right == null && valid(count)) {
            result[0]++;
        }
        dfs(root.left, count, result);
        dfs(root.right, count, result);
        count[root.val]--;
    }

    private static boolean valid(int[] count) {
        // 奇数个数
        int oddCount = 0;
        for (int value : count) {
            if (value % 2 == 1) {
                oddCount++;
            }
        }
        return oddCount <= 1;
    }
}
