package com.wz.lists;

import com.wz.common.ListNode;

import java.util.HashSet;

/**
 * We are given head, the head node of a linked list containing unique integer values.
 * We are also given the list G, a subset of the values in the linked list.
 * Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.
 *
 * Example 1:
 * Input:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
 *
 * Example 2:
 * Input:
 * head: 0->1->2->3->4
 * G = [0, 3, 1, 4]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
 */
public class LinkedListComponents {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{0, 1, 2, 3});
        System.out.println(numComponents(head, new int[]{0, 1, 3}));

        head = ListNode.build(new int[]{0, 1, 2, 3, 4});
        System.out.println(numComponents(head, new int[]{0, 3, 1, 4}));
    }

    /**
     * 把G 存入 hashset，然后遍历链表， 利用 hashset 来检查当前节点和下一个节点是否在 G 中
     * 如果当前节点在 G 中，而下一个节点不在，说明这里断开了，result++
     */
    public static int numComponents(ListNode head, int[] G) {
        HashSet<Integer> set = new HashSet<>(G.length);
        for (int num : G) {
            set.add(num);
        }

        int result = 0;
        while (head != null) {
            // head 节点在 G 中，next节点不在
            if (set.contains(head.val) && (head.next == null || !set.contains(head.next.val))) {
                result++;
            }
            head = head.next;
        }
        return result;
    }
}
