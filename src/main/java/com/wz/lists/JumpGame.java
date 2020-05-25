package com.wz.lists;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i][j] <= 10^5
 * <p>
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what.
 * Its maximum jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(canJump(nums));

        nums = new int[]{3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
    }

    /**
     * 记录到达每个位置时当前能到的最远位置，该位置为max(max, i+nums[i])
     * 然后检查能不能到达当前位置，以及能不能到达最终位置
     */
    public static boolean canJump(int[] nums) {
        // 当前能到的最远位置
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
            if (max >= nums.length) {
                return true;
            }
        }
        return true;
    }
}
