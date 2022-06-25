package com.wz.other;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 * For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
 * invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
 * Return an array answer, where answer[i] is the number of words in the given word list words that is valid with respect to the puzzle puzzles[i].
 *
 * Example 1:
 * Input: words = ["aaaa","asas","able","ability","actt","actor","access"], puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * Output: [1,1,3,2,4,0]
 * Explanation:
 * 1 valid word for "aboveyz" : "aaaa"
 * 1 valid word for "abrodyz" : "aaaa"
 * 3 valid words for "abslute" : "aaaa", "asas", "able"
 * 2 valid words for "absoryz" : "aaaa", "asas"
 * 4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
 * There are no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
 *
 * Example 2:
 * Input: words = ["apple","pleas","please"], puzzles = ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
 * Output: [0,1,3,2,0]
 *
 * Constraints:
 * 1. 1 <= words.length <= 10^5
 * 2. 4 <= words[i].length <= 50
 * 3. 1 <= puzzles.length <= 10^4
 * 4. puzzles[i].length == 7
 * 5. words[i] and puzzles[i] consist of lowercase English letters.
 * 6. Each puzzles[i] does not contain repeated characters.
 */
public class NumberOfValidWordsForEachPuzzle {
    public static void main(String[] args) {
        System.out.println(findNumOfValidWords(new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"},
                new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"}));
        System.out.println(findNumOfValidWords(new String[]{"apple", "pleas", "please"}, new String[]{"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"}));
    }

    /**
     * word 里必须包含 puzzle 的首字母，且不包含 puzzle 没有的字母
     * 即 word 里字母的顺序、重复次数都没有关系，本质只是一个字母的集合，因此可以将 word 转换为二进制
     * 使用 map 记录转换后每个二进制的出现次数，遍历 puzzle 及其所有子集，也将其转换为二进制，检查 map 中是否存在
     */
    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int mask = getMask(word, 0);
            map.put(mask, map.getOrDefault(mask, 0) + 1);
        }

        List<Integer> result = new LinkedList<>();
        for (String puzzle : puzzles) {
            int fistCharMask = 1 << (puzzle.charAt(0) - 'a'), puzzleMask = getMask(puzzle, 1);
            int subMask = puzzleMask, count = map.getOrDefault(fistCharMask, 0);
            while (subMask != 0) {
                // 首字母必须相同，因此需要和 fistCharMask 进行'或'运算
                count += map.getOrDefault(fistCharMask | subMask, 0);
                // 子集是减少字母，可以每次减 1，但只能将原来 1 变为 0，不能把 0 变为 1，所以还要和原 puzzleMask 进行'与'运算
                subMask = (subMask - 1) & puzzleMask;
            }
            result.add(count);
        }
        return result;
    }

    private static int getMask(String s, int start) {
        int mask = 0;
        for (int i = start; i < s.length(); i++) {
            mask |= 1 << (s.charAt(i) - 'a');
        }
        return mask;
    }
}
