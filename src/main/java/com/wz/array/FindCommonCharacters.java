package com.wz.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array A of strings made only from lowercase letters, return a list of all characters that show up
 * in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings
 * but not 4 times, you need to include that character three times in the final answer.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: ["bella","label","roller"]
 * Output: ["e","l","l"]
 *
 * Example 2:
 * Input: ["cool","lock","cook"]
 * Output: ["c","o"]
 */
public class FindCommonCharacters {
    public static void main(String[] args) {
        String[] A = new String[]{"bella", "label", "roller"};
        System.out.println(commonChars(A));

        A = new String[]{"cool", "lock", "cook"};
        System.out.println(commonChars(A));
    }

    /**
     * 使用一个长度为26的数组count，记录公共字符的出现次数，初始值设为整型最大值，
     * 循环处理A中的字符串，使用长度为26的数组local，记数每个字符串中的字符出现次数，然后比较count和local中对应元素值大小（字符出现次数），
     * 取较小（求交集）的一个重新赋值给count中的对应位置，因为公共出现次数必然是较小的，最后将count数组中大于0的数转成字符串添加到结果集中。
     */
    public static List<String> commonChars(String[] A) {
        int[] count = new int[26];
        Arrays.fill(count, Integer.MAX_VALUE);
        for (String str : A) {
            int[] local = new int[26];
            // 每个字符串中出现的次数
            for (char ch : str.toCharArray()) {
                local[ch - 'a']++;
            }
            // 公共出现次数
            for (int i = 0; i < 26; i++) {
                count[i] = Math.min(local[i], count[i]);
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                result.add(String.valueOf((char) ('a' + i)));
                count[i]--;
            }
        }
        return result;
    }
}
