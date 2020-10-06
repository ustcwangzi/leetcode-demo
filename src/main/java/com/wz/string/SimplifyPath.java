package com.wz.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.
 * Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names.
 * The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.
 *
 * Example 1:
 * Input: "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 *
 * Example 2:
 * Input: "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 * Example 3:
 * Input: "/a/./b/../../c/"
 * Output: "/c"
 */
public class SimplifyPath {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/home//foo/"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }

    /**
     * 中间是"."的情况直接去掉，是".."时删掉它上面挨着的一个路径，如果是空的话返回"/"，如果有多个"/"只保留一个
     * 那么可以把路径看做是由一个或多个"/"分割开的众多子字符串，把它们分别提取出来一一处理即可
     */
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] array = path.split("/");
        for (String cur : array) {
            if (!stack.isEmpty() && cur.equals("..")) {
                stack.pop();
            } else if (!cur.equals(".") && !cur.equals("") && !cur.equals("..")) {
                stack.push(cur);
            }
        }

        List<String> list = new ArrayList<>(stack);
        return "/" + String.join("/", list);
    }
}
