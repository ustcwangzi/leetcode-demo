package com.wz.lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list.
 *
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Example 1:
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder {
    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(ladderLength("hit", "cog", wordList));

        wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        System.out.println(ladderLength("hit", "cog", wordList));
    }

    /**
     * 广度优先搜索BFS
     * 对于每个元素都找到它的相邻节点（即编辑距离为1的元素），然后放入visited中，同时从wordList中移除
     * 进行下一次的循环，每循环一次，其实就是从beginWord向endWord变化的一步，因此distance++
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // 本次循环可作为起点的元素
        List<String> visited = new ArrayList<>();
        visited.add(beginWord);

        int distance = 1;
        while (!visited.contains(endWord)) {
            // 编辑距离为1的元素
            List<String> oneDiff = new ArrayList<>();
            for (String str : visited) {
                for (int i = 0; i < str.length(); i++) {
                    char[] array = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        array[i] = c;
                        String word = new String(array);
                        if (wordSet.contains(word)) {
                            oneDiff.add(word);
                            wordSet.remove(word);
                        }
                    }
                }
            }

            if (oneDiff.size() == 0) {
                return 0;
            }
            distance++;
            visited = oneDiff;
        }

        return distance;
    }
}
