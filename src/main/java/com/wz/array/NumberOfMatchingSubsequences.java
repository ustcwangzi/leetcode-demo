package com.wz.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 */
public class NumberOfMatchingSubsequences {
    public static void main(String[] args) {
        String[] words = new String[]{"a", "bb", "acd", "ace"};
        System.out.println(numMatchingSubseq("abcde", words));
    }

    /**
     * words[i]是S的子序列，则words[i]中每个字母一定出现在S中，且在S中保留相对先后顺序，
     * 例如”acd”是”abcde”的子序列，说明在S中要满足”a”在”c”前面，”c”在”d”前面，因此判断words[i]是否是S的子序列，
     * 只需要判断words[i]中每个字母在S中的下标是否满足相对先后顺序即可。
     * 因此可以维护一个下标映射indexMap，记录在S中每个字母出现的下标位置，然后再去看words[i]中的每个字母，是否能在S中满足相对先后顺序即可。
     * 例如判断”adb”是否是”abcbcede”的子序列，首先”a”在S中的下标为[0]，”d”在S中的下标为[6]，”b”在S中的下标为[1, 3]，
     * 发现在”adb”中b在d后面，因此在b的下标数组中查找是否存在下标大于6，但是在S中b的下标都小于6，因此可以判断”adb”不是”abcbcede”的子序列
     */
    public static int numMatchingSubseq(String S, String[] words) {
        // S中的字母出现的下标位置映射
        Map<Character, List<Integer>> indexMap = new HashMap<>();
        int index = 0;
        for (char c : S.toCharArray()) {
            if (indexMap.containsKey(c)) {
                indexMap.get(c).add(index++);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(index++);
                indexMap.put(c, indexList);
            }
        }

        int result = 0;
        // 对于每个单词，判断后面的字母在S中的位置是否大于前面的字母在S中的位置
        for (String word : words) {
            int curIndex = 0;
            for (char c : word.toCharArray()) {
                curIndex = binarySearch(indexMap.get(c), curIndex);
                if (curIndex == -1) {
                    break;
                }
                // 这里需要++，因为需要判断更后面的位置
                curIndex++;
            }
            if (curIndex != -1) {
                result++;
            }
        }

        return result;
    }

    /**
     * 二分查找list中第一个大于等于index的元素
     */
    private static int binarySearch(List<Integer> list, int index) {
        if (list == null || list.size() == 0 || list.get(list.size() - 1) < index) {
            return -1;
        }

        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < index) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return list.get(right) >= index ? list.get(right) : -1;
    }
}
