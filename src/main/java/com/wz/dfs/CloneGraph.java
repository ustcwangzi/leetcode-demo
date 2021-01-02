package com.wz.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 * Test case format:
 * For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1,
 * the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
 * Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 *
 * Example 1:
 * @see ../../../../resource/CloneGraph.jpg
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 *
 * Constraints:
 * 1. 1 <= Node.val <= 100
 * 2. Node.val is unique for each node.
 * 3. Number of Nodes will not exceed 100.
 * 4. There is no repeated edges and no self-loops in the graph.
 * 5. The Graph is connected and all nodes can be visited starting from the given node.
 */
public class CloneGraph {
    /**
     * 每个节点值不同，用 visited 记录每个节点是否被克隆了，若当前的结点已经被克隆过了，则直接返回其映射节点
     * 否则就进行克隆，并在 visited 中建立映射，然后遍历当前节点的所有 neighbor，加到克隆节点的 neighbors 数组
     */
    public static Node cloneGraph(Node node) {
        Map<Integer, Node> visited = new HashMap<>();
        return dfs(node, visited);
    }

    private static Node dfs(Node node, Map<Integer, Node> visited) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node.val)) {
            return visited.get(node.val);
        }

        Node result = new Node(node.val);
        visited.put(node.val, result);

        for (Node neighbor : node.neighbors) {
            result.neighbors.add(dfs(neighbor, visited));
        }

        return result;
    }

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
