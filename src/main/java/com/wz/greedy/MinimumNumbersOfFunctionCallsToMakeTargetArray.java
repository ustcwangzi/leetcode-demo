package com.wz.greedy;

/**
 * @see ../../../../resource/MinimumNumbersOfFunctionCallsToMakeTargetArray.jpg
 * Your task is to form an integer array nums from an initial array of zeros arr that is the same size as nums.
 * Return the minimum number of function calls to make nums from arr.
 * The answer is guaranteed to fit in a 32-bit signed integer.
 *
 * Example 1:
 * Input: nums = [1,5]
 * Output: 5
 * Explanation: Increment by 1 (second element): [0, 0] to get [0, 1] (1 operation).
 * Double all the elements: [0, 1] -> [0, 2] -> [0, 4] (2 operations).
 * Increment by 1 (both elements)  [0, 4] -> [1, 4] -> [1, 5] (2 operations).
 * Total of operations: 1 + 2 + 2 = 5.
 *
 * Example 2:
 * Input: nums = [2,2]
 * Output: 3
 * Explanation: Increment by 1 (both elements) [0, 0] -> [0, 1] -> [1, 1] (2 operations).
 * Double all the elements: [1, 1] -> [2, 2] (1 operation).
 * Total of operations: 2 + 1 = 3.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^9
 */
public class MinimumNumbersOfFunctionCallsToMakeTargetArray {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{1, 5}));
        System.out.println(minOperations(new int[]{2, 2}));
    }

    /**
     * 对于每个数字 x ：如果 x 为偶数，则除以 2，次数加一，如果中途出现了奇数，则减 1，次数加一
     * 整个数组的每个数字可以都按照这个方法来计算，找到需要最多乘 2 操作的数字，假设次数为 maxEven，
     * 整个数组就只需要进行 maxEven 次整体乘 2 的操作，然后再加上每个数字需要的加 1 操作就可以了
     */
    public static int minOperations(int[] nums) {
        int totalOdd = 0, maxEven = 0;
        for (int num : nums) {
            int odd = 0, even = 0;
            while (num > 0) {
                if (num % 2 == 1) {
                    odd++;
                    num--;
                } else {
                    num /= 2;
                    even++;
                }
            }

            maxEven = Math.max(maxEven, even);
            totalOdd += odd;
        }

        return maxEven + totalOdd;
    }
}
