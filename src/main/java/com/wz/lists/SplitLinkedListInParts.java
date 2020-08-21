package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.
 * The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.
 * Return a List of ListNode's representing the linked list parts that are formed.
 *
 * Example 1:
 * Input:
 * root = [1, 2, 3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The input and each element of the output are ListNodes, not arrays.
 * For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but it's string representation as a ListNode is [].
 *
 * Example 2:
 * Input:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
 */
public class SplitLinkedListInParts {
    public static void main(String[] args) {
        ListNode root = ListNode.build(new int[]{1, 2, 3});
        ListNode[] result = splitListToParts(root, 5);
        for (ListNode node : result) {
            ListNode.print(node);
        }
    }

    /**
     * 首先统计链表中结点的总个数，然后除以 k，得到的商 avg 就是能分成的部分个数，余数 ext 就是包含有多余的结点的子链表的个数
     * 然后循环生成 k 个子链表，在循环中，先把头结点加入结果 result 中，接着遍历该子链表的结点个数
     * 首先每个子链表都一定包含有 avg 个结点，同时还要判断有没有多余结点，如果 i 小于 ext，就说明当前子链表还得有一个多余结点
     * 接着 j 从 1 开始，移动到子链表的最后一个结点上，然后断开链表
     */
    public static ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode cur = root;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        int avg = len / k, ext = len % k;
        ListNode[] result = new ListNode[k];
        for (int i = 0; i < k && root != null; i++) {
            result[i] = root;
            // 判断是否需要多包含一个节点
            int size = avg + (i < ext ? 1 : 0);
            for (int j = 1; j < size; j++) {
                root = root.next;
            }
            // node 是下一个子链表的首节点
            ListNode node = root.next;
            // 断开链表
            root.next = null;
            root = node;
        }

        return result;
    }
}
