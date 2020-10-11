package com.wz.string;

import com.wz.common.NestedInteger;

import java.util.Stack;

/**
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Note: You may assume that the string is well-formed:
 * String is non-empty.
 * String does not contain white spaces.
 * String contains only digits 0-9, [, - ,, ].
 *
 * Example 1:
 * Given s = "324",
 * You should return a NestedInteger object which contains a single integer 324.
 *
 * Example 2:
 * Given s = "[123,[456,[789]]]",
 * Return a NestedInteger object containing a nested list with 2 elements:
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 *     i.  An integer containing value 456.
 *     ii. A nested list with one element:
 *          a. An integer containing value 789.
 */
public class MiniParser {
    /**
     * 遍历
     * 1. 碰到 '[', 入栈一个新的 NestedInteger
     * 2. 碰到 ',' 将新的数字(注意这里要判断这个字符串是否为空)加入到栈顶的NestedInteger 中
     * 3. 碰到 ']', 这里是关键
     *    首先，如果有数字，先加入到栈顶的NestedInteger中
     *    然后，如果此时栈的size > 1，说明栈顶的 NestedInteger 是前一个NestedInteger 的子元素，即嵌套，所以弹出，然后插入到此时的栈顶
     * 4. 其他情况下，直接加上字符
     */
    public NestedInteger deserialize(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        Stack<NestedInteger> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (char cur : s.toCharArray()) {
            switch (cur) {
                case '[':
                    stack.push(new NestedInteger());
                    break;
                case ']':
                    if (builder.length() != 0) {
                        stack.peek().add(new NestedInteger(Integer.parseInt(builder.toString())));
                        builder = new StringBuilder();
                    }
                    if (stack.size() > 1) {
                        NestedInteger node = stack.pop();
                        stack.peek().add(node);
                    }
                    break;
                case ',':
                    if (builder.length() != 0) {
                        stack.peek().add(new NestedInteger(Integer.parseInt(builder.toString())));
                        builder = new StringBuilder();
                    }
                    break;
                default:
                    builder.append(cur);
                    break;
            }
        }

        return stack.pop();
    }
}
