package com.wz.array;

/**
 * Given a binary array nums, you should delete one element from it.
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 * Return 0 if there is no such subarray.
 *
 * Example 1:
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 *
 * Example 2:
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 *
 * Example 3:
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 */
public class LongestSubarrayOf1AfterDeletingOneElement {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 1};
        System.out.println(longestSubarray(nums));

        nums = new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1};
        System.out.println(longestSubarray(nums));

        nums = new int[]{1, 1, 1};
        System.out.println(longestSubarray(nums));
    }

    /**
     * 滑动窗口
     * 去除一个元素后连续 1 的最大长度
     * 可以转化为最多包含一个 0 的情况，连续 1 的最大长度
     * 使用 left 和 right 维持一个滑动窗口，保证滑动窗口内最多只有一个 0，不超过时 right 后移；否则 left 右移
     */
    public static int longestSubarray(int[] nums) {
        int result = 0;
        int left = 0, right = 0, zeroCount = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            // 0 的数量不满足条件，left 右移
            while (zeroCount > 1 && left < right) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            // 更新最大长度
            result = Math.max(result, right - left + 1);
            right++;
        }
        // 需要删除一个元素
        return result - 1;
    }
}
