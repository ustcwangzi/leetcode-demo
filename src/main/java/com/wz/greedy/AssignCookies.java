package com.wz.greedy;

import java.util.Arrays;

/**
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with;
 * and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 *
 * Example 1:
 * Input: g = [1,2,3], s = [1,1]
 * Output: 1
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 * And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
 * You need to output 1.
 *
 * Example 2:
 * Input: g = [1,2], s = [1,2,3]
 * Output: 2
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 * You have 3 cookies and their sizes are big enough to gratify all of the children,
 * You need to output 2.
 *
 * Constraints:
 * 1. 1 <= g.length <= 3 * 10^4
 * 2. 0 <= s.length <= 3 * 10^4
 * 3. 1 <= g[i], s[j] <= 2^31 - 1
 */
public class AssignCookies {
    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
        System.out.println(findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3}));
    }

    /**
     * 对两个数组进行排序，然后使用 i 和 j 分别指向两个数组的开始位置，接着进行遍历
     * 若 g[i] <= s[j]，则说明满足条件，i、j 右移，同时结果加一，否则 i 不变，j 右移
     */
    public static int findContentChildren(int[] g, int[] s) {
        if (s.length == 0) {
            return 0;
        }

        Arrays.parallelSort(g);
        Arrays.parallelSort(s);

        int i = 0, j = 0, result = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                j++;
                result++;
            } else {
                j++;
            }
        }
        return result;
    }
}
