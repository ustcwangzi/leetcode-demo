package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is
 * said to be good if the length of the shortest path between them is less than or equal to distance.
 * Return the number of good leaf node pairs in the tree.
 *
 * Example 1:
 * @link ../../../../resource/NumberOfGoodLeafNodesPairs1.jpg
 * Input: root = [1,2,3,null,4], distance = 3
 * Output: 1
 * Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.
 *
 * Example 2:
 * @link ../../../../resource/NumberOfGoodLeafNodesPairs2.jpg
 * Input: root = [1,2,3,4,5,6,7], distance = 3
 * Output: 2
 * Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.
 */
public class NumberOfGoodLeafNodesPairs {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode right = new TreeNode(3, new TreeNode(6), new TreeNode(7));
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(countPairs(root, 3));
    }

    /**
     * DFS
     * 递归，返回值记录的是该节点到叶节点的距离，这样的话，叶节点到叶节点的距离就等于左右子树距离之和，筛选出满足条件的叶节点对即可
     */
    public static int countPairs(TreeNode root, int distance) {
        int[] result = new int[1];
        dfs(root, distance, result);
        return result[0];
    }

    private static List<Integer> dfs(TreeNode root, int distance, int[] result) {
        List<Integer> curList = new LinkedList<>();
        if (root == null) {
            return curList;
        }
        if (root.left == null && root.right == null) {
            curList.add(1);
            return curList;
        }

        List<Integer> leftList = dfs(root.left, distance, result);
        List<Integer> rightList = dfs(root.right, distance, result);
        // 筛选满足条件的叶节点对
        for (int left : leftList) {
            for (int right : rightList) {
                if (left + right <= distance) {
                    result[0]++;
                }
            }
        }


        // 距离加一
        for (int left : leftList) {
            curList.add(left + 1);
        }
        for (int right : rightList) {
            curList.add(right + 1);
        }
        return curList;
    }

}
