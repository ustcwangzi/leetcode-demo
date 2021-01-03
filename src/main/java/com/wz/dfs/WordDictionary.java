package com.wz.dfs;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * Implement the WordDictionary class:
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 *
 * Example:
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * Constraints:
 * 1. 1 <= word.length <= 500
 * 2. word in addWord consists lower-case English letters.
 * 3. word in search consist of  '.' or lower-case English letters.
 * 4. At most 50000 calls will be made to addWord and search.
 */
public class WordDictionary {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    /**
     * 字典树
     */
    public void addWord(String word) {
        TrieNode node = root;
        for (char cur : word.toCharArray()) {
            if (node.child[cur - 'a'] == null) {
                node.child[cur - 'a'] = new TrieNode();
            }
            node = node.child[cur - 'a'];
        }
        node.isWord = true;
    }

    /**
     * DFS
     * 搜索时遇到'.'，需要查找所有的子树，只要有一个返回true，整个搜索就返回true
     */
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private static boolean dfs(String word, int start, TrieNode node) {
        if (start == word.length()) {
            return node.isWord;
        }

        char cur = word.charAt(start);
        if (cur == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.child[i] != null && dfs(word, start + 1, node.child[i])) {
                    return true;
                }
            }
        } else {
            return node.child[cur - 'a'] != null && dfs(word, start + 1, node.child[cur - 'a']);
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }
}
