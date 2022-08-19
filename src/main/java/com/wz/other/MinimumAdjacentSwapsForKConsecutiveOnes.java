package com.wz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array, nums, and an integer k. nums comprises of only 0's and 1's.
 * In one move, you can choose two adjacent indices and swap their values.
 * Return the minimum number of moves required so that nums has k consecutive 1's.
 *
 * Example 1:
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: 1
 * Explanation: In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive 1's.
 *
 * Example 2:
 * Input: nums = [1,0,0,0,0,0,1,1], k = 3
 * Output: 5
 * Explanation: In 5 moves, the leftmost 1 can be shifted right until nums = [0,0,0,0,0,1,1,1].
 *
 * Example 3:
 * Input: nums = [1,1,0,1], k = 2
 * Output: 0
 * Explanation: nums already has 2 consecutive 1's.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. nums[i] is 0 or 1.
 * 3. 1 <= k <= sum(nums)
 */
public class MinimumAdjacentSwapsForKConsecutiveOnes {
    public static void main(String[] args) {
        System.out.println(minMoves(new int[]{1, 0, 0, 1, 0, 1}, 2));
        System.out.println(minMoves(new int[]{1, 0, 0, 0, 0, 0, 1, 1}, 3));
        System.out.println(minMoves(new int[]{1, 1, 0, 1}, 2));
    }

    /**
     * 思想就是一个线段上的几个点，到其中位数点的距离最近
     * 记录 nums 中所有 1 的位置，把全部的1都移动到这段 k 个1的index组成的subarray的中位数位置上
     * 然后计算左右侧的点移动到中位数的点上的移动步数，然后往前滑动窗口，得到下一段 k 个1的index组成的subarray，再次计算距离，更新全局最小值
     * 可以使用 preSum 来完成滑动窗口内的距离和的更新，原本每次滑动窗口后，需要重新计算这段的中位数和两侧点过来的距离，但是这样时间复杂度高，
     * 使用1的 indexArray 前缀和，也就是 preSum[i+k] - preSum[i+(k+1)/2] - (preSum[i+k/2] - preSum[i])
     * 最后还需要注意，因为实际上左右两侧的 1 不用移动到中位数1的位置上，所以，其实多计算了距离，具体是是 ((1+k)/2)*(k/2)
     */
    public static int minMoves(int[] nums, int k) {
        List<Integer> oneIndexList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                oneIndexList.add(i);
            }
        }
        int[] preSum = new int[oneIndexList.size() + 1];
        for (int i = 0; i < oneIndexList.size(); i++) {
            preSum[i + 1] = preSum[i] + oneIndexList.get(i);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < oneIndexList.size() - k + 1; i++) {
            result = Math.min(result, preSum[i + k] - preSum[k / 2 + i] - preSum[(k + 1) / 2 + i] + preSum[i]);
        }
        result -= (k / 2) * ((k + 1) / 2);
        return result;
    }
}
