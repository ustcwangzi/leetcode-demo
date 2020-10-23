package com.wz.string;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].
 * Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
 * 1. 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
 * 2. F.length >= 3;
 * 3. and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
 * Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.
 * Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.
 *
 * Example 1:
 * Input: "123456579"
 * Output: [123,456,579]
 *
 * Example 2:
 * Input: "11235813"
 * Output: [1,1,2,3,5,8,13]
 *
 * Note:
 * 1 <= S.length <= 200
 * S contains only digits.
 */
public class SplitArrayIntoFibonacciSequence {
    public static void main(String[] args) {
        System.out.println(splitIntoFibonacci("123456579"));
    }

    public static List<Integer> splitIntoFibonacci(String s) {
        List<Integer> result = new LinkedList<>();
        dfs(s, 0, result);
        return result;
    }

    /**
     * DFS，提前剪枝
     */
    private static boolean dfs(String s, int start, List<Integer> result) {
        if (start == s.length() && result.size() >= 3) {
            return true;
        }

        for (int i = start; i < s.length(); i++) {
            // 长度大于1，且首字符为0，直接break
            if (s.charAt(start) == '0' && i != start) {
                break;
            }

            long num = Long.parseLong(s.substring(start, i + 1));
            if (num > Integer.MAX_VALUE) {
                return false;
            }

            int size = result.size();
            // 从第三个数字开始，如果新加入的数字不等于前两个数字只和，直接break
            if (size > 2 && num > result.get(size - 2) + result.get(size - 1)) {
                break;
            }

            if (size < 2 || num == result.get(size - 2) + result.get(size - 1)) {
                result.add((int) num);
                if (dfs(s, i + 1, result)) {
                    return true;
                }
                // 移除最后一个元素
                result.remove(result.size() - 1);
            }
        }
        return false;
    }
}
