package com.wz.string;

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
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        System.out.println(tree2str(treeNode));
    }

    /**
     * 递归求出左右子结点的字符串，如果左右结果串均为空，则直接返回当前结点值
     * 如果左右子树都为空，直接返回当前节点值，否则将左子树转换的字符串放在括号中，并加到结果集中
     * 如果右子树为空，直接返回当前结果，否则将右子树转换的字符串放在括号中，并加到结果集中
     */
    public static String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }

        String result = String.valueOf(t.val);
        if (t.left == null && t.right == null) {
            return result;
        }

        result += "(" + tree2str(t.left) + ")";

        if (t.right != null) {
            result += "(" + tree2str(t.right) + ")";
        }

        return result;
    }
}
