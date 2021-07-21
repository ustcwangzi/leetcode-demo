package com.wz.bfs;

import com.wz.bfs.WordLadder;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *
 * Note:
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 *
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadderII {
    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(findLadders("hit", "cog", wordList));
    }

    /**
     * BFS与DFS配合使用
     * 使用neighborsMap存储每个节点其下一层的节点，distance存储每个节点到beginWord的距离
     * 那么整体可分为两步进行：
     * 1.BFS得到neighborsMap
     * 2.在neighborsMap的基础上用DFS找到所有路径
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return result;
        }

        Map<String, Integer> distance = new HashMap<>();
        distance.put(beginWord, 0);
        // 1. BFS to get words neighbors
        Map<String, Set<String>> neighborsMap = generateNeighborsMap(wordSet, beginWord, distance);

        if (neighborsMap.size() == 0) {
            return result;
        }

        // 2. DFS to get all paths
        List<String> combinationEntry = new ArrayList<>();
        combinationEntry.add(beginWord);
        getAllPaths(beginWord, endWord, neighborsMap, combinationEntry, result);

        return result;
    }

    /**
     * 使用BFS得到每个节点其下一层的节点，思路与{@link WordLadder}类似
     */
    private static Map<String, Set<String>> generateNeighborsMap(Set<String> wordSet, String beginWord,
                                                                 Map<String, Integer> distance) {
        Map<String, Set<String>> neighborsMap = new HashMap<>();
        Queue<String> currentLayer = new LinkedList<>();
        Queue<String> nextLayer = new LinkedList<>();
        currentLayer.offer(beginWord);

        while (!currentLayer.isEmpty()) {
            String current = currentLayer.poll();
            int currentDistance = distance.get(current);

            for (int i = 0; i < current.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (current.charAt(i) == c) {
                        continue;
                    }
                    char[] array = current.toCharArray();
                    array[i] = c;
                    String word = new String(array);
                    if (wordSet.contains(word) && (!distance.containsKey(word) || distance.get(word) == currentDistance + 1)) {
                        nextLayer.offer(word);

                        Set<String> neighbors = neighborsMap.getOrDefault(current, new HashSet<>());
                        neighbors.add(word);
                        // 下一层节点更新
                        neighborsMap.put(current, neighbors);
                        // 距离更新
                        distance.put(word, currentDistance + 1);
                    }
                }
            }

            // 继续遍历下一层
            if (currentLayer.isEmpty()) {
                currentLayer = nextLayer;
                nextLayer = new LinkedList<>();
            }
        }
        return neighborsMap;
    }

    /**
     * DFS: 遍历neighborsMap获取所有路径
     */
    private static void getAllPaths(String currentWord, String endWord, Map<String, Set<String>> neighborsMap,
                                    List<String> combinationEntry, List<List<String>> result) {
        if (currentWord.equals(endWord)) {
            result.add(new ArrayList<>(combinationEntry));
            return;
        }

        for (String neighbor : neighborsMap.getOrDefault(currentWord, new HashSet<>())) {
            combinationEntry.add(neighbor);
            getAllPaths(neighbor, endWord, neighborsMap, combinationEntry, result);
            combinationEntry.remove(combinationEntry.size() - 1);
        }
    }
}
