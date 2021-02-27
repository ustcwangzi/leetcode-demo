package com.wz.greedy;

/**
 * You are given an array of n strings strs, all of the same length.
 * We may choose any deletion indices, and we delete all the characters in those indices for each string.
 * For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef", "vyz"].
 * Suppose we chose a set of deletion indices answer such that after deletions, the final array has its elements in lexicographic order
 * (i.e., strs[0] <= strs[1] <= strs[2] <= ... <= strs[n - 1]). Return the minimum possible value of answer.length.
 *
 * Example 1:
 * Input: strs = ["ca","bb","ac"]
 * Output: 1
 * Explanation:
 * After deleting the first column, strs = ["a", "b", "c"].
 * Now strs is in lexicographic order (ie. strs[0] <= strs[1] <= strs[2]).
 * We require at least 1 deletion since initially strs was not in lexicographic order, so the answer is 1.
 *
 * Example 2:
 * Input: strs = ["xc","yb","za"]
 * Output: 0
 * Explanation:
 * strs is already in lexicographic order, so we do not need to delete anything.
 * Note that the rows of strs are not necessarily in lexicographic order:
 * i.e., it is NOT necessarily true that (strs[0][0] <= strs[0][1] <= ...)
 *
 * Example 3:
 * Input: strs = ["zyx","wvu","tsr"]
 * Output: 3
 * Explanation: We have to delete every column.
 *
 * Constraints:
 * 1. n == strs.length
 * 2. 1 <= n <= 100
 * 3. 1 <= strs[i].length <= 100
 * 4. strs[i] consists of lowercase English letters.
 */
public class DeleteColumnsToMakeSortedII {
    public static void main(String[] args) {
        System.out.println(minDeletionSize(new String[]{"ca", "bb", "ac"}));
        System.out.println(minDeletionSize(new String[]{"zyx", "wvu", "tsr"}));
    }

    /**
     * 对于两个字符串，只要前面字符顺序已经比出来了，后面字符的顺序就不用管了，比如 "bx" 和 "ea"，因为 b 比 e 小，所以 "bx" 比 "ea" 小，
     * 如果看成二维数组的话，在比较 strs[i][j] 和 strs[i+1][j] 时，假如 [0, j-1] 中的某个位置 k，满足 strs[i][k] < strs[i+1][k]，
     * 这里就不用再比了，所以用一个数组 sorted 来标记某相邻的两个字符串之间是否已经有序了
     * 对数组按列进行遍历，内层遍历行，若 sorted[i] 为 false，且 strs[i][j] > strs[i+1][j]，说明当前列需要被删除
     * 当内层循环结束后，此时若 i < m-1，说明是进行的 break，直接 continue 外层循环
     * 否则说明是遍历结束退出的，则再遍历一遍所有行，更新一下 sorted 数组即可
     */
    public static int minDeletionSize(String[] strs) {
        int result = 0, m = strs.length, n = strs[0].length(), i;
        boolean[] sorted = new boolean[m - 1];

        for (int j = 0; j < n; j++) {
            for (i = 0; i < m - 1; i++) {
                if (!sorted[i] && strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    ++result;
                    break;
                }
            }

            if (i < m - 1) {
                continue;
            }
            for (i = 0; i < m - 1; i++) {
                sorted[i] = sorted[i] || strs[i].charAt(j) < strs[i + 1].charAt(j);
            }
        }
        return result;
    }
}
