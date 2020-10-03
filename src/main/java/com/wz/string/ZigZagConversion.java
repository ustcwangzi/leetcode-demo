package com.wz.string;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 *
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class ZigZagConversion {
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
    }

    /**
     * 往下走要走 numRows 步，往上走要走 numRows-2 步（除去头尾，因为和往下走重叠）
     * 因此重复以下不步骤即可：
     * 1. 往下走 numRows 步
     * 2. 往上走 numRows-2 步
     */
    public static String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }

        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }

        int index = 0, n = s.length();
        while (index < n) {
            // 往下走
            for (int row = 0; row < numRows && index < n; row++) {
                builders[row].append(s.charAt(index++));
            }
            // 往上走
            for (int row = numRows - 2; row > 0 && index < n; row--) {
                builders[row].append(s.charAt(index++));
            }
        }

        for (int row = 1; row < numRows; row++) {
            builders[0].append(builders[row]);
        }
        return builders[0].toString();
    }
}
