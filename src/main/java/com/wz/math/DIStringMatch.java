package com.wz.math;

import java.util.Arrays;

/**
 * Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.
 * Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:
 * If S[i] == "I", then A[i] < A[i+1]
 * If S[i] == "D", then A[i] > A[i+1]
 *
 * Example 1:
 * Input: "IDID"
 * Output: [0,4,1,3,2]
 *
 * Example 2:
 * Input: "DDI"
 * Output: [3,2,0,1]
 *
 * Note:
 * 1 <= S.length <= 10000
 * S only contains characters "I" or "D".
 */
public class DIStringMatch {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(diStringMatch("IDID")));
        System.out.println(Arrays.toString(diStringMatch("DDI")));
    }

    /**
     * 对于上升来说，可以从0开始累加，而对于下降来说，则可以从n开始下降，这样保证了在结束之前二者绝不会相遇，
     * 到最后一个数字的时候二者相同，再将这个相同数字加入即可，因为返回的数组的个数始终会比给定字符串长度大1个
     */
    public static int[] diStringMatch(String S) {
        int[] result = new int[S.length() + 1];
        int n = S.length(), mn = 0, mx = n, index = 0;
        char[] array = S.toCharArray();
        for (char c : array) {
            if (c == 'I') {
                result[index++] = mn++;
            } else {
                result[index++] = mx--;
            }
        }
        result[index] = mx;
        return result;
    }
}
