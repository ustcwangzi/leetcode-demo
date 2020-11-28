package com.wz.dynamicprogramming;

/**
 * Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
 * Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 * Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted.
 * Output the minimum number of steps to get n 'A'.
 *
 * Example 1:
 * Input: 3
 * Output: 3
 * Explanation:
 * Intitally, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 *
 * Note:
 * The n will be in the range [1, 1000].
 */
public class TwoKeysKeyboard {
    public static void main(String[] args) {
        System.out.println(minSteps(3));
        System.out.println(minSteps(6));
    }

    /**
     * 动态规划
     * 当n = 1时，已经有一个A了，不需要其他操作，返回0
     * 当n = 2时，需要复制一次，粘贴一次，返回2
     * 当n = 3时，需要复制一次，粘贴两次，返回3
     * 当n = 4时，这就有两种做法，一种是需要复制一次，粘贴三次，共4步，
     *           另一种是先复制一次，粘贴一次，得到 AA，然后再复制一次，粘贴一次，得到 AAAA，两种方法都是返回4
     * 当n = 5时，需要复制一次，粘贴四次，返回5
     * 当n = 6时，需要复制一次，粘贴两次，得到 AAA，再复制一次，粘贴一次，得到 AAAAAA，共5步，返回5
     * 通过分析上面 6 个的例子，已经可以总结出一些规律，首先对于任意一个 n(除了1以外)，最差的情况就是用 n 步，但有可能是会小于 n 步，
     * 比如 n=6 时，就只用了 5 步，仔细分析一下，发现时先拼成了 AAA，再复制粘贴成了 AAAAAA
     * 分析发现，小模块的长度必须要能整除 n，这样才能拆分。对于 n=6，其实还可先拼出 AA，然后再复制一次，粘贴两次，得到的还是 5。
     * 即可以找出 n 的所有因子，然后这个因子可以当作模块的个数，再算出模块的长度 n/i，再加上模块的个数 dp[i] 来更新结果
     */
    public static int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                }
            }
        }
        return dp[n];
    }
}
