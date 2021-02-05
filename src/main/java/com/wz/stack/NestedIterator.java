package com.wz.stack;

import com.wz.common.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,1,2,1,1].
 *
 * Example 2:
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,4,6].
 */
public class NestedIterator implements Iterator<Integer> {

    private final Stack<NestedInteger> stack = new Stack<>();

    /**
     * 每层嵌套都是 List，把 List 中的元素倒序放到栈中，在 hasNext() 的判断过程中，如果看到当前的元素是 Integer 那么直接弹出；
     * 如果看到的是 List 类型，那么应该对其遍历，把里面的 Integer 再倒序放入栈中
     */
    public NestedIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger cur = stack.peek();
            if (cur.isInteger()) {
                return true;
            }
            stack.pop();
            for (int i = cur.getList().size() - 1; i >= 0; i--) {
                stack.push(cur.getList().get(i));
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }
}
