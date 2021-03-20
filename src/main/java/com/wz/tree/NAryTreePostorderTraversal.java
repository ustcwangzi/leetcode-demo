package com.wz.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal.
 * Each group of children is separated by the null value (See examples)
 *
 * Example 1:
 * @see ../../../../resource/NAryTreePreorderTraversal.jpg
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [5,6,3,2,4,1]
 */
public class NAryTreePostorderTraversal {
    public static void main(String[] args) {
        Node left = new Node(3, Arrays.asList(new Node(5, Collections.emptyList()), new Node(6, Collections.emptyList())));
        Node root = new Node(1, Arrays.asList(left, new Node(2, Collections.emptyList()), new Node(4, Collections.emptyList())));
        System.out.println(postorder(root));
    }

    public static List<Integer> postorder(Node root) {
        List<Integer> result = new LinkedList<>();
        dfs(root, result);
        return result;
    }

    private static void dfs(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }

        for (Node cur : root.children) {
            dfs(cur, result);
        }
        result.add(root.val);
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
