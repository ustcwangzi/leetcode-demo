package com.wz.lists;

/**
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 *
 * Example 1:
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 */
public class SubarraySumsDivisibleByK {
    public static void main(String[] args) {
        int[] A = new int[]{4, 5, 0, -2, -3, 1};
        System.out.println(subarraysDivByK(A, 5));
    }

    /**
     * dp[i]表示 0～i 的和，那么有dp[i,j] = dp[j] - dp[i]
     * 对于任何一个子集都能写成如下的形式 dp[i] = a * K + rem，
     * 假设 dp[i] = a1 * K + rem1，dp[j] = a2*K + rem2，那么dp[i,j] = dp[j] - dp[i] = (a2 - a1) * K + (rem2 - rem1)
     * 如果想让 dp[i,j] 能被K整除，很明显，只需要保证 rem2 - rem1 = 0 即可，也就是模K的余数相同
     * 因此可采用以下解决方案：
     * 建立mod数组，长度为K，mod[i] 表示原数组中模K之后余数为i的个数
     * 遍历mod数组，如果mod[i]大于1，那么 res += (mod[i] - 1) * (mod[i]) / 2,相当于里边任意的两个组合相减之后余数结果为0
     * 最后结果加上mod[0]，表示dp[i]原本自己就能被K整除，不需要和别人相减了
     */
    public static int subarraysDivByK(int[] A, int K) {
        int[] mod = new int[K];
        int sum = 0;
        for (int cur : A) {
            sum += cur;
            // sum可能为负数，这里对负数取模处理
            mod[(sum % K + K) % K]++;
        }

        sum = 0;
        for (int cur : mod) {
            if (cur > 1) {
                sum += (cur - 1) * cur / 2;
            }
        }
        sum += mod[0];
        return sum;
    }
}
