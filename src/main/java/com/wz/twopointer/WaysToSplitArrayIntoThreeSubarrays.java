package com.wz.twopointer;

/**
 * A split of an integer array is good if:
 * 1. The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
 * 2. The sum of the elements in left is less than or equal to the sum of the elements in mid,
 *    and the sum of the elements in mid is less than or equal to the sum of the elements in right.
 * Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: nums = [1,1,1]
 * Output: 1
 * Explanation: The only good way to split nums is [1] [1] [1].
 *
 * Example 2:
 * Input: nums = [1,2,2,2,5,0]
 * Output: 3
 * Explanation: There are three good ways of splitting nums:
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 *
 * Constraints:
 * 1. 3 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^4
 */
public class WaysToSplitArrayIntoThreeSubarrays {
    public static void main(String[] args) {
        System.out.println(waysToSplit(new int[]{1, 1, 1}));
        System.out.println(waysToSplit(new int[]{0, 0, 0}));
    }

    /**
     * 前缀和 + 双指针
     * 如果能找到中间区间 [j...k] 那么结果就确定了，因此可以固定第一个区间[0...i]，寻找中间区间的上下边界
     * 三个区间需要满足：
     * sum[0...i] <= sum[i+1...j]
     * sum[i+1...k] <= sum[k+1...n-1]
     * 使用前缀和进行简化之后：
     * pre[i] <= pre[j] - pre[i]
     * pre[k] - pre[i] <= pre[n-1] - pre[k]
     * 找到满足条件的 j、k，然后更新结果即可
     */
    public static int waysToSplit(int[] nums) {
        int n = nums.length, mod = 1000000007, result = 0;
        int[] pre = new int[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }

        for (int i = 0, j = 0, k = 0; i < n - 2; i++) {
            // 找下边界，使用 <
            while (j <= i || (j < n - 1 && pre[j] < pre[i] * 2)) {
                j++;
            }
            // 找上边界，使用 <=，会比实际需要的 k 大 1，因此在下面累加结果时使用 k - j
            while (k < j || (k < n - 1 && pre[k] - pre[i] <= pre[n - 1] - pre[k])) {
                k++;
            }
            result = (result + k - j) % mod;
        }
        return result;
    }
}
