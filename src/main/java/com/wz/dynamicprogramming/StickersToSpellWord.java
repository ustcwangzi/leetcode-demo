package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * We are given N different types of stickers. Each sticker has a lowercase English word on it.
 * You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.
 * You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 * What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.
 *
 * Example 1:
 * Input:
 * ["with", "example", "science"], "thehat"
 * Output:
 * 3
 * Explanation:
 * We can use 2 "with" stickers, and 1 "example" sticker.
 * After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 * Also, this is the minimum number of stickers necessary to form the target string.
 *
 * Example 2:
 * Input:
 * ["notice", "possible"], "basicbasic"
 * Output:
 * -1
 * Explanation:
 * We can't form the target "basicbasic" from cutting letters from the given stickers.
 *
 * Note:
 * 1. stickers has length in the range [1, 50].
 * 2. stickers consists of lowercase English words (without apostrophes).
 * 3. target has length in the range [1, 15], and consists of lowercase English letters.
 * 4. In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.
 * 5. The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.
 */
public class StickersToSpellWord {
    public static void main(String[] args) {
        System.out.println(minStickers(new String[]{"with", "example", "science"}, "thehat"));
    }

    /**
     * dp[i] 表示组成第 i 个子集合所需要的最少的 sticker 个数，注意这里是子集合，而不是子串。
     * 长度为 n 的字符串共有 2^n 次方个子集合，比如字符串 "ab"，就有 4 个子集合，分别是 "", "a", "b", "ab"。
     * 字符串 "abc" 就有 8 个子集合，如果用 0 到 7 来分别对应其子集合，就有：
     *      abc   subset
     * 0    000     ""
     * 1    001     c
     * 2    010     b
     * 3    011     bc
     * 4    100     a
     * 5    101     ac
     * 6    110     bb
     * 7    111     abc
     * 可以看到 0 到 7 的二进制数的每一位上的 0 和 1 就相当于 mask，是 1 的话就选上该位对应的字母，000 就表示都不选，就是空集，
     * 111 就表示都选，就是 abc，那么只要将所有子集合的 dp 值都算出来，最后返回 dp 数组的最后一个位置上的数字，就是和目标子串相等的子集合
     * 以下面这个简单的例子来讲解：
     * stickers = ["ab", "bc", "ac"], target = "abc"
     * 之前说了 abc 的共有 8 个子集合，所以 dp 数组的长度为 8，除了 dp[0] 初始化为 0 之外，其余的都初始化为 MAX_VALUE，
     * 然后开始逐个更新 dp 数组的值，目标是从 sticker 中取出字符，来拼出子集合，所以如果当前遍历到的 dp 值仍为 MAX_VALUE 的话，
     * 说明该子集合无法被拼出来，自然无法再其基础上去拼别的子集合，直接跳过。否则就遍历所有的 sticker，让变量 cur 等于 i，
     * 说明此时是在第 i 个子集合的基础上去 reach 其他的子集合，遍历当前 sticker 的每一个字母，对于 sticker 的每个字母，
     * 都扫描一遍 target 的所有字符，如果 target 里有这个字符，且该字符未出现在当前 cur 位置的子集合中，则将该字符加入子集合中
     *比如当前的 cur 是 3，二进制为 011，对应的子集合是 "bc"，此时如果遍历到的 sticker 是 "ab"，那么遇到 "a" 时，
     * 知道 target 中是有 "a" 的，然后看 "bc" 中包不包括 "a"，判断方法是看 (cur >> k) & 1 是否为0，
     * 因为子集合是跟二进制对应的，"bc" 就对应着 011，第一个 0 就表示 "a" 缺失，所以想看哪个字符，就提取出该字符对应的二进制位，
     * 提取方法就是 cur >> k，提取出来的值与上 1 就知道该位是 0 还是 1 了，如果是 0，表示缺失，那么把该位变为 1，
     * 通过 cur |= 1 << k 来实现，此时 cur 变为 7，二进制为 111，对应的子集合是 "abc"，更新 dp[cur] 为 dp[cur] 和 dp[i]+1 中的较大值，
     * 最后循环结束后，如果 "abc" 对应的 dp 值还是 MAX_VALUE，就返回-1，否则返回其 dp 值
     */
    public static int minStickers(String[] stickers, String target) {
        int n = target.length(), m = 1 << n;
        int[] dp = new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < m; ++i) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }

            for (String sticker : stickers) {
                int cur = i;
                char[] array = sticker.toCharArray();
                for (char c : array) {
                    for (int k = 0; k < n; ++k) {
                        if (target.charAt(k) == c && ((cur >> k) & 1) == 0) {
                            cur |= 1 << k;
                            break;
                        }
                    }
                }
                dp[cur] = Math.min(dp[cur], dp[i] + 1);
            }
        }

        return dp[m - 1] == Integer.MAX_VALUE ? -1 : dp[m - 1];
    }
}
