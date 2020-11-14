package com.wz.dynamicprogramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    /**
     * DFS
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<>();
        dfs(s, 0, new LinkedList<>(), result);
        return result;
    }

    private static void dfs(String s, int start, List<String> group, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(group));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i)) {
                continue;
            }
            // 选择当前子串
            group.add(s.substring(start, i + 1));
            dfs(s, i + 1, group, result);
            // 不选择当前子串
            group.remove(group.size() - 1);
        }
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
