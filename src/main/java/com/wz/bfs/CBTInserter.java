package com.wz.bfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
 * Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:
 * 1. CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
 * 2. CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete,
 *    and returns the value of the parent of the inserted TreeNode;
 * 3. CBTInserter.get_root() will return the head node of the tree.
 *
 * Example 1:
 * Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * Output: [null,1,[1,2]]
 *
 * Example 2:
 * Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * Output: [null,3,4,[1,2,3,4,5,6,7,8]]
 *
 * Note:
 * 1. The initial given tree is complete and contains between 1 and 1000 nodes.
 * 2. CBTInserter.insert is called at most 10000 times per test case.
 * 3. Every value of a given or inserted node is between 0 and 5000.
 */
public class CBTInserter {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode right = new TreeNode(3, new TreeNode(6), null);
        CBTInserter inserter = new CBTInserter(new TreeNode(1, left, right));
        System.out.println(inserter.insert(7));
        System.out.println(inserter.insert(8));
        TreeNode.traversalPreOrder(inserter.get_root());
    }

    private final TreeNode root;
    private final Queue<TreeNode> queue;

    /**
     * BFS
     * 对 root 进行层次遍历，使用 queue 保存未满(即不同时存在左右子树)的节点，队首保存的就是第一个未满节点
     * 新节点加入时
     * 如果队首没有左子树，则直接将其作为队首的左子树
     * 如果队首没有右子树，则将其作为队首的右子树，同时将队首弹出，因为次数队首已经满了，不能再进行添加
     *      1
     *    /   \
     *   2     3
     *  / \   /
     * 4   5 6
     * 初识时，queue 中节点为 [3, 4, 5, 6]
     * 加入 7 时，队首为 3，没有右子树，将 7 作为其右子树，同时 3 弹出，此时 queue 中节点为 [4, 5, 6, 7]
     * 加入 8 时，队首为 4，没有左子树，将 8 作为其左子树，此时 queue 中节点为 [4, 5, 6, 7, 8]
     */
    public CBTInserter(TreeNode root) {
        this.root = root;
        queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.peek();
            // 已满的节点从 queue 中弹出
            if (cur.left != null && cur.right != null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
                queue.poll();
            } else {
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                // 已到达最后一层，不需要再继续遍历
                break;
            }
        }
    }

    public int insert(int v) {
        TreeNode cur = new TreeNode(v);
        TreeNode parent = queue.peek();
        if (parent.left == null)
            parent.left = cur;
        else if (parent.right == null) {
            parent.right = cur;
            queue.poll();
        }
        queue.offer(cur);
        return parent.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
