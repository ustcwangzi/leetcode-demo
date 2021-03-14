package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.
 *
 * Example:
 *      3
 *    /   \
 *   1     4
 *    \
 *     2
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Input: root = [3,1,4,null,2], k = 3
 * Output: 3
 *
 * Constraints:
 * 1. The number of nodes in the tree is n.
 * 2. 1 <= k <= n <= 104
 * 3. 0 <= Node.val <= 104
 */
public class KthSmallestElementInBST {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1, null, new TreeNode(2));
        TreeNode root = new TreeNode(3, left, new TreeNode(4));
        System.out.println(kthSmallest(root, 1));
        System.out.println(kthSmallest2(root, 1));
        System.out.println(kthSmallest(root, 3));
        System.out.println(kthSmallest2(root, 3));
    }

    /**
     * BST 的中序遍历是有序的，直接返回第 k 个元素即可
     */
    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> result = new LinkedList<>();
        inOrderTraversal(root, result);
        return result.get(k - 1);
    }

    /**
     * 递归中序遍历
     */
    private static void inOrderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, result);
        result.add(root.val);
        inOrderTraversal(root.right, result);
    }

    /**
     * 非递归中序遍历，{@link com.wz.stack.BinaryTreeInorderTraversal}
     */
    public static int kthSmallest2(TreeNode root, int k) {
        List<Integer> result = new LinkedList<>();
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                if (result.size() == k) {
                    return cur.val;
                }
                cur = cur.right;
            }
        }
        return 0;
    }
}
