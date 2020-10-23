package com.wz.string;

import java.util.HashMap;
import java.util.Map;

/**
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.
 * The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff",
 * then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee",
 * as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing
 * because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement:
 * for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 *
 * Example 1:
 * Input: S = "abcd", indexes = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
 * Output: "eeebffff"
 * Explanation:
 * "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 *
 * Example 2:
 * Input: S = "abcd", indexes = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation:
 * "ab" starts at index 0 in S, so it's replaced by "eee".
 * "ec" doesn't starts at index 2 in the original S, so we do nothing.
 *
 * Constraints:
 * 1. 0 <= S.length <= 1000
 * 2. S consists of only lowercase English letters.
 * 3. 0 <= indexes.length <= 100
 * 4. 0 <= indexes[i] < S.length
 * 5. sources.length == indexes.length
 * 6. targets.length == indexes.length
 * 7. 1 <= sources[i].length, targets[i].length <= 50
 * 8. sources[i].length and targets[i].length consist of only lowercase English letters.
 */
public class FindAndReplaceInString {
    public static void main(String[] args) {
        System.out.println(findReplaceString("abcd", new int[]{0, 2}, new String[]{"ab", "ec"}, new String[]{"eee", "fff"}));
    }

    /**
     * 若某个坐标位置起，源字符串数组中对应位置的字符串出现了，将其替换为目标字符串
     * 使用 map 保存 indexes 数组中的数字跟其位置坐标组成的映射，当可以查找到源字符串位置的时候，才添加到 map 中
     * 遍历原字符串S，对于每个位置，在 map 中查找，如果发现需要替换，就把目标字符串提取出来，加入结果中，此时 i 需要加上源字符串的长度
     * 若不需要替换，则直接将字符加入结果中，然后 i 移动到下一个位置
     */
    public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            // 只添加可以替换的位置
            if (S.startsWith(sources[i], indexes[i])) {
                map.put(indexes[i], i);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < S.length(); ) {
            if (map.containsKey(i)) {
                // 替换
                builder.append(targets[map.get(i)]);
                i += sources[map.get(i)].length();
            } else {
                builder.append(S.charAt(i));
                i++;
            }
        }
        return builder.toString();
    }
}
