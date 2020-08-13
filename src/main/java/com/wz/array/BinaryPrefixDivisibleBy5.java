package com.wz.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A of 0s and 1s, consider N_i: the i-th subarray from A[0] to A[i] interpreted as a binary number
 * (from most-significant-bit to least-significant-bit.)
 * Return a list of booleans answer, where answer[i] is true if and only if N_i is divisible by 5.
 *
 * Example 1:
 * Input: [0,1,1]
 * Output: [true,false,false]
 * Explanation:
 * The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.
 * Only the first number is divisible by 5, so answer[0] is true.
 *
 * Example 2:
 * Input: [0,1,1,1,1,1]
 * Output: [true,false,false,false,true,false]
 *
 * Note:
 * 1 <= A.length <= 30000
 * A[i] is 0 or 1
 */
public class BinaryPrefixDivisibleBy5 {
    public static void main(String[] args) {
        int[] A = new int[]{0, 1, 1};
        System.out.println(prefixesDivBy5(A));

        A = new int[]{0, 1, 1, 1, 1, 1};
        System.out.println(prefixesDivBy5(A));
    }

    /**
     * 不能将数组转化成 int, 因为数组长度 30000 超出了 int 的范围(32)
     * 注意到能整除 5 的只有个位为 5 或者 0 的数, 因此只需要统计个位数(% 10)即可
     */
    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> result = new ArrayList<>(A.length);
        int num = 0;
        for (int cur : A) {
            num = (num << 1) + cur;
            num %= 10;
            result.add(num % 5 == 0);
        }

        return result;
    }
}
