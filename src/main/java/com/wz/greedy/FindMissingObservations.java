package com.wz.greedy;

import java.util.Arrays;

/**
 * You have observations of n + m 6-sided dice rolls with each face numbered from 1 to 6.
 * n of the observations went missing, and you only have the observations of m rolls.
 * Fortunately, you have also calculated the average value of the n + m rolls.
 * You are given an integer array rolls of length m where rolls[i] is the value of the ith observation.
 * You are also given the two integers mean and n.
 * Return an array of length n containing the missing observations such that the average value of the n + m rolls is exactly mean.
 * If there are multiple valid answers, return any of them. If no such array exists, return an empty array.
 * The average value of a set of k numbers is the sum of the numbers divided by k.
 * Note that mean is an integer, so the sum of the n + m rolls should be divisible by n + m.
 *
 * Example 1:
 * Input: rolls = [3,2,4,3], mean = 4, n = 2
 * Output: [6,6]
 * Explanation: The mean of all n + m rolls is (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4.
 *
 * Example 2:
 * Input: rolls = [1,5,6], mean = 3, n = 4
 * Output: [2,3,2,2]
 * Explanation: The mean of all n + m rolls is (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3.
 *
 * Example 3:
 * Input: rolls = [1,2,3,4], mean = 6, n = 4
 * Output: []
 * Explanation: It is impossible for the mean to be 6 no matter what the 4 missing rolls are.
 *
 * Constraints:
 *
 * m == rolls.length
 * 1 <= n, m <= 105
 * 1 <= rolls[i], mean <= 6
 */
public class FindMissingObservations {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(missingRolls(new int[]{1, 5, 6}, 3, 4)));
        System.out.println(Arrays.toString(missingRolls(new int[]{1, 2, 3, 4}, 6, 4)));
    }

    /**
     * 先计算目标结果之和 target = (m+n)*mean - sum(rools[0...n-1])
     * 因为每个元素值在 1～6 直接，并且总共有 n 个，因此总和范围在 n～6*n，若 target 不在该范围内，则直接返回空
     * 否则，先用 target/n 填充每个位置，再计算余数 remainder，从 0～remainder 每个位置增加 1，将全部target 分配完
     */
    public static int[] missingRolls(int[] rolls, int mean, int n) {
        int target = (rolls.length + n) * mean - Arrays.stream(rolls).sum();
        if (target > 6 * n || target < n) {
            return new int[0];
        }

        int[] result = new int[n];
        int value = target / n;
        Arrays.fill(result, value);
        int remainder = target - value * n;
        for (int i = 0; i < remainder; i++) {
            result[i]++;
        }
        return result;
    }
}
