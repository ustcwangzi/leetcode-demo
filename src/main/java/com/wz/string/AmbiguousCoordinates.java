package com.wz.string;

import java.util.LinkedList;
import java.util.List;

/**
 * We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".
 * Then, we removed all commas, decimal points, and spaces, and ended up with the string S.
 * Return a list of strings representing all possibilities for what our original coordinates could have been.
 * Our original representation never had extraneous zeroes, so we never started with numbers like
 * "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.
 * Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".
 * The final answer list can be returned in any order.  Also note that all coordinates in the final answer have
 * exactly one space between them (occurring after the comma.)
 *
 * Example 1:
 * Input: "(123)"
 * Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
 *
 * Example 2:
 * Input: "(00011)"
 * Output:  ["(0.001, 1)", "(0, 0.011)"]
 * Explanation:
 * 0.0, 00, 0001 or 00.01 are not allowed.
 */
public class AmbiguousCoordinates {
    public static void main(String[] args) {
        System.out.println(ambiguousCoordinates("(123)"));
        System.out.println(ambiguousCoordinates("(00011)"));
    }

    /**
     * 如果字符串为空，那么直接返回空集合；
     * 如果字符串长度大于1，且首尾字符都是0的话，不可分，比如 0xxx0，因为整数长度大于1的话不能以0开头，中间也没法加小数点，因为小数最后一位不能是0；
     * 如果长度大于1，第一位是0，但最后一位不是0，可以在第一个0后面加个小数点返回，这时就必须要加小数点了，因为长度大于1的整数不能以0开头；
     * 如果最后一位是0，说明不能加小数点，直接把当前值返回即可；
     * 最后就是一般情况了，先把原数加入结果，然后遍历中间的每个位置，都加个小数点，所有情况归纳如下：
     * if S == "": return []
     * if S == "0": return [S]
     * if S == "0XXX0": return []
     * if S == "0XXX": return ["0.XXX"]
     * if S == "XXX0": return [S]
     * return [S, "X.XXX", "XX.XX", "XXX.X"...]
     */
    public static List<String> ambiguousCoordinates(String S) {
        List<String> result = new LinkedList<>();
        int n = S.length();
        for (int i = 1; i < n - 1; ++i) {
            List<String> A = findAll(S.substring(1, i)), B = findAll(S.substring(i, n - 1));
            for (String a : A) {
                for (String b : B) {
                    result.add("(" + a + ", " + b + ")");
                }
            }
        }
        return result;
    }

    private static List<String> findAll(String s) {
        int n = s.length();
        List<String> result = new LinkedList<>();
        if (n == 0 || n > 1 && s.charAt(0) == '0' && s.charAt(n - 1) == '0') {
            return result;
        }
        if (n > 1 && s.charAt(0) == '0') {
            result.add("0." + s.substring(1));
            return result;
        }
        result.add(s);
        if (n == 1 || s.charAt(n - 1) == '0') {
            return result;
        }
        for (int i = 1; i < n; ++i) {
            result.add(s.substring(0, i) + "." + s.substring(i, n));
        }
        return result;
    }
}
