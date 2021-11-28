package com.wz.array;

/**
 * Given an array nums of 0s and 1s and an integer k, return True if all 1's are at least k places away from each other, otherwise return False.
 *
 * Example 1:
 * @link ../../../../resource/CheckIfAll1AreAtLeastLengthKPlacesAway.jpg
 * Input: nums = [1,0,0,0,1,0,0,1], k = 2
 * Output: true
 * Explanation: Each of the 1s are at least 2 places away from each other.
 *
 * Example 2:
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: false
 * Explanation: The second 1 and third 1 are only one apart from each other.
 *
 * Example 3:
 * Input: nums = [1,1,1,1,1], k = 0
 * Output: true
 *
 * Example 4:
 * Input: nums = [0,1,0,1], k = 1
 * Output: true
 */
public class CheckIfAll1AreAtLeastLengthKPlacesAway {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 0, 0, 1, 0, 0, 1};
        System.out.println(kLengthApart(nums, 2));

        nums = new int[]{1, 0, 0, 1, 0, 1};
        System.out.println(kLengthApart(nums, 2));

        nums = new int[]{0, 1, 0, 1};
        System.out.println(kLengthApart(nums, 1));
    }

    /**
     * 统计每两个 1 之间 0 的个数，个数大于 k 则返回 false
     */
    public static boolean kLengthApart(int[] nums, int k) {
        // 标记前面是否有1
        boolean start = nums[0] == 1;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (!start) {
                    continue;
                }
                count++;
            } else {
                if (start && count < k) {
                    return false;
                }
                start = true;
                count = 0;
            }
        }
        return true;
    }
}
