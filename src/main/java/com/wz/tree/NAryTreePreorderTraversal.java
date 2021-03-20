package com.wz.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal.
 * Each group of children is separated by the null value (See examples)
 *
 * Example 1:
 * @see ../../../../resource/NAryTreePreorderTraversal.jpg
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,3,5,6,2,4]
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 10^4].
 * 2. 0 <= Node.val <= 10^4
 * 3. The height of the n-ary tree is less than or equal to 1000.
 */
public class NAryTreePreorderTraversal {
    public static void main(String[] args) {
        Node left = new Node(3, Arrays.asList(new Node(5, Collections.emptyList()), new Node(6, Collections.emptyList())));
        Node root = new Node(1, Arrays.asList(left, new Node(2, Collections.emptyList()), new Node(4, Collections.emptyList())));
        System.out.println(preorder(root));
    }

    public static List<Integer> preorder(Node root) {
        List<Integer> result = new LinkedList<>();
        dfs(root, result);
        return result;
    }

    private static void dfs(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        for (Node cur : root.children) {
            dfs(cur, result);
        }
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
