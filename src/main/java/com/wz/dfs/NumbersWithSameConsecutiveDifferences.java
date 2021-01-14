package com.wz.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
 * Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: n = 3, k = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 *
 * Example 2:
 * Input: n = 2, k = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 */
public class NumbersWithSameConsecutiveDifferences {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(numsSameConsecDiff(3, 7)));
    }

    /**
     * DFS
     * 求所有的 n 位数，使得其中每一个数中的每相邻两个位的数字之差为 k
     * 需要注意的是 n==1 时要考虑0，以及 k==0 时注意剪枝
     */
    public static int[] numsSameConsecDiff(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = n == 1 ? 0 : 1; i < 10; i++) {
            dfs(0, i, 1, n, k, list);
        }

        int[] result = new int[list.size()];
        int i = 0;
        for (int num : list) {
            result[i++] = num;
        }
        return result;
    }

    private static void dfs(int num, int digit, int index, int n, int k, List<Integer> result) {
        if (digit < 0 || digit > 9) {
            return;
        }
        num = num * 10 + digit;

        if (index == n) {
            result.add(num);
            return;
        }

        dfs(num, digit + k, index + 1, n, k, result);
        if (k > 0) {
            dfs(num, digit - k, index + 1, n, k, result);
        }
    }

}
