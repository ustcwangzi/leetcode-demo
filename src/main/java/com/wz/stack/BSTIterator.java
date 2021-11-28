package com.wz.stack;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 * 1. BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 *    The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * 2. boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * 3. int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 *
 * Example 1:
 * @link ../../../../resource/BSTIterator.jpg
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 10^5].
 * 2. 0 <= Node.val <= 10^6
 * 3. At most 10^5 calls will be made to hasNext, and next.
 */
public class BSTIterator {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(15, new TreeNode(9), new TreeNode(20));
        TreeNode root = new TreeNode(7, new TreeNode(3), right);
        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }

    Queue<Integer> queue;

    /**
     * 使用队列保存中序遍历的结果即可
     */
    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        dfs(root);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        queue.add(root.val);
        dfs(root.right);
    }

    public int next() {
        return queue.poll();
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
