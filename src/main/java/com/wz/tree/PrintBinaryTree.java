package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Print a binary tree in an m*n 2D string array following these rules:
 * 1. The row number m should be equal to the height of the given binary tree.
 * 2. The column number n should always be an odd number.
 * 3. The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
 *  The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part).
 *  You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part.
 *  The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not,
 *  you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree.
 *  However, if two subtrees are none, then you don't need to leave space for both of them.
 * 4. Each unused space should contain an empty string "".
 * 5. Print the subtrees following the same rules.
 *
 * Example 1:
 * Input:
 *      1
 *     /
 *    2
 * Output:
 * [["", "1", ""],
 *  ["2", "", ""]]
 *
 * Example 2:
 * Input:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * Output:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 *
 * Example 3:
 * Input:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * Output:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 *
 * Note: The height of binary tree is in the range of [1, 10].
 */
public class PrintBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        System.out.println(printTree(root));
    }

    public static List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root), level = (int) Math.pow(2, height) - 1;
        List<List<String>> result = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            List<String> row = new ArrayList<>(level);
            for (int j = 0; j < level; j++) {
                row.add("");
            }
            result.add(row);
        }

        build(root, result, 0, 0, level - 1);
        return result;
    }

    private static void build(TreeNode root, List<List<String>> result, int level, int left, int right) {
        int mid = (left + right) / 2;
        result.get(level).set(mid, "" + root.val);
        if (root.left != null) {
            build(root.left, result, level + 1, left, mid - 1);
        }
        if (root.right != null) {
            build(root.right, result, level + 1, mid + 1, right);
        }
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
