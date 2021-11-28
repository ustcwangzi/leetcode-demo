package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * 1. Search for a node to remove.
 * 2. If the node is found, delete the node.
 * Follow up: Can you solve it with time complexity O(height of tree)?
 *
 * Example 1:
 * @link ../../../../resource/DeleteNodeInBST1.jpg
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 * @link ../../../../resource/DeleteNodeInBST2.jpg
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 10^4].
 * 2. -10^5 <= Node.val <= 10^5
 * 3. Each node has a unique value.
 * 4. root is a valid binary search tree.
 * 5. -10^5 <= key <= 10^5
 */
public class DeleteNodeInBST {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(3, new TreeNode(2), new TreeNode(4));
        TreeNode right = new TreeNode(6, null, new TreeNode(7));
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(deleteNode(root, 3));
    }

    /**
     * key 大于当前 root 值，从右子树中删除节点
     * key 小于当前 root 值，从左子树中删除节点
     * key 等于当前 root 值，若左右子树存在空，则直接使用左或右子树替换掉 root 即可
     * 若左右子树都不为空，需要在右子树中找到最小值，即右子树的最左节点，然后将该值赋值给 root，再递归删除该最小值节点
     */
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null || root.right == null) {
                return root.left != null ? root.left : root.right;
            }
            // 寻找右子树的最左节点
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            root.val = cur.val;
            root.right = deleteNode(root.right, cur.val);
        }

        return root;
    }
}
