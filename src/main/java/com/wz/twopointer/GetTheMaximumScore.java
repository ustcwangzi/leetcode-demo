package com.wz.twopointer;

/**
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 * A valid path is defined as follows:
 * - Choose array nums1 or nums2 to traverse (from index-0).
 * - Traverse the current array from left to right.
 * - If you are reading any value that is present in nums1 and nums2 you are allowed to change your path to the other array.
 *   (Only one repeated value is considered in the valid path).
 * The score is defined as the sum of uniques values in a valid path.
 * Return the maximum score you can obtain of all possible valid paths. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * @link ../../../../resource/GetTheMaximumScore.jpg
 * Input: nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 * Output: 30
 * Explanation: Valid paths:
 * [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],  (starting from nums1)
 * [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]    (starting from nums2)
 * The maximum is obtained with the path in green [2,4,6,8,10].
 *
 * Example 2:
 * Input: nums1 = [1,3,5,7,9], nums2 = [3,5,100]
 * Output: 109
 * Explanation: Maximum sum is obtained with the path [1,3,5,100].
 *
 * Example 3:
 * Input: nums1 = [1,2,3,4,5], nums2 = [6,7,8,9,10]
 * Output: 40
 * Explanation: There are no common elements between nums1 and nums2.
 * Maximum sum is obtained with the path [6,7,8,9,10].
 *
 * Constraints:
 * 1. 1 <= nums1.length, nums2.length <= 10^5
 * 2. 1 <= nums1[i], nums2[i] <= 10^7
 * 3. nums1 and nums2 are strictly increasing.
 */
public class GetTheMaximumScore {
    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{2, 4, 5, 8, 10}, new int[]{4, 6, 8, 9}));
        System.out.println(maxSum(new int[]{1, 3, 5, 7, 9}, new int[]{3, 5, 100}));
    }

    /**
     * 用两个变量分别记录两条线的 curSum，遇到相等元素时，取最大值，然后将 curSum 重置，继续遍历，具体步骤：
     * 使用 i、j 分别指向 nums1[]、nums2[]，sum1、sum2 分别记录两个数组的累加和，i、j 都没有到结束位置时，遍历两个数组
     * 若 nums1[i] < nums2[j]，则累加 sum1，同时 i 右移
     * 若 nums1[i] > nums2[j]，则累加 sum2，同时 j 右移
     * 若 nums1[i] == nums2[j]，则选择 sum1、sum2 中较大的那个累加到 result 中，同时 sum1、sum2 清空，i 和 j 右移
     * 若 i 或 j 还没有到达结束位置，则将剩余位置遍历完累加到 sum1 或 sum2
     * 最后，选择 sum1、sum2 中较大的那个累加到 result 中
     */
    public static int maxSum(int[] nums1, int[] nums2) {
        int mod = 1000000007;
        int i = 0, j = 0;
        long result = 0, sum1 = 0, sum2 = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                sum1 += nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                sum2 += nums2[j++];
            } else {
                result += Math.max(sum1, sum2) + nums1[i++];
                j++;
                sum1 = 0;
                sum2 = 0;
            }
        }
        while (i < nums1.length) {
            sum1 += nums1[i++];
        }
        while (j < nums2.length) {
            sum2 += nums2[j++];
        }
        return (int) ((Math.max(sum1, sum2) + result) % mod);
    }
}
