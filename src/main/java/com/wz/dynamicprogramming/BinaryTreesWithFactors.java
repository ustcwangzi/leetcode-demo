package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 * We make a binary tree using these integers, and each number may be used for any number of times.
 * Each non-leaf node's value should be equal to the product of the values of its children.
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 *
 * Example 1:
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 *
 * Example 2:
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 *
 * Constraints:
 * 1. 1 <= arr.length <= 1000
 * 2. 2 <= arr[i] <= 10^9
 * 3. All the values of arr are unique.
 */
public class BinaryTreesWithFactors {
    public static void main(String[] args) {
        System.out.println(numFactoredBinaryTrees(new int[]{2, 4}));
        System.out.println(numFactoredBinaryTrees(new int[]{2, 4, 5, 10}));
    }

    /**
     * 动态规划，dp[i] 表示以 arr[i] 为根节点时能够形成的符合条件的二叉树个数
     * 要求根节点的值必须是左右子节点的乘积，那么根节点的 dp 值一定是需要加上左右子节点 dp 值的乘积，因为可以进行组合
     * 由于单节点是满足条件的，因此 dp 值全部初始化为 1，然后寻找满足条件的左右子节点
     * 如果 arr[i] 能够整除 arr[j]，那么 arr[j] 就可以作为一个子节点，而余数就是另一个子节点
     * 为快速找到另一个子节点是否存在，可以提前将 arr 中值和下标保存在 map 中
     */
    public static int numFactoredBinaryTrees(int[] arr) {
        int mod = 1000000007, n = arr.length;
        Arrays.parallelSort(arr);
        long[] dp = new long[n];
        Arrays.fill(dp, 1);

        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] != 0) {
                    continue;
                }
                // arr[i] 为根，arr[j] 是其中一个子节点，child 是另一个子节点
                int child = arr[i] / arr[j];
                if (map.containsKey(child)) {
                    dp[i] = (dp[i] + dp[j] * dp[map.get(child)]) % mod;
                }
            }
        }

        long result = 0;
        for (long value : dp) {
            result += value;
        }
        return (int) (result % mod);
    }
}
