package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example:
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4});
        ListNode.print(swapPairs(head));
    }

    /**
     * head 和 next 分别指向当前需要交换的节点，pre 指向 head 的前一个节点
     * 同时为了操作方便，在链表最前面加了一个哨兵 dummy，然后在遍历中依次交换 head 和 next 即可
     */
    public static ListNode swapPairs(ListNode head) {
        // 哨兵 dummy，指向当前节点的前一个节点 pre
        ListNode dummy = new ListNode(), pre = dummy;
        dummy.next = head;
        while (head != null && head.next != null) {
            // 将 head 和 next 进行交换
            ListNode next = head.next;
            head.next = next.next;
            next.next = head;
            pre.next = next;

            // 交换后，节点右移
            pre = head;
            head = head.next;
        }

        return dummy.next;
    }
}
