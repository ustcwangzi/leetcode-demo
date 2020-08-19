package com.wz.lists;

import com.wz.common.ListNode;

public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        ListNode intersection = ListNode.build(new int[]{8, 4, 5});
        ListNode headA = ListNode.build(new int[]{4, 1});
        headA.next = intersection;

        ListNode headB = ListNode.build(new int[]{5, 6, 1});
        headB.next = intersection;

        ListNode.print(getIntersectionNode(headA, headB));
    }

    /**
     * 分别从两个链表各自的开头往后遍历，当其中一个遍历到末尾时，跳到另一个链表的开头继续遍历
     * 两个指针最终会相等，而且只有两种情况，一种情况是在交点处相等，另一种情况是在各自的末尾的空节点处相等
     * 因为两个指针走过的路程相同，是两个链表的长度之和，所以一定会相等
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            // 当一个到达尾部时，跳到另一个链表的开始
            curA = curA != null ? curA.next : headB;
            curB = curB != null ? curB.next : headA;
        }

        return curA;
    }
}
