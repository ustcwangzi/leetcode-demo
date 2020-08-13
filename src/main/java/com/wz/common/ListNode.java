package com.wz.common;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode build(int[] array) {
        ListNode head = new ListNode(array[0]), cur = head;
        for (int i = 1; i < array.length; i++) {
            ListNode node = new ListNode(array[i]);
            cur.next = node;
            cur = node;
        }
        return head;
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
