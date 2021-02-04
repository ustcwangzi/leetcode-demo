package com.wz.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a last in first out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal queue (push, top, pop, and empty).
 * Implement the MyStack class:
 * 1. void push(int x) Pushes element x to the top of the stack.
 * 2. int pop() Removes the element on the top of the stack and returns it.
 * 3. int top() Returns the element on the top of the stack.
 * 4. boolean empty() Returns true if the stack is empty, false otherwise.
 * Notes:
 * 1. You must use only standard operations of a queue, which means only push to back, peek/pop from front, size, and is empty operations are valid.
 * 2. Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue),
 *    as long as you use only a queue's standard operations.
 *
 * Example 1:
 * Input
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 2, 2, false]
 * Explanation
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // return 2
 * myStack.pop(); // return 2
 * myStack.empty(); // return False
 *
 * Constraints:
 * 1. 1 <= x <= 9
 * 2. At most 100 calls will be made to push, pop, top, and empty.
 * 3. All the calls to pop and top are valid.
 */
public class MyStack {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
    }

    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
    }

    /**
     * 将头部元素逐个移至尾部，然后返回当前头部即可
     */
    public int pop() {
        int size = queue.size();
        while (size > 1) {
            queue.add(queue.poll());
            size--;
        }
        return queue.poll();
    }

    /**
     * 和 pop 类似，只是最后返回之前需要将 poll 出来的元素再次加入队列中
     * 如 [1,2] while 循环之后变为 [2,1]，返回结果是 2，但返回之前需要将元素还原为 [1,2]，不然下次调用会返回 1
     */
    public int top() {
        int size = queue.size();
        while (size > 1) {
            queue.add(queue.poll());
            size--;
        }
        int result = queue.poll();
        // 需要再加回来，不能直接使用 peek
        queue.add(result);
        return result;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
