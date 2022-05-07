package com.wz.greedy;

/**
 * Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number
 * in the range [1, n] inclusive can be formed by the sum of some elements in the array.
 * Return the minimum number of patches required.
 *
 * Example 1:
 * Input: nums = [1,3], n = 6
 * Output: 1
 * Explanation:
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 *
 * Example 2:
 * Input: nums = [1,5,10], n = 20
 * Output: 2
 * Explanation: The two patches can be [2, 4].
 *
 * Example 3:
 * Input: nums = [1,2,2], n = 5
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 10^4
 * 3. nums is sorted in ascending order.
 * 4. 1 <= n <= 2^31 - 1
 */
public class PatchingArray {
    public static void main(String[] args) {
        System.out.println(minPatches(new int[]{1, 3}, 6));
        System.out.println(minPatches(new int[]{1, 5, 10}, 20));
    }

    /**
     * 贪心，使用 sum 记录当前累加和，先遍历数组，若当前 num 大于 sum+1，说明出现空缺，需要填充 sum+1，否则直接使用 num
     * 遍历过程中，若 sum 已大于等于 n，直接结束
     * 遍历结束后，可能还是达不到 n，因此需要继续循环，每循环一次填充一个 sum+1，直到 sum >= n
     */
    public static int minPatches(int[] nums, int n) {
        int result = 0;
        long sum = 0;
        for (int num : nums) {
            if (sum >= n) {
                return result;
            }
            while (sum + 1 < num && sum < n) {
                result++;
                sum += sum + 1;
            }
            sum += num;
        }
        while (sum < n) {
            result++;
            sum += sum + 1;
        }
        return result;
    }
}
