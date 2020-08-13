package com.wz.array;

/**
 * A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0),
 * followed by some number of '1's (also possibly 0.)
 * We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.
 * Return the minimum number of flips to make S monotone increasing.
 *
 * Example 1:
 * Input: "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 *
 * Example 2:
 * Input: "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 *
 * Example 3:
 * Input: "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 */
public class FlipStringToMonotoneIncreasing {
    public static void main(String[] args) {
        String S = "00110";
        System.out.println(minFlipsMonoIncr(S));
        System.out.println(minFlipsMonoIncr2(S));

        S = "010110";
        System.out.println(minFlipsMonoIncr(S));
        System.out.println(minFlipsMonoIncr2(S));

        S = "00011000";
        System.out.println(minFlipsMonoIncr(S));
        System.out.println(minFlipsMonoIncr2(S));
    }

    /**
     * 动态规划
     * count1[i] 表示 [0, i-1] 子串内将1转为0的个数，从而形成单调字符串
     * count0[j] 表示 [j, n-1] 子串内将0转为1的个数，从而形成单调字符串
     * 这样最终在某个位置使得 count0[i]+count1[i] 最小的时候，就是变为单调串的最优解
     */
    public static int minFlipsMonoIncr(String S) {
        int n = S.length();
        int[] count0 = new int[n + 1], count1 = new int[n + 1];
        for (int i = 1, j = n - 1; j >= 0; i++, j--) {
            count1[i] = count1[i - 1] + (S.charAt(i - 1) == '0' ? 0 : 1);
            count0[j] = count0[j + 1] + (S.charAt(j) == '1' ? 0 : 1);
        }

        int result = n;
        for (int i = 0; i <= n; i++) {
            result = Math.min(result, count1[i] + count0[i]);
        }

        return result;
    }

    /**
     * 用 count1 来记录1出现的次数，同时 result 表示使到当前位置的子串变为单调串的翻转次数，对于当前元素，
     * 如果是1，直接 count1++ ；如果是0，有两个选择，要么将其转为1，需要 result+1 次，要么将之前所有的1转换为0，则需要 count1 次
     */
    public static int minFlipsMonoIncr2(String S) {
        int result = 0, count1 = 0;
        for (char c : S.toCharArray()) {
            if (c == '1') {
                count1++;
            } else {
                result = Math.min(result + 1, count1);
            }
        }
        return result;
    }
}
