package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree, return all duplicate subtrees.
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 * Example 1:
 * @see ../../../../resource/FindDuplicateSubtrees1.jpg
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 *
 * Example 2:
 * @see ../../../../resource/FindDuplicateSubtrees2.jpg
 * Input: root = [2,1,1]
 * Output: [[1]]
 *
 * Constraints:
 * 1. The number of the nodes in the tree will be in the range [1, 10^4]
 * 2. -200 <= Node.val <= 200
 */
public class FindDuplicateSubtrees {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4), null);
        TreeNode right = new TreeNode(3, left, new TreeNode(4));
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(findDuplicateSubtrees(root));
    }

    /**
     * 把每个子树都进行序列化，使用 map 来存储序列化的结果出现的次数，从而可以找到重复的子树
     */
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new LinkedList<>();
        traverse(root, new HashMap<>(), result);
        return result;
    }

    private static String traverse(TreeNode root, Map<String, Integer> count, List<TreeNode> result) {
        if (root == null) {
            return "";
        }

        String serial = root.val + "," + traverse(root.left, count, result) + "," + traverse(root.right, count, result);
        count.put(serial, count.getOrDefault(serial, 0) + 1);
        if (count.get(serial) == 2) {
            result.add(root);
        }
        return serial;
    }
}
