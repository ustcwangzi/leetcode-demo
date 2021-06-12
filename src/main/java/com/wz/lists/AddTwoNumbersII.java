package com.wz.lists;

import com.wz.common.ListNode;

import java.util.Stack;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit
 * comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {
    public static void main(String[] args) {
        ListNode l1 = ListNode.build(new int[]{7, 2, 4, 3});
        ListNode l2 = ListNode.build(new int[]{5, 6, 4});
        ListNode.print(addTwoNumbers(l1, l2));
        System.out.println();
        ListNode.print(addInList(l1, l2));
    }

    /**
     * 利用栈来保存所有的元素，然后利用栈的后进先出的特点就可以从后往前取数字了
     * 首先遍历两个链表，将所有数字分别压入两个栈 stack1 和 stack2 中，建立一个值为0的 result 节点，然后开始循环
     * 如果栈不为空，则将栈顶数字加入 sum 中，将 result 节点值赋为 sum%10，同时新建一个进位节点 node，赋值为sum/10
     * 然后 node 后面连上 result，再将 result 指向 node，这样循环退出后，只要看 result 的值是否为0
     * 为0说明最高位没有产生进位，返回 result.next，否则返回 result 即可
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode result = new ListNode(0);
        int sum = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }

            result.val = sum % 10;
            // 保存进位
            sum /= 10;
            // 将进位节点 node 放在 result 的前面，然后 result 指向新的头节点
            ListNode node = new ListNode(sum);
            node.next = result;
            result = node;

        }
        // 等于0说明最高位没产生进位
        return result.val == 0 ? result.next : result;
    }

    /**
     * 使用栈保存两个链表元素，然后依次弹出元素进行相加，产生的新节点采用头插法加入结果中
     */
    public static ListNode addInList(ListNode head1, ListNode head2) {
        Stack<Integer> stack1 = new Stack<>(), stack2 = new Stack<>();
        ListNode cur = head1;
        while (cur != null) {
            stack1.push(cur.val);
            cur = cur.next;
        }
        cur = head2;
        while (cur != null) {
            stack2.push(cur.val);
            cur = cur.next;
        }

        ListNode dummy = new ListNode(0);
        int carry = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int sum = stack1.pop() + stack2.pop() + carry;
            carry = sum / 10;
            cur = new ListNode(sum % 10);
            cur.next = dummy.next;
            dummy.next = cur;
        }
        while (!stack1.isEmpty()) {
            int sum = stack1.pop() + carry;
            carry = sum / 10;
            cur = new ListNode(sum % 10);
            cur.next = dummy.next;
            dummy.next = cur;
        }
        while (!stack2.isEmpty()) {
            int sum = stack2.pop() + carry;
            carry = sum / 10;
            cur = new ListNode(sum % 10);
            cur.next = dummy.next;
            dummy.next = cur;
        }
        if (carry > 0) {
            cur = new ListNode(carry);
            cur.next = dummy.next;
            dummy.next = cur;
        }
        return dummy.next;
    }
}
