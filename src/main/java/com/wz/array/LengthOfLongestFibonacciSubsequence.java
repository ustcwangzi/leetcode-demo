package com.wz.array;

import java.util.HashSet;
import java.util.Set;

/**
 * A sequence X_1, X_2, ..., X_n is fibonacci-like if:
 * n >= 3
 * X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
 * Given a strictly increasing array A of positive integers forming a sequence,
 * find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.
 * (Recall that a subsequence is derived from another sequence A by deleting any number of elements
 * (including none) from A, without changing the order of the remaining elements.
 * For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)
 *
 * Example 1:
 * Input: [1,2,3,4,5,6,7,8]
 * Output: 5
 * Explanation:
 * The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 *
 * Example 2:
 * Input: [1,3,7,11,12,14,18]
 * Output: 3
 * Explanation:
 * The longest subsequence that is fibonacci-like:
 * [1,11,12], [3,11,14] or [7,11,18].
 *
 * Note:
 * 3 <= A.length <= 1000
 * 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 */
public class LengthOfLongestFibonacciSubsequence {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(lenLongestFibSubseq(A));

        A = new int[]{1, 3, 7, 11, 12, 14, 18};
        System.out.println(lenLongestFibSubseq(A));
    }

    /**
     * 遍历所有的两个数字的组合，以其为起始的两个数字，然后再用个 while 循环，不断检测两个数字之和是否存在，那么为了快速查找，
     * 要使用一个 HashSet 先把原数组中所有的数字存入，这样就可以进行常数级时间查找了，每找到一个，count 自增1，然后更新结果 result
     */
    public static int lenLongestFibSubseq(int[] A) {
        Set<Integer> set = new HashSet<>(A.length);
        for (int value : A) {
            set.add(value);
        }

        int result = 0;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int a = A[i], b = A[j], c = a + b, count = 2;
                // 更新三个值继续判断
                while (set.contains(c)) {
                    a = b;
                    b = c;
                    c = a + b;
                    count++;
                }
                result = Math.max(result, count);
            }
        }

        return result > 2 ? result : 0;
    }
}
