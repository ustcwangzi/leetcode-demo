package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis
 * pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
 *
 * Example 1:
 * Input: Binary tree: [1,2,3,4]
 *        1
 *      /   \
 *     2     3
 *    /
 *   4
 *
 * Output: "1(2(4))(3)"
 * Explanation: Originallay it needs to be "1(2(4)())(3()())",
 * but you need to omit all the unnecessary empty parenthesis pairs.
 * And it will be "1(2(4))(3)".
 *
 * Example 2:
 * Input: Binary tree: [1,2,3,null,4]
 *        1
 *      /   \
 *     2     3
 *      \
 *       4
 *
 * Output: "1(2()(4))(3)"
 * Explanation: Almost the same as the first example,
 * except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
 */
public class ConstructStringFromBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4), null);
        TreeNode root = new TreeNode(1, left, new TreeNode(3));
        System.out.println(tree2str(root));

        left = new TreeNode(2, null, new TreeNode(4));
        root = new TreeNode(1, left, new TreeNode(3));
        System.out.println(tree2str(root));
    }

    public static String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(t.val);
        String left = tree2str(t.left), right = tree2str(t.right);
        if (left.length() > 0 || right.length() > 0) {
            builder.append("(").append(left).append(")");
        }
        if (right.length() > 0) {
            builder.append("(").append(right).append(")");
        }
        return builder.toString();
    }
}
