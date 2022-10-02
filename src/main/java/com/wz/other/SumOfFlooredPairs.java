package com.wz.other;

import java.util.Arrays;

/**
 * Given an integer array nums, return the sum of floor(nums[i] / nums[j]) for all pairs of indices 0 <= i, j < nums.length in the array.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * The floor() function returns the integer part of the division.
 *
 * Example 1:
 * Input: nums = [2,5,9]
 * Output: 10
 * Explanation:
 * floor(2 / 5) = floor(2 / 9) = floor(5 / 9) = 0
 * floor(2 / 2) = floor(5 / 5) = floor(9 / 9) = 1
 * floor(5 / 2) = 2
 * floor(9 / 2) = 4
 * floor(9 / 5) = 1
 * We calculate the floor of the division for every pair of indices in the array then sum them up.
 *
 * Example 2:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 49
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^5
 */
public class SumOfFlooredPairs {
    public static void main(String[] args) {
        System.out.println(sumOfFlooredPairs(new int[]{2, 5, 9}));
        System.out.println(sumOfFlooredPairs(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }

    /**
     * 1.首先对于floor函数，例如floor(x/y)中，假设y一定，则有多个x使得floor(x/y)相同；
     * 例如y=9，则
     * x=[9,17]时floor函数都为1；
     * x=[18,26]时floor函数都为2；
     * x=[27,35]时floor函数都是3；
     * 显然需要进行区间计数，容易联想到前缀和
     * 2.前缀和数组，preSum[i] 表示 [0,i] 的元素个数总和，preSum[i] - preSum[i-1] 则表示元素 i 的个数
     * 3.对于元素i，每次找区间 [i,i*2-1] [i*2,i*3-1] [i*3,i*4-1] ....[i*(j-1),i*j-1] 之间的元素个数，倍数关系在循环中用 j 表示
     * 倍数 * 区间内的元素总个数 = 元素 i 在该段区间的函数值总和
     * 元素 i 的个数 * 倍数 * 区间内的元素总个数 = 所有 i 在该段区间的函数值总和
     * 再对多段区间进行累加即可
     * 4.注意越界情况，运算期间用 long，最后转int，另外，当 i*j-1 > max 时直接使用 max 作为数组下标防止数组越界
     */
    public static int sumOfFlooredPairs(int[] nums) {
        long result = 0, mod = 1000000007;
        int max = Arrays.stream(nums).max().orElse(0);
        int[] freq = new int[max + 1];
        for (int num : nums) {
            freq[num]++;
        }
        int[] preSum = new int[max + 1];
        preSum[0] = nums[0];
        for (int i = 1; i <= max; i++) {
            preSum[i] = freq[i] + preSum[i - 1];
        }

        for (int i = 1; i <= max; i++) {
            // x 表示数字 i 的个数
            long x = preSum[i] - preSum[i - 1];
            if (x == 0) {
                continue;
            }

            // [i,i*2-1]、[i*2,i*3-1]、[i*3,i*4-1]，区间内的floor函数值都一样
            for (int j = i; j <= max; j += i) {
                // y 表示区间的个数,如果 j+i-1 > max 则取 max 即可，防止数组溢出
                long y = preSum[Math.min(j + i - 1, max)] - preSum[j - 1];
                // 倍数 * 区间数 * 个数
                result = (result + j / i * y * x) % mod;
            }
        }
        return (int) result;
    }
}
