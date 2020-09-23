package com.wz.math;

import java.util.*;

/**
 * Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.
 * Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].
 *
 * Example 1:
 * Input: [1,17,8]
 * Output: 2
 * Explanation:
 * [1,8,17] and [17,8,1] are the valid permutations.
 *
 * Example 2:
 * Input: [2,2,2]
 * Output: 1
 */
public class NumberOfSquarefulArrays {
    public static void main(String[] args) {
        System.out.println(numSquarefulPerms(new int[]{1, 17, 8}));
        System.out.println(numSquarefulPerms(new int[]{2, 2, 2}));
    }

    /**
     * 先对每一个数字a，找出能够放在a后面的数字b，即a + b是一个完全平方数，找的方法就是两层循环的遍历
     * 使用动态规划，dp[mask][i]表示使用mask指示的子数组，以第i个数组为结尾的合法排列的方法。
     * mask暗示的数组意思是，如果mask使用二进制表示为001010011100，表示使用第2,3,4，7,9个数组组成的子数组，
     * 即二进制的每一位代表原数组的一个位置，1代表使用这个位置上的数组，0代表不使用。
     * 然后使用两层循环遍历，外层0~2^12-1，内层0~len（数组长度）
     * dp[mask][i] = sum{ dp[p_mask][last] }，p_mask是mask除去第i个数字之后的子数组，last是p_mask所代表的的子数组合法排列的最后一个数字
     * 最后返回sum{ dp[2^12-1][last] }
     * 这里要注意的是必须除去重复的情况，即如果给定的数组是[2, 2, 2]，那么合法且不重复的排列只有一个，
     * 但是按照上面的动态规划会照出来六个，也就是三个2不同的排列，除去重复主要使用两个规则：
     * 1.同一个数字在结尾处只能出现一次，也就是说sum{ dp[2^12-1][last] }中的last如果重复了，那么就跳过，不把它加到结果中去
     * 2.除开最后一个数字last，如果其他位置的数字有重复的，那么就除以重复数量的全排列数目，对于所有的数字都要处理一遍
     */
    public static int numSquarefulPerms(int[] A) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int len = A.length;
        for (int i = 0; i < len; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                int root = (int) Math.sqrt(A[i] + A[j]);
                if (root * root == A[i] + A[j]) {
                    list.add(j);
                }
            }
            map.put(i, list);
        }

        int[][] record = new int[1 << len][len];
        for (int mask = 0; mask < (1 << len); mask++) {
            for (int i = 0; i < len; i++) {
                //if the mask don't contain this number
                if ((mask & (1 << i)) == 0) {
                    continue;
                }
                //find the parents mask
                int p_mask = (mask ^ (1 << i));
                if (p_mask == 0) {
                    record[mask][i] = 1;
                    continue;
                }
                //traverse every tail
                for (int last = 0; last < len; last++) {
                    //if the p_mask don't contains this tail
                    if ((p_mask & (1 << last)) == 0) {
                        continue;
                    }
                    if (map.get(last).contains(i)) {
                        record[mask][i] += record[p_mask][last];
                    }
                }
            }
        }

        int result = 0, mask = (1 << len) - 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (!set.contains(A[i])) {
                int cur = record[mask][i];
                int p_mask = (mask) ^ (1 << i);
                Map<Integer, Integer> dup = new HashMap<>();
                for (int it = 0; it < len; it++) {
                    if ((p_mask & (1 << it)) != 0) {
                        dup.put(A[it], dup.getOrDefault(A[it], 0) + 1);
                    }
                }
                for (int key : dup.keySet()) {
                    int count = dup.get(key);
                    int num = 1;
                    while (count > 1) {
                        num *= count--;
                    }
                    cur /= num;
                }
                result += cur;
            }
            set.add(A[i]);
        }
        return result;
    }
}
