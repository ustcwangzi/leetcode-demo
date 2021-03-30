package com.wz.tree;

import com.wz.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a binary tree with the following rules:
 *
 * root.val == 0
 * If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
 * If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
 * Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
 *
 * You need to first recover the binary tree and then implement the FindElements class:
 *
 * FindElements(TreeNode* root) Initializes the object with a contamined binary tree, you need to recover it first.
 * bool find(int target) Return if the target value exists in the recovered binary tree.
 *
 *
 * Example 1:
 *  -1              0
 *    \        ->    \
 *     -1             2
 * Input
 * ["FindElements","find","find"]
 * [[[-1,null,-1]],[1],[2]]
 * Output
 * [null,false,true]
 * Explanation
 * FindElements findElements = new FindElements([-1,null,-1]);
 * findElements.find(1); // return False
 * findElements.find(2); // return True
 *
 * Example 2:
 *         -1                 0
 *       /    \            /    \
 *      -1    -1    ->    1      2
 *     /  \              / \
 *    -1  -1            3   4
 * Input
 * ["FindElements","find","find","find"]
 * [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
 * Output
 * [null,true,true,false]
 * Explanation
 * FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
 * findElements.find(1); // return True
 * findElements.find(3); // return True
 * findElements.find(5); // return False
 *
 * Constraints:
 * 1. TreeNode.val == -1
 * 2. The height of the binary tree is less than or equal to 20
 * 3. The total number of nodes is between [1, 10^4]
 * 4. Total calls of find() is between [1, 10^4]
 * 5. 0 <= target <= 10^6
 */
public class FindElements {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-1, null, new TreeNode(-1));
        FindElements findElements = new FindElements(root);
        System.out.println(findElements.find(1));
        System.out.println(findElements.find(2));

        root.left = new TreeNode(-1, new TreeNode(-1), new TreeNode(-1));
        findElements = new FindElements(root);
        System.out.println(findElements.find(1));
        System.out.println(findElements.find(3));
        System.out.println(findElements.find(5));
    }

    private final Set<Integer> set = new HashSet<>();

    public FindElements(TreeNode root) {
        recoverTree(root, 0);
    }

    public boolean find(int target) {
        return set.contains(target);
    }

    /**
     * 先序遍历恢复二叉树，同时将 value 保存在 set 中
     */
    private void recoverTree(TreeNode root, int value) {
        if (root == null) {
            return;
        }

        root.val = value;
        set.add(value);
        recoverTree(root.left, value * 2 + 1);
        recoverTree(root.right, value * 2 + 2);
    }
}
