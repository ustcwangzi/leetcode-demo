package com.wz.string;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * @link ../../../../resource/LetterCombinationsOfPhoneNumber.jpg
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Example 2:
 * Input: digits = ""
 * Output: []
 *
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */
public class LetterCombinationsOfPhoneNumber {
    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations(""));
        System.out.println(letterCombinations("2"));
    }

    private static final String[] numDic = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * DFS， 思路与{@link com.wz.array.CombinationSum}类似
     */
    public static List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();
        if (digits.length() == 0) {
            return result;
        }
        dfs(digits, 0, new StringBuilder(), result);
        return result;
    }

    private static void dfs(String digits, int start, StringBuilder group, List<String> result) {
        // 达到字符个数，返回
        if (start == digits.length()) {
            result.add(group.toString());
            return;
        }

        String str = numDic[digits.charAt(start) - '2'];
        for (int i = 0; i < str.length(); i++) {
            // 选择当前字符
            group.append(str.charAt(i));
            dfs(digits, start + 1, group, result);
            // 移除最后一个字符
            group.delete(group.length() - 1, group.length());
        }
    }
}
