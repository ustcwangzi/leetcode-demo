package com.wz.lists;

import com.wz.common.RandomNode;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * 1. val: an integer representing Node.val
 * 2. random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
 *
 * Example 1:
 * @link ../../../../resource/CopyListWithRandomPointer.jpg
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 */
public class CopyListWithRandomPointer {
    /**
     * 1. 在原链表的每个节点后面拷贝出一个新的节点
     * 2. 依次给新的节点的随机指针赋值，而且这个赋值非常容易 cur.next.random = cur.random.next
     * 3. 断开链表中的拷贝可得到深度拷贝后的新链表
     */
    public static RandomNode copyRandomList(RandomNode head) {
        if (head == null) {
            return head;
        }

        // 在每个原节点后面加入一个 copy 节点
        RandomNode cur = head;
        while (cur != null) {
            RandomNode copy = new RandomNode(cur.val);
            copy.next = cur.next;
            cur.next = copy;
            cur = copy.next;
        }

        // 按照原节点的 random 建立 copy 节点的 random
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 将 copy 节点取出链接在结果中
        RandomNode result = head.next;
        cur = head;
        while (cur != null) {
            RandomNode copy = cur.next;
            // 断开 copy 节点
            cur.next = copy.next;
            // 将 copy 节点连在结果中
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
            cur = cur.next;
        }
        return result;
    }
}
