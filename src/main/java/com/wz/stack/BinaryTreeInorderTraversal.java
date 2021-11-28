package com.wz.stack;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example 1:
 * @link ../../../../resource/BinaryTreeInorderTraversal.jpg
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 100].
 * 2. -100 <= Node.val <= 100
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(2, new TreeNode(3), null);
        TreeNode root = new TreeNode(1, null, right);
        System.out.println(inorderTraversal(root));
        System.out.println(traversalUnRecursive(root));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
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
        traversalRecursive(root.left, result);
        result.add(root.val);
        traversalRecursive(root.right, result);
    }

    /**
     * 非递归
     * 用 cur 记录当前正在遍历的节点，不停的遍历左子树，左子树不空则入栈，否则出栈并将值加入结果集，同时开始遍历右子树
     */
    private static List<Integer> traversalUnRecursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }
}
