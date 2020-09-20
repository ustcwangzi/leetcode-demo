package com.wz.math;

import java.util.Arrays;

/**
 * Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent the same binary value.
 * If it is possible, return any [i, j] with i+1 < j, such that:
 * A[0], A[1], ..., A[i] is the first part;
 * A[i+1], A[i+2], ..., A[j-1] is the second part, and
 * A[j], A[j+1], ..., A[A.length - 1] is the third part.
 * All three parts have equal binary value.
 * If it is not possible, return [-1, -1].
 * Note that the entire part is used when considering what binary value it represents.  For example,
 * [1,1,0] represents 6 in decimal, not 3.  Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
 *
 * Example 1:
 * Input: [1,0,1,0,1]
 * Output: [0,3]
 *
 * Example 2:
 * Input: [1,1,0,1,1]
 * Output: [-1,-1]
 *
 * Note:
 * 3 <= A.length <= 30000
 * A[i] == 0 or A[i] == 1
 */
public class ThreeEqualParts {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(threeEqualParts(new int[]{1, 0, 1, 0, 1})));
        System.out.println(Arrays.toString(threeEqualParts(new int[]{1, 1, 0, 1, 1})));
    }

    /**
     * 统计1的个数 cntOne，然后计算出每段的1的个数 k=cntOne/3，再用三个变量 start, mid, end 来标记每段的第一个1的位置，
     * 因为想要跳过开头的连续0。再用变量 cnt 来重新在遍历的过程中统计1的个数，在 cnt 为0的时候，一直更新 start，这样可以跳过开头连续0，
     * 当 cnt=k+1 时，更新 mid 为i，因为这是第二段中第一个1的位置，当 cnt=2*k+1 时，更新 end 为i，因为这是第三段中第一个1的位置，
     * 然后此时验证三个区间，即 A[start]，A[mid]，和 A[end] 必须相等，然后三个指针同时向后移动一位，若有任何不相等，就 break 掉，
     * 最后看若 end 等于n，说明三段区间中1的位置都相等，是符合题意的，返回 {start-1, mid}，否则返回 {-1,-1} 即可
     */
    public static int[] threeEqualParts(int[] A) {
        int cntOne = 0, n = A.length;
        for (int num : A) {
            if (num == 1) ++cntOne;
        }
        if (cntOne == 0) {
            return new int[]{0, n - 1};
        }
        if (cntOne % 3 != 0) {
            return new int[]{-1, -1};
        }
        int k = cntOne / 3, start = 0, mid = 0, end = 0, cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (A[i] == 0) {
                continue;
            }
            if (cnt == 0) {
                start = i;
            }
            ++cnt;
            if (cnt == k + 1) {
                mid = i;
            }
            if (cnt == 2 * k + 1) {
                end = i;
                break;
            }
        }
        while (end < n && A[start] == A[mid] && A[mid] == A[end]) {
            ++start;
            ++mid;
            ++end;
        }
        if (end == n) {
            return new int[]{start - 1, mid};
        }
        return new int[]{-1, -1};
    }
}
