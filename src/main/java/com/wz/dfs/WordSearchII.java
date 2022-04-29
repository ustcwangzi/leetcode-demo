package com.wz.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example 1:
 * @link ../../../../resource/WordSearchII1.jpg
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 * Example 2:
 * @link ../../../../resource/WordSearchII2.jpg
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 * Constraints:
 * 1. m == board.length
 * 2. n == board[i].length
 * 3. 1 <= m, n <= 12
 * 4. board[i][j] is a lowercase English letter.
 * 5. 1 <= words.length <= 3 * 10^4
 * 6. 1 <= words[i].length <= 10
 * 7. words[i] consists of lowercase English letters.
 * 8. All the strings of words are unique.
 */
public class WordSearchII {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        System.out.println(findWords(board, words));

        board = new char[][]{{'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}};
        words = new String[]{"oa", "oaa"};
        System.out.println(findWords(board, words));
    }

    /**
     * Trie + DFS
     * {@link WordDictionary}, {@link WordSearch}
     */
    public static List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            addWord(root, word);
        }

        List<String> result = new LinkedList<>();
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, root, result, i, j);
            }
        }

        return new LinkedList<>(result);
    }

    private static void dfs(char[][] board, boolean[][] visited, TrieNode node, List<String> result, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        node = node.child[board[i][j] - 'a'];
        if (visited[i][j] || node == null) {
            return;
        }

        visited[i][j] = true;
        if (node.word != null) {
            result.add(node.word);
            // 防止重复
            node.word = null;
        }

        dfs(board, visited, node, result, i - 1, j);
        dfs(board, visited, node, result, i + 1, j);
        dfs(board, visited, node, result, i, j - 1);
        dfs(board, visited, node, result, i, j + 1);
        visited[i][j] = false;
    }

    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }

    private static void addWord(TrieNode node, String word) {
        for (char cur : word.toCharArray()) {
            if (node.child[cur - 'a'] == null) {
                node.child[cur - 'a'] = new TrieNode();
            }
            node = node.child[cur - 'a'];
        }
        node.word = word;
    }
}
