package com.wz.array;

import java.util.Arrays;

/**
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it.
 * That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 * Return the answer in an array.
 *
 * Example 1:
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Explanation:
 * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
 * For nums[1]=1 does not exist any smaller number than it.
 * For nums[2]=2 there exist one smaller number than it (1).
 * For nums[3]=2 there exist one smaller number than it (1).
 * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
 *
 * Example 2:
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 *
 * Example 3:
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 *
 * Constraints:
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class HowManyNumbersAreSmallerThanCurrentNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{8, 1, 2, 2, 3};
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(nums)));

        nums = new int[]{6, 5, 4, 8};
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(nums)));

        nums = new int[]{7, 7, 7, 7};
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(nums)));
    }

    /**
     * 由于数组中元素范围是 [0,100]，先统计数组中每个元素的出现次数 count，然后利用 count 计算出小于等于 i 的元素个数
     */
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];
        // 统计元素出现次数
        for (int num : nums) {
            count[num]++;
        }

        // 统计小于等于i的元素个数
        for (int i = 1; i < 101; ++i) {
            count[i] += count[i - 1];
        }

        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; ++i) {
            result[i] = (nums[i] == 0 ? 0 : count[nums[i] - 1]);
        }
        return result;
    }
}
