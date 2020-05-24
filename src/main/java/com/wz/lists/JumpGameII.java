package com.wz.lists;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * Note:
 * You can assume that you can always reach the last index.
 * <p>
 * Example:
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGameII {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(jump(nums));

        nums = new int[]{1};
        System.out.println(jump(nums));

        nums = new int[]{2, 1};
        System.out.println(jump(nums));

        nums = new int[]{1, 3, 2};
        System.out.println(jump(nums));
    }

    /**
     * 当前的步数step
     * 使用当前的步数可以去的最远位置curMax
     * 使用当前的步数，如果再走一步，可以到达的最远位置nextMax
     * 遍历数组，发现i不可达时，再走一步，同时更新当前可以到达的最远位置
     */
    public static int jump(int[] nums) {
        int step = 0, curMax = 0, nextMax = 0;
        for (int i = 0; i < nums.length; i++) {
            // 不可达，再走一步
            if (i > curMax) {
                step++;
                curMax = nextMax;
            }
            if (curMax >= nums.length) {
                return step;
            }
            nextMax = Math.max(nextMax, nums[i] + i);
        }
        return step;
    }
}
