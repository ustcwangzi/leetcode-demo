package com.wz.math;

import java.util.Arrays;

/**
 * A string S of lowercase letters is given.  Then, we may make any number of moves.
 * In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.
 * Return the lexicographically smallest string we could have after any number of moves.
 *
 * Example 1:
 * Input: S = "cba", K = 1
 * Output: "acb"
 * Explanation:
 * In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
 * In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
 *
 * Example 2:
 * Input: S = "baaca", K = 3
 * Output: "aaabc"
 * Explanation:
 * In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
 * In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".
 *
 * Note:
 * 1 <= K <= S.length <= 1000
 * S consists of lowercase letters only.
 */
public class OrderlyQueue {
    public static void main(String[] args) {
        System.out.println(orderlyQueue("cba", 1));
        System.out.println(orderlyQueue("baaca", 3));
    }

    /**
     * 一个只有小写字母的字符串，每次可以把前K个字母中的任意一个移动到末尾，返回可以变换成的字母顺序最小的字符串
     * 若 K=1，其实只有K中不同的情况，可以都生成，然后比较出其中最小的那个返回即可。
     * K>1 的时候，其实是可以转换成有序的，即相当于直接对S串进行排序即可
     */
    public static String orderlyQueue(String S, int K) {
        if (K > 1) {
            char[] array = S.toCharArray();
            Arrays.sort(array);
            return new String(array);
        }

        String result = S;
        for (int i = 1; i < S.length(); i++) {
            String tmp = S.substring(i) + S.substring(0, i);
            if (result.compareTo(tmp) > 0) {
                result = tmp;
            }
        }
        return result;
    }
}
