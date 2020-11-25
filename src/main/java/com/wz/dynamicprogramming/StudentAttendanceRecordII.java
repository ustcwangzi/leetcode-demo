package com.wz.dynamicprogramming;

/**
 * Given a positive integer n, return the number of all possible attendance records with length n,
 * which will be regarded as rewardable. The answer may be very large, return it after mod 10^9 + 7.
 * A student attendance record is a string that only contains the following three characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * Example 1:
 * Input: n = 2
 * Output: 8
 * Explanation:
 * There are 8 records with length 2 will be regarded as rewardable:
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" won't be regarded as rewardable owing to more than one absent times.
 *
 * Note: The value of n won't exceed 100,000.
 */
public class StudentAttendanceRecordII {
    public static void main(String[] args) {
        System.out.println(checkRecord(2));
    }

    /**
     * P[i] 表示数组 [0,i] 范围内以 P 结尾的所有排列方式，L[i] 表示数组 [0,i] 范围内以 L 结尾的所有排列方式，
     * A[i] 表示数组 [0,i] 范围内以 A 结尾的所有排列方式，那么最终所求的就是 P[n-1] + L[n-1] + A[n-1]
     * 首先来看 P 数组的，P 字符没有任何限制条件，可以跟在任何一个字符后面，所以有 P[i] = A[i-1] + P[i-1] + L[i-1]
     * 再来看 L 数组的，L 字符唯一的限制条件是不能有超过两个连续的 L，那么在 P 和 L 字符后面可以加 1 一个 L，
     * 如果前一个字符是 L，要看再前面的一位是什么字符，如果是 P 或 A，可以加 L，如果是 L，就不能再加了，否则就连续 3 个了，
     * 所以有 L[i] = A[i-1] + P[i-1] + A[i-2] + P[i-2]
     * 最后来看 A 数组的，这个比较麻烦，字符 A 的限制条件是整个字符串最多只能有 1 个 A，那么当前一个字符是 A 的话，就不能再加 A，
     * 当前一个字符是 P 或者 L 的话，要确定之前从没有 A 出现过，才能加上 A。那么实际上还需要定义两个数组 P1, L1,
     * P1[i] 表示数组 [0,i] 范围内以 P 结尾的不包含 A 的所有排列方式，L1[i] 表示数组 [0,i] 范围内以 L 结尾的不包含 A 的所有排列方式，
     * 根据前两种情况不难推出 P1 和 L1 的递推公式，再加上 A 的递推公式如下：
     * A[i] = P1[i-1] + L1[i-1]
     * P1[i] = P1[i-1] + L1[i-1]
     * L1[i] = P1[i-1] + P1[i-2]
     * 将第二第三个等式多次带入第一个等式，就可以将 P1 和 L1 消掉，可以化简为：
     * A[i] = A[i-1] + A[i-2] + A[i-3]
     */
    public static int checkRecord(int n) {
        int mod = 1000000007;
        int[] P = new int[n], L = new int[n], A = new int[n];
        P[0] = 1;
        L[0] = 1;
        A[0] = 1;

        if (n > 1) {
            L[1] = 3;
            A[1] = 2;
        }
        if (n > 2) {
            A[2] = 4;
        }

        for (int i = 1; i < n; ++i) {
            P[i] = ((P[i - 1] + L[i - 1]) % mod + A[i - 1]) % mod;
            if (i > 1) L[i] = ((A[i - 1] + P[i - 1]) % mod + (A[i - 2] + P[i - 2]) % mod) % mod;
            if (i > 2) A[i] = ((A[i - 1] + A[i - 2]) % mod + A[i - 3]) % mod;
        }

        return ((A[n - 1] + P[n - 1]) % mod + L[n - 1]) % mod;
    }
}
