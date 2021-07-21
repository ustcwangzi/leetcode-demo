package com.wz.bfs;

import java.util.*;

/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * Suppose we need to investigate a mutation from a gene string start to a gene string end
 * where one mutation is defined as one single character changed in the gene string.
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 * Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end.
 * If there is no such a mutation, return -1.
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 * <p>
 * Example 1:
 * Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 * <p>
 * Example 2:
 * Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * Output: 2
 * <p>
 * Example 3:
 * Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 * Output: 3
 * <p>
 * Constraints:
 * 1. start.length == 8
 * 2. end.length == 8
 * 3. 0 <= bank.length <= 10
 * 4. bank[i].length == 8
 * 5. start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */
public class MinimumGeneticMutation {
    public static void main(String[] args) {
        System.out.println(minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));
    }

    /**
     * BFS
     * 对于每个字符，都尝试修改某一位将其换为一个新的字符，然后和 end 进行比较，若相等则返回结果
     * 否则如果这个新字符串在 bank 中存在，说明变换是合法的，加入 queue 中等待下一次遍历，并将其从 bank 中移除
     * 同时需要在本次位置尝试修改结束之后将之前字符进行还原。每遍历一层，就将结果加一
     */
    public static int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) {
            return -1;
        }

        char[] gens = new char[]{'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] array = queue.poll().toCharArray();
                for (int j = 0; j < array.length; j++) {
                    char old = array[j];
                    // 尝试对位置 j 的字符进行替换
                    for (char ch : gens) {
                        array[j] = ch;
                        String update = new String(array);
                        if (end.equals(update)) {
                            return count + 1;
                        }
                        if (bankSet.contains(update)) {
                            queue.offer(update);
                            bankSet.remove(update);
                        }
                    }
                    // 对位置 j 进行还原
                    array[j] = old;
                }
            }
            count++;
        }
        return -1;
    }
}
