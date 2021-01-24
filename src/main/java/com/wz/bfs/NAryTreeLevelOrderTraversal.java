package com.wz.bfs;

import java.util.*;

/**
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 *
 * Example 1:
 * @see ../../../../resource/NAryTreeLevelOrderTraversal1.jpg
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 *
 * Example 2:
 * @see ../../../../resource/NAryTreeLevelOrderTraversal2.jpg
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 *
 * Constraints:
 * 1. The height of the n-ary tree is less than or equal to 1000
 * 2. The total number of nodes is between [0, 10^4]
 */
public class NAryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Node left = new Node(3, Arrays.asList(new Node(5), new Node(6)));
        Node root = new Node(1, Arrays.asList(left, new Node(2), new Node(4)));
        System.out.println(levelOrder(root));
    }

    /**
     * 与 {@link BinaryTreeLevelOrderTraversal} 类似，只是将 left、right 替换为 children
     */
    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelResult = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                levelResult.add(cur.val);
                if (cur.children != null) {
                    queue.addAll(cur.children);
                }
            }
            result.add(levelResult);
        }
        return result;
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
