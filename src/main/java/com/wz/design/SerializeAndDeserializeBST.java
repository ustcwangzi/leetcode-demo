package com.wz.design;

import com.wz.common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string,
 * and this string can be deserialized to the original tree structure.
 * The encoded string should be as compact as possible.
 *
 * Example 1:
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 10^4].
 * 2. 0 <= Node.val <= 10^4
 * 3. The input tree is guaranteed to be a binary search tree.
 */
public class SerializeAndDeserializeBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        SerializeAndDeserializeBST serializeAndDeserializeBST = new SerializeAndDeserializeBST();
        String str = serializeAndDeserializeBST.serialize(root);
        System.out.println(str);
        System.out.println(serializeAndDeserializeBST.deserialize(str));
    }

    /**
     * 序列化：先序遍历，使用 # 代表空，使用 , 分割每个节点
     */
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString();
    }

    private void serialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("#").append(",");
        } else {
            builder.append(root.val).append(",");
            serialize(root.left, builder);
            serialize(root.right, builder);
        }
    }

    /**
     * 反序列化：使用 , 将字符串拆开存到队列中，然后递归创建每个节点
     */
    public TreeNode deserialize(String data) {
        String[] array = data.split(",");
        Queue<String> queue = new LinkedList<>();
        Arrays.stream(array).forEach(queue::offer);
        return deserialize(queue);
    }

    private TreeNode deserialize(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String cur = queue.poll();
        if (cur.equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.left = deserialize(queue);
        root.right = deserialize(queue);
        return root;
    }
}
