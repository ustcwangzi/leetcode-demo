package com.wz.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 *
 * Example 1:
 * Input: n = 13
 * Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
 *
 * Example 2:
 * Input: n = 2
 * Output: [1,2]
 *
 * Constraints:
 * 1 <= n <= 5 * 10^4
 */
public class LexicographicalNumbers {
    public static void main(String[] args) {
        System.out.println(lexicalOrder(13));
    }

    /**
     * 对 1～n 按照字典序进行排序
     * 可以将每个数字想象成一棵树，那么最终结果就是树的先序遍历
     * 　　　　1        2        3    ...
     *       /\       /\       /\
     *    10 ...19  20...29  30...39   ....
     */
    public static List<Integer> lexicalOrder(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 1; i < 10; i++) {
            dfs(i, n, result);
        }
        return result;
    }

    private static void dfs(int cur, int n, List<Integer> result) {
        if (cur > n) return;
        result.add(cur);
        for (int i = 0; i < 10; i++) {
            dfs(cur * 10 + i, n, result);
        }
    }
}
