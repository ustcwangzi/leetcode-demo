package com.wz.stack;

/**
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node,
 * we record the node's value. If it is a null node, we record using a sentinel value such as #.
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree.
 * Find an algorithm without reconstructing the tree.
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 *
 * Example 1:
 * Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Output: true
 *
 * Example 2:
 * Input: "1,#"
 * Output: false
 */
public class VerifyPreorderSerializationOfBinaryTree {
    public static void main(String[] args) {
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    /**
     * 对于二叉树，把空的地方也作为叶子节点（题目中的#），那么有
     * 1. 所有的非空节点提供 2 个出度和 1 个入度（根除外）
     * 2. 所有的空节点但提供 0 个出度和 1 个入度
     * 在遍历的时候，计算 diff = outDegree – inDegree，每遍历一个节点，diff – 1，因为它提供一个入度；
     * 同时当节点不是 # 的时候，diff+2，因为它提供两个出度，如果序列式合法，那么遍历过程中 diff >= 0 且最后结果为 0
     */
    public static boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        // 兼容根节点
        int diff = 1;
        for (String node : nodes) {
            if (--diff < 0) {
                return false;
            }
            if (!node.equals("#")) {
                diff += 2;
            }
        }
        return diff == 0;
    }
}
