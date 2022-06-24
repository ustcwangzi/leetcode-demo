package com.wz.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks has the same maximum capacity.
 * Implement the DinnerPlates class:
 * - DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks capacity.
 * - void push(int val) Pushes the given integer val into the leftmost stack with a size less than capacity.
 * - int pop() Returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns -1 if all the stacks are empty.
 * - int popAtStack(int index) Returns the value at the top of the stack with the given index index and removes it from that stack or returns -1 if the stack with that given index is empty.
 *
 * Example 1:
 * Input
 * ["DinnerPlates", "push", "push", "push", "push", "push", "popAtStack", "push", "push", "popAtStack", "popAtStack", "pop", "pop", "pop", "pop", "pop"]
 * [[2], [1], [2], [3], [4], [5], [0], [20], [21], [0], [2], [], [], [], [], []]
 * Output
 * [null, null, null, null, null, null, 2, null, null, 20, 21, 5, 4, 3, 1, -1]
 * Explanation:
 * DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
 * D.push(1);
 * D.push(2);
 * D.push(3);
 * D.push(4);
 * D.push(5);         // The stacks are now:  2  4
 *                                            1  3  5
 *                                            ﹈ ﹈ ﹈
 * D.popAtStack(0);   // Returns 2.  The stacks are now:     4
 *                                                        1  3  5
 *                                                        ﹈ ﹈ ﹈
 * D.push(20);        // The stacks are now: 20  4
 *                                            1  3  5
 *                                            ﹈ ﹈ ﹈
 * D.push(21);        // The stacks are now: 20  4 21
 *                                            1  3  5
 *                                            ﹈ ﹈ ﹈
 * D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
 *                                                         1  3  5
 *                                                         ﹈ ﹈ ﹈
 * D.popAtStack(2);   // Returns 21.  The stacks are now:     4
 *                                                         1  3  5
 *                                                         ﹈ ﹈ ﹈
 * D.pop()            // Returns 5.  The stacks are now:      4
 *                                                         1  3
 *                                                         ﹈ ﹈
 * D.pop()            // Returns 4.  The stacks are now:   1  3
 *                                                         ﹈ ﹈
 * D.pop()            // Returns 3.  The stacks are now:   1
 *                                                         ﹈
 * D.pop()            // Returns 1.  There are no stacks.
 * D.pop()            // Returns -1.  There are still no stacks.
 *
 * Constraints:
 * 1. 1 <= capacity <= 2 * 10^4
 * 2. 1 <= val <= 2 * 10^4
 * 3. 0 <= index <= 10^5
 * 4. At most 2 * 10^5 calls will be made to push, pop, and popAtStack.
 */
public class DinnerPlates {
    public static void main(String[] args) {
        DinnerPlates dinnerPlates = new DinnerPlates(2);
        dinnerPlates.push(1);
        dinnerPlates.push(2);
        dinnerPlates.push(3);
        dinnerPlates.push(4);
        dinnerPlates.push(5);
        System.out.println(dinnerPlates.popAtStack(0));
        dinnerPlates.push(20);
        dinnerPlates.push(21);
        System.out.println(dinnerPlates.popAtStack(0));
        System.out.println(dinnerPlates.popAtStack(2));
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
        System.out.println(dinnerPlates.pop());
    }

    private final int capacity;
    private final List<Stack<Integer>> stackList;
    private final TreeSet<Integer> availableStack;

    /**
     * 使用 List<Stack<Integer>> 保存每个 stack 的中的元素，使用 TreeSet<Integer> 保存有空间的 stack 位置
     */
    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        this.stackList = new ArrayList<>();
        this.availableStack = new TreeSet<>();
    }

    /**
     * 从 availableStack 中获取第一个位置，然后从 stackList 中获取对应 stack，加入元素
     * 加入之后，需要判断该 stack 还是否有空间，没有的话，需要从 availableStack 中移除
     */
    public void push(int val) {
        if (availableStack.isEmpty()) {
            stackList.add(new Stack<>());
            availableStack.add(stackList.size() - 1);
        }

        Stack<Integer> firstAvailableStack = stackList.get(availableStack.first());
        firstAvailableStack.push(val);
        if (firstAvailableStack.size() == capacity) {
            availableStack.pollFirst();
        }
    }

    /**
     * 从 stackList 获取最后一个 stack，弹出元素，同时向 availableStack 中加入该 stack 的索引
     * 弹出之后，需要从后向前判断 stackList 中的 stack 是否为空，将空的 stack 弹出，同时从 availableStack 移除，直到遇到不为空的 stack
     */
    public int pop() {
        if (stackList.isEmpty()) {
            return -1;
        }

        int val = stackList.get(stackList.size() - 1).pop();
        availableStack.add(stackList.size() - 1);
        while (!stackList.isEmpty() && stackList.get(stackList.size() - 1).isEmpty()) {
            stackList.remove(stackList.size() - 1);
            availableStack.pollLast();
        }
        return val;
    }

    /**
     * 若 index 是最后一个位置，则直接调用 {@link DinnerPlates#pop()} 即可
     * 否则从 stackList 获取对应 stack 并弹出元素，同时将索引加入 availableStack 中
     */
    public int popAtStack(int index) {
        if (index >= stackList.size()) {
            return -1;
        }
        if (index == stackList.size() - 1) {
            return pop();
        }

        Stack<Integer> requireStack = stackList.get(index);
        availableStack.add(index);
        return requireStack.isEmpty() ? -1 : requireStack.pop();
    }
}
