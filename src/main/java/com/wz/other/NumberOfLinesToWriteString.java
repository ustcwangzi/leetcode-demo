package com.wz.other;

import java.util.Arrays;

/**
 * You are given a string s of lowercase English letters and an array widths denoting how many pixels wide each lowercase English letter is.
 * Specifically, widths[0] is the width of 'a', widths[1] is the width of 'b', and so on.
 * You are trying to write s across several lines, where each line is no longer than 100 pixels. Starting at the beginning of s,
 * write as many letters on the first line such that the total width does not exceed 100 pixels. Then, from where you stopped in s,
 * continue writing as many letters as you can on the second line. Continue this process until you have written all of s.
 * Return an array result of length 2 where:
 * result[0] is the total number of lines.
 * result[1] is the width of the last line in pixels.
 *
 * Example 1:
 * Input: widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10], s = "abcdefghijklmnopqrstuvwxyz"
 * Output: [3,60]
 * Explanation: You can write s as follows:
 * abcdefghij  // 100 pixels wide
 * klmnopqrst  // 100 pixels wide
 * uvwxyz      // 60 pixels wide
 * There are a total of 3 lines, and the last line is 60 pixels wide.
 *
 * Example 2:
 * Input: widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10], s = "bbbcccdddaaa"
 * Output: [2,4]
 * Explanation: You can write s as follows:
 * bbbcccdddaa  // 98 pixels wide
 * a            // 4 pixels wide
 * There are a total of 2 lines, and the last line is 4 pixels wide.
 *
 * Constraints:
 * 1. widths.length == 26
 * 2. 2 <= widths[i] <= 10
 * 3. 1 <= s.length <= 1000
 * 4. s contains only lowercase English letters.
 */
public class NumberOfLinesToWriteString {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(numberOfLines(new int[]
                        {4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                "bbbcccdddaaa")));
    }

    /**
     * widths 代表 a-z 的每个字符宽度，每一行最大宽度为 100，求 s 可以排多少行以及最后一行的宽度
     * 直接遍历 s，若当前宽度超过 100，则行数增加，同时宽度重置为 0
     */
    public static int[] numberOfLines(int[] widths, String s) {
        int curLen = 0, row = 1, i = 0;
        while (i < s.length()) {
            if (curLen + widths[s.charAt(i) - 'a'] > 100) {
                row++;
                curLen = 0;
            } else {
                curLen += widths[s.charAt(i) - 'a'];
                i++;
            }
        }
        return new int[]{row, curLen};
    }
}
