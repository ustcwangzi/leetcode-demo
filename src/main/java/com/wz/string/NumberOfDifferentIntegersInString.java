package com.wz.string;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string word that consists of digits and lowercase English letters.
 * You will replace every non-digit character with a space. For example, "a123bc34d8ef34" will become " 123  34 8  34".
 * Notice that you are left with some integers that are separated by at least one space: "123", "34", "8", and "34".
 * Return the number of different integers after performing the replacement operations on word.
 * Two integers are considered different if their decimal representations without any leading zeros are different.
 *
 * Example 1:
 * Input: word = "a123bc34d8ef34"
 * Output: 3
 * Explanation: The three different integers are "123", "34", and "8". Notice that "34" is only counted once.
 *
 * Example 2:
 * Input: word = "a1b01c001"
 * Output: 1
 * Explanation: The three integers "1", "01", and "001" all represent the same integer because
 * the leading zeros are ignored when comparing their decimal values.
 *
 * Constraints:
 * 1. 1 <= word.length <= 1000
 * 2. word consists of digits and lowercase English letters.
 */
public class NumberOfDifferentIntegersInString {
    public static void main(String[] args) {
        System.out.println(numDifferentIntegers("leet1234code234"));
        System.out.println(numDifferentIntegers("a123bc34d8ef34"));
        System.out.println(numDifferentIntegers("a1b01c001"));
    }

    /**
     * 遍历 word，使用 set 收集结果，使用 start 记录每个数字的起始位置
     * 若当前是字母并且 start 不是 -1，则将 word[start...i) 放入 set 中，同时将 start 更新为 -1
     * 若当前是数字并且 start 是 -1，则将 start 更新为 i
     * 遍历完成后，若 start 不是 -1，则将 word[start...n-1] 放入 set 中，以免遗漏最后的数字
     */
    public static int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        int start = -1;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i))) {
                if (start != -1) {
                    set.add(rmLeadZero(word.substring(start, i)));
                    start = -1;
                }
                continue;
            }

            if (start == -1) {
                start = i;
            }
        }

        if (start != -1) {
            set.add(rmLeadZero(word.substring(start)));
        }
        return set.size();
    }

    /**
     * 去除开始的 '0'
     */
    private static String rmLeadZero(String s) {
        StringBuilder builder = new StringBuilder();
        boolean leading = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                leading = false;
                builder.append(s.charAt(i));
            } else if (!leading) {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
