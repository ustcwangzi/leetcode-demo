package com.wz.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)
 * Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.
 *
 * Example :
 * Input:
 * [1,2,3,4,5,6,7,8]
 * Output: true
 * Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
 *
 * Note:
 * The length of A will be in the range [1, 30].
 * A[i] will be in the range of [0, 10000].
 */
public class SplitArrayWithSameAverage {
    public static void main(String[] args) {
        System.out.println(splitArraySameAverage(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    /**
     * 由于平均值是由数字总和除以个数得来的，那么假设整个数组有n个数组，数字总和为 sum，分成的其中一个数组有k个，假设其数字和为 sum1，
     * 那么另一个数组就有 n-k 个，假设其数组和为 sum2，就有如下等式：
     * sum / n = sum1 / k = sum2 / (n - k)
     * 看前两个等式，sum / n = sum1 / k，可以变个形，sum * k / n = sum1，那么由于数字都是整数，所以 sum * k 一定可以整除 n，
     * 这个可能当作一个快速的判断条件。下面来考虑k的取值范围，由于要分成两个数组，可以始终将k当作其中较短的那个数组，则k的取值范围就是 [1, n/2]，
     * 就是说，如果在这个范围内的k，没有满足的 sum * k % n == 0 的话，那么可以直接返回false，这是一个快速的剪枝过程。
     * 如果有的话，也不能立即说可以分成满足题意的两个小数组，最简单的例子，比如数组 [1, 3]，当k=1时，sum * k % n == 0 成立，
     * 但明显不能分成两个平均值相等的数组。所以还需要进一步检测，当找到满足的 sum * k % n == 0 的k了时候，其实可以直接算出 sum1，
     * 通过 sum * k / n，那么就知道较短的数组的数字之和，只要能在原数组中数组找到任意k个数字，使其和为 sum1，就可以 split 成功了
     * 。问题到这里就转化为了如何在数组中找到任意k个数字，使其和为一个给定值。
     * k的范围是固定的，可以事先任意选数组中k个数字，将其所有可能出现的数字和保存下来，最后再查找。那么为了去重复跟快速查找，
     * 可以使用 HashSet 来保存数字和，可以建立 n/2 + 1 个 HashSet，多加1是为了不做数组下标的转换，并且防止越界，因为在累加的过程中，
     * 计算k的时候，需要用到 k-1 的情况。这里的 dp 数组就是一个包含 HashSet 的数组，其中 dp[i] 表示数组中任选 i 个数字，所有可能的数字和。
     * 首先在 dp[0] 中加入一个0，这个是为了防止越界。更新 dp[i] 的思路是，对于 dp[i-1] 中的每个数字，都加上一个新的数字，
     * 所以最外层的 for 循环是遍历原数组的中的每个数字的，中间的 for 循环是遍历k的，从 n/2 遍历到1，
     * 然后最内层的 for 循环是遍历 dp[i-1] 中的所有数组，加上最外层遍历到的数字，并存入 dp[i] 即可。
     * 整个 dp 数组更新好了之后，下面就是验证的环节了，对于每个k值，验证若 sum * k / n == 0 成立，
     * 并且 sum * i / n 在 dp[i] 中存在，则返回 true。最后都没有成立的话，返回 false
     */
    public static boolean splitArraySameAverage(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int len = A.length, sum = Arrays.stream(A).sum();

        // find all qualified target sum, and store them into a map with target vs. subarr size pair
        int target = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n = 1; n <= len / 2; ++n) {
            if ((sum * n) % len == 0) {
                // update to the max target
                target = sum * n / len;
                // put target/arrSize pair in to map
                map.put(target, n);
            }
        }
        // return false, if there is no qualified target sum
        if (target == -1) {
            return false;
        }

        // create and init a dp array: count of nums to sum to the current target
        int[][] dp = new int[len + 1][target + 1];
        //(1) iterate over all nums in array A
        for (int i = 1; i <= len; ++i) {
            int a = A[i - 1];
            // (2) iterate from 0 to the max target: check if current total i nums can sum up to the target?
            for (int j = 0; j <= target; ++j) {
                // for each new number i, init dp[i][j] from dp[i-1][j]: not use cur num i, and still use previous (i-1) nums to sum up to j
                dp[i][j] = dp[i - 1][j];
                if (a == j || (j > a && dp[i - 1][j - a] > 0)) {
                    // use current num i, only if we can sum up to a target (j-a) from previous (i-1) num, where dp[i-1][j-a] > 0
                    // then increase the count [of using previous (i-1) num to sum up to (j-a)] by 1 (plus current num i), and update it to dp[i][j]
                    dp[i][j] = dp[i - 1][j - a] + 1;
                    // if j is a valid target (a map key), and nums count in dp[i][j] == the subarray size from map val -> find a qualified subarr
                    if (map.containsKey(j) && dp[i][j] == map.get(j))
                        return true;
                }
            }
        }
        return false;
    }
}
