package com.wz.greedy;

/**
 * You are given an array of n strings strs, all of the same length.
 * The strings can be arranged such that there is one on each line, making a grid. For example, strs = ["abc", "bce", "cae"] can be arranged as:
 * abc
 * bce
 * cae
 * You want to delete the columns that are not sorted lexicographically. In the above example (0-indexed),
 * columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted while column 1 ('b', 'c', 'a') is not, so you would delete column 1.
 * Return the number of columns that you will delete.
 *
 * Example 1:
 * Input: strs = ["cba","daf","ghi"]
 * Output: 1
 * Explanation: The grid looks as follows:
 *   cba
 *   daf
 *   ghi
 * Columns 0 and 2 are sorted, but column 1 is not, so you only need to delete 1 column.
 *
 * Example 2:
 * Input: strs = ["zyx","wvu","tsr"]
 * Output: 3
 * Explanation: The grid looks as follows:
 *   zyx
 *   wvu
 *   tsr
 * All 3 columns are not sorted, so you will delete all 3.
 *
 * Constraints:
 * 1. n == strs.length
 * 2. 1 <= n <= 100
 * 3. 1 <= strs[i].length <= 1000
 * 4. strs[i] consists of lowercase English letters.
 */
public class DeleteColumnsToMakeSorted {
    public static void main(String[] args) {
        System.out.println(minDeletionSize(new String[]{"cba", "daf", "ghi"}));
        System.out.println(minDeletionSize(new String[]{"zyx", "wvu", "tsr"}));
    }

    /**
     * 按列遍历，若当前位置的字符大于下一行同列上的字符，则需要删掉该列，同时 result++，因为进行了删除所以 break
     */
    public static int minDeletionSize(String[] strs) {
        int m = strs.length, n = strs[0].length(), result = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m - 1; i++) {
                if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }
}
