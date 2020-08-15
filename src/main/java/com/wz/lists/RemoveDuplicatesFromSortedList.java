package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 *
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 1, 1, 2, 3, 3});
        ListNode.print(deleteDuplicates(head));
    }

    /**
     * 用 cur 指向当前正在遍历的节点，发现该节点值与下一个节点值相同，则删除下一个节点
     * 注意，删除下一个节点之后，cur 不变，因为有可能再下一个节点值与 cur 的值相同，比如 1 -> 1 -> 1
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                // 删除 cur 的下一个节点
                cur.next = cur.next.next;
                continue;
            }
            // 没有相同元素时 cur 右移
            cur = cur.next;
        }
        return head;
    }
}
