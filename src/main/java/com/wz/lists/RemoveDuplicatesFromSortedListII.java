package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * Return the linked list sorted as well.
 *
 * Example 1:
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 *
 * Example 2:
 * Input: 1->1->1->2->3
 * Output: 2->3
 */
public class RemoveDuplicatesFromSortedListII {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 1, 1, 2, 3, 3});
        ListNode.print(deleteDuplicates(head));
    }

    /**
     * 定义一个前驱指针 pre 和一个现指针 cur，现指针从 pre 的下一个位置开始遍历，遇到相同节点则右移，直到遇到不同项时
     * 然后检查 cur 是否是 pre 的下一个节点，若不是，说明 cur 遇到了重复元素，cur 本身也是重复元素中的一个，将 pre 指向 cur 的下一个节点
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(), pre = dummy;
        dummy.next = head;
        while (pre.next != null) {
            ListNode cur = pre.next;
            while (cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }

            if (cur != pre.next) {
                // 此时 cur 也是重复元素之一
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
        }

        return dummy.next;
    }
}
