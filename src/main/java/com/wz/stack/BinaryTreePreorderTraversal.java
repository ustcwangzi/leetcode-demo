package com.wz.stack;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 * Example 1:
 * @see ../../../../resource/BinaryTreeInorderTraversal.jpg
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 100].
 * 2. -100 <= Node.val <= 100
 */
public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(2, new TreeNode(3), null);
        TreeNode root = new TreeNode(1, null, right);
        System.out.println(preorderTraversal(root));
        System.out.println(traversalUnRecursive(root));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        traversalRecursive(root, result);
        return result;
    }

    /**
     * 递归
     */
    private static void traversalRecursive(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        traversalRecursive(root.left, result);
        traversalRecursive(root.right, result);
    }

    /**
     * 非递归，与 {@link BinaryTreeInorderTraversal} 类似
     * 只是将加入结果集的步骤放在了遍历左右子树之前
     */
    private static List<Integer> traversalUnRecursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                result.add(cur.val);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return result;
    }
}
