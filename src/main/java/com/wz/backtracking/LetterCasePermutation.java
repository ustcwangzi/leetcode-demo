package com.wz.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create. You can return the output in any order.
 *
 * Example 1:
 * Input: S = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 *
 * Example 2:
 * Input: S = "3z4"
 * Output: ["3z4","3Z4"]
 *
 * Constraints:
 * 1. S will be a string with length between 1 and 12.
 * 2. S will consist only of letters or digits.
 */
public class LetterCasePermutation {
    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
    }

    /**
     * DFS
     * 遇到数字则继续下一个位置，遇到字符，先将字符转成小写进行DFS，再将字符转成大些进行DFS
     */
    public static List<String> letterCasePermutation(String s) {
        List<String> result = new LinkedList<>();
        dfs(s.toCharArray(), 0, result);
        return result;
    }

    private static void dfs(char[] word, int index, List<String> result) {
        if (index == word.length) {
            result.add(new String(word));
            return;
        }
        if (Character.isDigit(word[index])) {
            dfs(word, index + 1, result);
            return;
        }
        if (Character.isLetter(word[index])) {
            word[index] = Character.toLowerCase(word[index]);
            dfs(word, index + 1, result);
            word[index] = Character.toUpperCase(word[index]);
            dfs(word, index + 1, result);
        }
    }
}
