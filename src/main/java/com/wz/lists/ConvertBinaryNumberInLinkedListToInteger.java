package com.wz.lists;

import com.wz.common.ListNode;

/**
 * Given head which is a reference node to a singly-linked list.
 * The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.
 * Return the decimal value of the number in the linked list.
 *
 * Example 1:
 * Input: head = [1,0,1]
 * Output: 5
 * Explanation: (101) in base 2 = (5) in base 10
 *
 * Example 2:
 * Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * Output: 18880
 */
public class ConvertBinaryNumberInLinkedListToInteger {
    public static void main(String[] args) {
        ListNode head = ListNode.build(new int[]{1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0});
        System.out.println(getDecimalValue(head));
    }

    /**
     * 二进制转十进制的方法，101 = 1<<2 + 0<<1 + 1<<0 = 4 + 0 + 1 = 5
     * 二进制 | 二进制 等价于 二进制 + 二进制
     * 因此 (result << 1) | head.val 等价于 result = result*2 + head.val
     */
    public static int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            result = (result << 1) | head.val;
            head = head.next;
        }
        return result;
    }
}
