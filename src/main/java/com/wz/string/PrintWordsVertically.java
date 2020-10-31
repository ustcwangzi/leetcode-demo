package com.wz.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s. Return all the words vertically in the same order in which they appear in s.
 * Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
 * Each word would be put on only one column and that in one column there will be only one word.
 *
 * Example 1:
 * Input: s = "HOW ARE YOU"
 * Output: ["HAY","ORO","WEU"]
 * Explanation: Each word is printed vertically.
 *  "HAY"
 *  "ORO"
 *  "WEU"
 *
 * Example 2:
 * Input: s = "TO BE OR NOT TO BE"
 * Output: ["TBONTB","OEROOE","   T"]
 * Explanation: Trailing spaces is not allowed.
 * "TBONTB"
 * "OEROOE"
 * "   T"
 *
 * Constraints:
 * 1. 1 <= s.length <= 200
 * 2. s contains only upper case English letters.
 * 3. It's guaranteed that there is only one space between 2 words.
 */
public class PrintWordsVertically {
    public static void main(String[] args) {
        System.out.println(printVertically("TO BE OR NOT TO BE"));
    }

    /**
     * 用" "分割字符串，然后统计最长字符串的长度，双层循环遍历每个字符串，依次取出字符串对应位置的字符
     * 注意，逆序遍历字符串，当取到的结果不为空的时候才加入空格，加入结果集时再逆序，用这种方式来处理结果集尾部不包含空字符的case
     */
    public static List<String> printVertically(String s) {
        String[] array = s.split(" ");
        int maxLength = 0;
        for (String str : array) {
            maxLength = Math.max(maxLength, str.length());
        }

        List<String> result = new ArrayList<>(maxLength);
        for (int i = 0; i < maxLength; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = array.length - 1; j >= 0; j--) {
                if (array[j].length() > i) {
                    builder.append(array[j].charAt(i));
                } else if (builder.length() != 0) {
                    builder.append(" ");
                }
            }
            result.add(builder.reverse().toString());
        }

        return result;
    }
}
