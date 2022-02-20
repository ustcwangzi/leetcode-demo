package com.wz.lists;

import com.wz.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
 * The twin sum is defined as the sum of a node and its twin.
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 *
 * Example 1:
 * Input: head = [5,4,2,1]
 * Output: 6
 * Explanation:
 * Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
 * There are no other nodes with twins in the linked list.
 * Thus, the maximum twin sum of the linked list is 6.
 *
 * Example 2:
 * Input: head = [4,2,2,3]
 * Output: 7
 * Explanation:
 * The nodes with twins present in this linked list are:
 * - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
 * - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
 * Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
 *
 * Constraints:
 * 1. The number of nodes in the list is an even integer in the range [2, 10^5].
 * 2. 1 <= Node.val <= 10^5
 */
public class MaximumTwinSumOfLinkedList {
    public static void main(String[] args) {
        System.out.println(pairSum1(ListNode.build(new int[]{4, 2, 2, 3})));
        System.out.println(pairSum2(ListNode.build(new int[]{4, 2, 2, 3})));
    }

    /**
     * 方案一：直接将所有元素存到 list 中，然后遍历 list，找到最大的 list[i]+list[n-1-i]
     */
    public static int pairSum1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size() / 2; i++) {
            max = Math.max(max, list.get(i) + list.get(list.size() - 1 - i));
        }
        return max;
    }

    /**
     * 使用快慢指针找到中间节点 {@link MiddleOfLinkedList}
     * 寻找中间节点的过程中，将前半部分进行反转 {@link ReverseLinkedList}
     * 然后就可以分别从开始、中间位置遍历，寻找最大的节点元素之和
     */
    public static int pairSum2(ListNode head) {
        ListNode dummy = new ListNode(), slow = head, fast = head;
        dummy.next = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            // 将 next 插到 dummy 的下一个位置
            ListNode next = slow.next;
            slow.next = next.next;
            next.next = dummy.next;
            dummy.next = next;
        }

        head = dummy.next;
        // 偶数个节点，slow 的下一个节点为中间节点
        slow = slow.next;
        int max = Integer.MIN_VALUE;
        while (slow != null) {
            max = Math.max(max, head.val + slow.val);
            head = head.next;
            slow = slow.next;
        }
        return max;
    }
}
