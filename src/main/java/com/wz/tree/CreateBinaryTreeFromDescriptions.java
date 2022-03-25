package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,
 * 1. If isLefti == 1, then childi is the left child of parenti.
 * 2. If isLefti == 0, then childi is the right child of parenti.
 * Construct the binary tree described by descriptions and return its root.
 * The test cases will be generated such that the binary tree is valid.
 *
 * Example 1:
 * @link ../../../../resource/CreateBinaryTreeFromDescriptions1.jpg
 * Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
 * Output: [50,20,80,15,17,19]
 * Explanation: The root node is the node with value 50 since it has no parent.
 * The resulting binary tree is shown in the diagram.
 *
 * Example 2:
 * @link ../../../../resource/CreateBinaryTreeFromDescriptions2.jpg
 * Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
 * Output: [1,2,null,null,3,4]
 * Explanation: The root node is the node with value 1 since it has no parent.
 * The resulting binary tree is shown in the diagram.
 *
 * Constraints:
 * 1. 1 <= descriptions.length <= 10^4
 * 2. descriptions[i].length == 3
 * 3. 1 <= parenti, childi <= 10^5
 * 4. 0 <= isLefti <= 1
 * 5. The binary tree described by descriptions is valid.
 */
public class CreateBinaryTreeFromDescriptions {
    public static void main(String[] args) {
        TreeNode.traversalPreOrder(createBinaryTree(new int[][]{{20, 15, 1}, {20, 17, 0}, {50, 20, 1}, {50, 80, 0}, {80, 19, 1}}));
    }

    /**
     * Map + Set
     * 使用 map 保存每个元素及其对应节点，使用 set 保存所有孩子节点
     * 遍历 descriptions，根据 isLeft 将孩子节点设置为父节点的左节点 OR 右节点，同时将孩子节点加入到 set 中
     * 最后，再遍历一次 descriptions，set 中不存在的节点就是根节点
     */
    public static TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> childSet = new HashSet<>();
        for (int[] array : descriptions) {
            int parent = array[0], child = array[1], isLeft = array[2];
            childSet.add(child);
            TreeNode parentNode = map.getOrDefault(parent, new TreeNode(parent));
            TreeNode childNode = map.getOrDefault(child, new TreeNode(child));
            if (isLeft == 1) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
            map.put(parent, parentNode);
            map.put(child, childNode);
        }

        for (int[] array : descriptions) {
            if (!childSet.contains(array[0])) {
                return map.get(array[0]);
            }
        }
        return null;
    }
}
