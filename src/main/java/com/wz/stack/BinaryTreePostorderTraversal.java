package com.wz.stack;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example 1:
 * @link ../../../../resource/BinaryTreeInorderTraversal.jpg
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 *
 * Constraints:
 * 1. The number of the nodes in the tree is in the range [0, 100].
 * 2. -100 <= Node.val <= 100
 */
public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(2, new TreeNode(3), null);
        TreeNode root = new TreeNode(1, null, right);
        System.out.println(postorderTraversal(root));
        System.out.println(traversalUnRecursive(root));
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
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
        traversalRecursive(root.right, result);
        result.add(root.val);
    }

    /**
     * 非递归，与 {@link BinaryTreePreorderTraversal} 类似
     * preOrder 遍历顺序是 根-左-右，调整左右子树入栈的顺序之后，遍历顺序为 根-右-左
     * 与 postOrder 的 左-右-根 是逆序关系，因此在加入结果集时，将尾插法改为头插法即可
     */
    private static List<Integer> traversalUnRecursive(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                result.add(0, cur.val);
                cur = cur.right;
            } else {
                cur = stack.pop();
                cur = cur.left;
            }
        }
        return result;
    }
}
