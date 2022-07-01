package com.wz.dfs;

/**
 * Given a list of words, list of  single letters (might be repeating) and score of every character.
 * Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).
 * It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
 *
 * Example 1:
 * Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
 * Output: 23
 * Explanation:
 * Score  a=1, c=9, d=5, g=3, o=2
 * Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
 * Words "dad" and "dog" only get a score of 21.
 *
 * Example 2:
 * Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
 * Output: 27
 * Explanation:
 * Score  a=4, b=4, c=4, x=5, z=10
 * Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
 * Word "xxxz" only get a score of 25.
 *
 * Example 3:
 * Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
 * Output: 0
 * Explanation:
 * Letter "e" can only be used once.
 *
 * Constraints:
 * 1. 1 <= words.length <= 14
 * 2. 1 <= words[i].length <= 15
 * 3. 1 <= letters.length <= 100
 * 4. letters[i].length == 1
 * 5. score.length == 26
 * 6. 0 <= score[i] <= 10
 * 7. words[i], letters[i] contains only lower case English letters.
 */
public class MaximumScoreWordsFormedByLetters {
    public static void main(String[] args) {
        System.out.println(maxScoreWords(new String[]{"dog", "cat", "dad", "good"}, new char[]{'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'},
                new int[]{1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
        System.out.println(maxScoreWords(new String[]{"xxxz", "ax", "bx", "cx"}, new char[]{'z', 'a', 'b', 'c', 'x', 'x', 'x'},
                new int[]{4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10}));
    }

    /**
     * 对于每个 word 有两种情况：选择 or 不选择，从所有场景中获取最大得分，DFS
     * - 不选择，得分不变，直接遍历下一个 word
     * - 选择，需要将对应字符从 letters 中去除，并累加得分
     */
    public static int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] freq = new int[26];
        for (char ch : letters) {
            freq[ch - 'a']++;
        }
        return dfs(words, score, freq, 0);
    }

    private static int dfs(String[] words, int[] score, int[] freq, int index) {
        if (index == words.length) {
            return 0;
        }
        int unChoose = dfs(words, score, freq, index + 1);

        int sum = 0;
        // 标记剩余字符还能否组成 word
        boolean flag = true;
        for (char cur : words[index].toCharArray()) {
            if (freq[cur - 'a'] == 0) {
                flag = false;
            }
            freq[cur - 'a']--;
            sum += score[cur - 'a'];
        }

        int choose = 0;
        if (flag) {
            choose = sum + dfs(words, score, freq, index + 1);
        }
        // 注意需要将字符进行还原，以免影响下一轮 dfs
        for (char cur : words[index].toCharArray()) {
            freq[cur - 'a']++;
        }
        return Math.max(unChoose, choose);
    }
}
