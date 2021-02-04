package com.wz.stack;

import java.util.Stack;

/**
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 *
 * Implement the MyQueue class:
 * 1. void push(int x) Pushes element x to the back of the queue.
 * 2. int pop() Removes the element from the front of the queue and returns it.
 * 3. int peek() Returns the element at the front of the queue.
 * 4. boolean empty() Returns true if the queue is empty, false otherwise.
 * Notes:
 * 1. You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * 2. Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue)
 *    as long as you use only a stack's standard operations.
 * 3. Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words,
 *    performing n operations will take overall O(n) time even if one of those operations may take longer.
 *
 * Example 1:
 * Input
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 1, 1, false]
 * Explanation
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 * Constraints:
 * 1. 1 <= x <= 9
 * 2. At most 100 calls will be made to push, pop, peek, and empty.
 * 3. All the calls to pop and peek are valid.
 */
public class MyQueue {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
        System.out.println(queue.pop());
        System.out.println(queue.empty());
    }

    Stack<Integer> stack1, stack2;

    /**
     * 使用两个栈实现
     */
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * 新增元素全都加入到 stack1
     */
    public void push(int x) {
        stack1.push(x);
    }

    /**
     * 弹出元素全都是用 stack2
     * 如果 stack2 为空，就将 stack1 中的元素依次弹出到 stack1 中
     */
    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.add(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.add(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
