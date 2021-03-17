package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.*;

/**
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined
 * as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
 * So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 *
 * Examples 1
 * Input:
 *
 *   5
 *  /  \
 * 2   -3
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 *
 * Examples 2
 * Input:
 *   5
 *  /  \
 * 2   -5
 * return [2], since 2 happens twice, however -5 only occur once.
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class MostFrequentSubtreeSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findFrequentTreeSum(new TreeNode(5, new TreeNode(2), new TreeNode(-3)))));
        System.out.println(Arrays.toString(findFrequentTreeSum(new TreeNode(5, new TreeNode(2), new TreeNode(-5)))));
    }

    /**
     * 使用后序遍历计算每个子树节点之和存入 map 中，然后遍历 map 统计出现次数最多的结果
     */
    public static int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        postOrder(root, map);
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                resultList.add(entry.getKey());
            } else if (entry.getValue() > max) {
                max = entry.getValue();
                resultList.clear();
                resultList.add(entry.getKey());
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    private static int postOrder(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }

        int left = postOrder(root.left, map);
        int right = postOrder(root.right, map);
        int sum = root.val + left + right;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }
}
