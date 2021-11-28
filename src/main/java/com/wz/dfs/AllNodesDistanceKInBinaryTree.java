package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.*;

/**
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 *
 * Example 1:
 * @link ../../../../resource/AllNodesDistanceKInBinaryTree.jpg
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * Output: [7,4,1]
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 * Note:
 * 1. The given tree is non-empty.
 * 2. Each node in the tree has unique values 0 <= node.val <= 500.
 * 3. The target node is a node in the tree.
 * 4. 0 <= K <= 1000.
 */
public class AllNodesDistanceKInBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(5);
        left.left = new TreeNode(6);
        left.right = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        TreeNode right = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(distanceK(root, left, 2));
    }

    /**
     * BFS
     * 将二叉树转化为无向图，然后在无向图中使用BFS进行层次遍历
     * 无向图使用邻接表表示，每个节点有三个与之相连的节点：父节点和左右子节点
     * 然后从 target 开始进行层次遍历，依次遍历每一层和每一个节点的相邻节点，每层结束后 distance 加一，当 distance == K 时满足条件
     */
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, Set<TreeNode>> graph = new HashMap<>();
        buildGraph(root, null, graph);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);

        List<Integer> result = new LinkedList<>();
        int distance = 0;
        while (!queue.isEmpty()) {
            // 每层节点个数
            int size = queue.size();
            // 遍历每一层
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 满足条件
                if (distance == K) {
                    result.add(cur.val);
                }
                // 遍历每一个相邻节点
                for (TreeNode neighbor : graph.get(cur)) {
                    if (visited.contains(neighbor)) {
                        continue;
                    }
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
            // 该层遍历结束
            distance++;
        }
        return result;
    }

    private static void buildGraph(TreeNode root, TreeNode parent, Map<TreeNode, Set<TreeNode>> graph) {
        if (root == null) {
            return;
        }
        graph.putIfAbsent(root, new HashSet<>());
        if (parent != null) {
            graph.get(root).add(parent);
            graph.get(parent).add(root);
        }
        buildGraph(root.left, root, graph);
        buildGraph(root.right, root, graph);
    }
}
