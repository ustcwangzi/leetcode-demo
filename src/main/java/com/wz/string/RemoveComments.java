package com.wz.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a C++ program, remove comments from it. The program source is an array where source[i] is the i-th line of the source code.
 * This represents the result of splitting the original source code string by the newline character \n.
 * In C++, there are two types of comments, line comments, and block comments.
 * The string // denotes a line comment, which represents that it and rest of the characters to the right of it in the same line should be ignored.
 * The string /* denotes a block comment, which represents that all characters until the next (non-overlapping) occurrence of *\/ should be ignored.
 * (Here,occurrences happen in reading order:line by line from left to right.)To be clear,the string /*\/ does not yet end the block comment,
 * as the ending would be overlapping the beginning.
 * The first effective comment takes precedence over others: if the string // occurs in a block comment, it is ignored.
 * Similarly, if the string /* occurs in a line or block comment, it is also ignored.
 * If a certain line of code is empty after removing comments, you must not output that line: each string in the answer list will be non-empty.
 * There will be no control characters, single quote, or double quote characters. For example, source = "string s = "/* Not a comment.
 * *\/";"will not be a test case.(Also,nothing else such as defines or macros will interfere with the comments.)
 * It is guaranteed that every open block comment will eventually be closed,so /* outside of a line or block comment always starts a new comment.
 * Finally, implicit newline characters can be deleted by block comments. Please see the examples below for details.
 * After removing the comments from the source code, return the source code in the same format.
 *
 * Example:
 * Input:
 * source = ["a/*comment", "line", "more_comment*\/b"]
 * Output:["ab"]
 * Explanation:The original source string is"a/*comment\nline\nmore_comment*\/b",where we have bolded the newline characters.
 * After deletion,the implicit newline characters are deleted,leaving the string"ab",which when delimited by newline characters becomes["ab"].
 */
public class RemoveComments {
    public static void main(String[] args) {
        System.out.println(removeComments(new String[]{"a/*comment\", \"line\", \"more_comment*/b"}));
    }

    public static List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inBlock = false;
        for (String str : source) {
            for (int j = 0; j < str.length(); j++) {
                int len = str.length();
                if (!inBlock) {
                    if (str.charAt(j) == '/' && j != len - 1 && str.charAt(j + 1) == '/') {
                        break;
                    } else if (str.charAt(j) == '/' && j != len - 1 && str.charAt(j + 1) == '*') {
                        inBlock = true;
                        j++;
                    } else {
                        cur.append(str.charAt(j));
                    }
                } else {
                    if (str.charAt(j) == '*' && j != len - 1 && str.charAt(j + 1) == '/') {
                        inBlock = false;
                        j++;
                    }
                }
            }
            if (cur.length() != 0 && !inBlock) {
                result.add(cur.toString());
                cur = new StringBuilder();
            }
        }
        return result;
    }
}
