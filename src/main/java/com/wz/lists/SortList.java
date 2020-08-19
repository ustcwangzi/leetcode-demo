package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class SortList {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{4, 2, 1, 3});
        ListNode.print(sortList(head));
    }

    /**
     * 题目限定了时间必须为O(n*logn)，符合要求只有快速排序，归并排序，堆排序，而根据单链表的特点，最适于用归并排序
     * 归并排序的核心是一个 merge() 函数，其主要是合并两个有序链表，这一步比较简单
     * 对于拆分，当两个链表各只有一个节点的时候，一定是有序的，因此可以将链表从中间断开，分成两部分
     * 左右两边再分别调用排序的递归函数 sortList()，得到各自有序的链表后，再进行 merge()，这样整体就是有序的了
     * 将链表从中间断开的方法，可以采用快慢指针
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 将链表从中间断开，左右子链表再进行递归拆分、合并
        pre.next = null;
        return merge(sortList(head), sortList(slow));
    }

    /**
     * 合并两个有序链表
     */
    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }

        return dummy.next;
    }
}
