package com.wz.design;

import com.wz.common.TreeNode;

import java.util.Stack;

public class Codec {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(3, new TreeNode(4), new TreeNode(5));
        TreeNode root = new TreeNode(1, new TreeNode(2), right);
        Codec codec = new Codec();
        String data = codec.serialize(root);
        System.out.println(data);
        TreeNode.traversalPreOrder(codec.deserialize(data));
        System.out.println();

        right = new TreeNode(3, new TreeNode(4), new TreeNode(5));
        root = new TreeNode(1, new TreeNode(2), right);
        codec = new Codec();
        data = codec.serialize2(root);
        System.out.println(data);
        TreeNode.traversalPreOrder(codec.deserialize(data));
    }

    /**
     * 与 {@link SerializeAndDeserializeBST} 类似
     */
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        preorder(root, builder);
        return builder.toString();
    }

    private void preorder(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("#").append(",");
            return;
        }
        builder.append(root.val).append(",");
        preorder(root.left, builder);
        preorder(root.right, builder);
    }

    public String serialize2(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                builder.append(cur.val).append(",");
                stack.push(cur);
                cur = cur.left;
            } else {
                builder.append("#").append(",");
                cur = stack.pop();
                cur = cur.right;
            }
        }
        // 最后一定是 null，做补齐操作
        builder.append("#").append(",");
        return builder.toString();
    }

    public TreeNode deserialize(String data) {
        return build(data.split(","));
    }

    int index = 0;

    private TreeNode build(String[] array) {
        if (array[index].equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(array[index]));
        index++;
        root.left = build(array);
        index++;
        root.right = build(array);
        return root;
    }
}
