package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:
 * 1. Create a root node whose value is the maximum value in nums.
 * 2. Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * 3. Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 *
 * Example 1:
 *       6
 *    /     \
 *   3       5
 *    \     /
 *     2   0
 *      \
 *       1
 * Input: nums = [3,2,1,6,0,5]
 * Output: [6,3,5,null,2,0,null,null,1]
 * Explanation: The recursive calls are as follow:
 * - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
 *     - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
 *         - Empty array, so no child.
 *         - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
 *             - Empty array, so no child.
 *             - Only one element, so child is a node with value 1.
 *     - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
 *         - Only one element, so child is a node with value 0.
 *         - Empty array, so no child.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 0 <= nums[i] <= 1000
 * 3. All integers in nums are unique.
 */
public class MaximumBinaryTree {
    public static void main(String[] args) {
        TreeNode root = constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        TreeNode.traversalPreOrder(root);
    }

    /**
     * 递归创建左右子树
     * 每次找出当前 nums[start...end] 的最大值下标 index，将 nums[index] 作为根节点
     * 然后使用 nums[start...index-1] 创建左子树、nums[index+1...end] 创建右子树
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private static TreeNode construct(int[] nums, int start, int end) {
        if (start < 0 || end > nums.length - 1 || start > end) {
            return null;
        }

        int maxIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = construct(nums, start, maxIndex - 1);
        root.right = construct(nums, maxIndex + 1, end);
        return root;
    }
}
