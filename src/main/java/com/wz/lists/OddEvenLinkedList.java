package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example:
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 *
 * Constraints:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 * The length of the linked list is between [0, 10^4].
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 2, 3, 4, 5});
        ListNode.print(oddEvenList(head));
    }

    /**
     * 因为原链表是按照奇偶顺序排列的，因为可以使用 pre 指向奇节点，cur 指向偶节点
     * 然后把偶节点 cur 后面的奇节点插入到 pre 的下一个位置，pre 和 cur 右移，直到遍历完成
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = head, cur = head.next;
        while (cur != null && cur.next != null) {
            // 将 cur 的下一个节点插到 pre 的下一个位置
            ListNode next = pre.next;
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = next;

            cur = cur.next;
            pre = pre.next;
        }

        return head;
    }
}
