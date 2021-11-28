package com.wz.lists;

import com.wz.common.Node;

/**
 * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer,
 * which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own,
 * and so on, to produce a multilevel data structure, as shown in the example below.
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.
 *
 * Example:
 * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
 * Explanation:
 * The multilevel linked list in the input is as follows:
 * @link ../../../../resource/FlattenMultilevelDoublyLinkedList1.jpg
 * After flattening the multilevel linked list it becomes:
 * @link ../../../../resource/FlattenMultilevelDoublyLinkedList2.jpg
 */
public class FlattenMultilevelDoublyLinkedList {
    /**
     * 遍历，发现有 child 节点，就将 child 移至当前节点后面，不必理会多层，后面遍历到的时候会同样处理
     */
    public static Node flatten(Node head) {
        Node cur = head;
        while (cur != null) {
            if (cur.child != null) {
                Node next = cur.next;
                Node last = cur.child;
                while (last.next != null) {
                    last = last.next;
                }
                cur.next = cur.child;
                cur.next.prev = cur;
                cur.child = null;
                last.next = next;

                if (next != null) {
                    next.prev = last;
                }
            }
            cur = cur.next;
        }
        return head;
    }
}
