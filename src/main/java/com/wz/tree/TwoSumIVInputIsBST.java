package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given the root of a Binary Search Tree and a target number k,
 * return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 * Example 1:
 * @link ../../../../resource/TwoSumIVInputIsBST.jpg
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 10^4].
 * 2. -10^4 <= Node.val <= 10^4
 * 3. root is guaranteed to be a valid binary search tree.
 * 4. -10^5 <= k <= 10^5
 */
public class TwoSumIVInputIsBST {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(3, new TreeNode(2), new TreeNode(4));
        TreeNode right = new TreeNode(6, null, new TreeNode(7));
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(findTarget(root, 9));
    }

    public static boolean findTarget(TreeNode root, int k) {
        return find(root, k, new HashSet<>());
    }

    /**
     * 递归遍历，遍历过程中记录已遍历的节点值，同时判断是否存在需要的值
     */
    private static boolean find(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }

        int target = k - root.val;
        if (set.contains(target)) {
            return true;
        }

        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }
}
