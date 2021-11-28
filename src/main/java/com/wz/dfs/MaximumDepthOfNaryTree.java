package com.wz.dfs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a n-ary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 *
 * Example 1:
 * @link ../../../../resource/MaximumDepthOfNaryTree.jpg
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: 3
 */
public class MaximumDepthOfNaryTree {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node = new Node(3);
        node.children = Arrays.asList(new Node(5, Collections.emptyList()), new Node(6, Collections.emptyList()));
        root.children = Arrays.asList(node, new Node(2, Collections.emptyList()), new Node(4, Collections.emptyList()));
        System.out.println(maxDepth(root));
    }

    /**
     * 与 {@link MaximumDepthOfBinaryTree} 类似
     */
    public static int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for (Node node : root.children) {
            max = Math.max(max, maxDepth(node));
        }
        return max + 1;
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
