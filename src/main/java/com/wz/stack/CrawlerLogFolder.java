package com.wz.stack;

import java.util.Stack;

/**
 * The Leetcode file system keeps a log each time some user performs a change folder operation.
 * The operations are described below:
 * 1. "../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
 * 2. "./" : Remain in the same folder.
 * 3. "x/" : Move to the child folder named x (This folder is guaranteed to always exist).
 * You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
 * The file system starts in the main folder, then the operations in logs are performed.
 * Return the minimum number of operations needed to go back to the main folder after the change folder operations.
 *
 * Example 1:
 * @link ../../../../resource/CrawlerLogFolder.jpg
 * Input: logs = ["d1/","d2/","../","d21/","./"]
 * Output: 2
 * Explanation: Use this change folder operation "../" 2 times and go back to the main folder.
 */
public class CrawlerLogFolder {
    public static void main(String[] args) {
        System.out.println(minOperations(new String[]{"d1/", "d2/", "../", "d21/", "./"}));
    }

    /**
     * 用栈记录剩下的目录，遍历数组，遇到 ./ 跳过，遇到 ../ 出栈，其他元素入栈，最后返回栈中元素个数
     */
    public static int minOperations(String[] logs) {
        Stack<String> stack = new Stack<>();
        for (String log : logs) {
            if (log.equals("./")) {
                continue;
            }
            if (log.equals("../")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(log);
            }
        }
        return stack.size();
    }
}
