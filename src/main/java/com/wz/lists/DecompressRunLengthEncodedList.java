package com.wz.lists;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * We are given a list nums of integers representing a list compressed with run-length encoding.
 * Consider each adjacent pair of elements [freq, val] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair,
 * there are freq elements with value val concatenated in a sublist. Concatenate all the sublists from left to right to generate the decompressed list.
 * Return the decompressed list.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [2,4,4,4]
 * Explanation: The first pair [1,2] means we have freq = 1 and val = 2 so we generate the array [2].
 * The second pair [3,4] means we have freq = 3 and val = 4 so we generate [4,4,4].
 * At the end the concatenation [2] + [4,4,4] is [2,4,4,4].
 *
 * Example 2:
 * Input: nums = [1,1,2,3]
 * Output: [1,3,3]
 *
 * Constraints:
 * 2 <= nums.length <= 100
 * nums.length % 2 == 0
 * 1 <= nums[i] <= 100
 */
public class DecompressRunLengthEncodedList {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(decompressRLElist(nums)));

        nums = new int[]{1, 1, 2, 3};
        System.out.println(Arrays.toString(decompressRLElist(nums)));
    }

    /**
     * 直接遍历即可，偶数位置代表的是奇数位置元素的出现次数
     */
    public static int[] decompressRLElist(int[] nums) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length - 1; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                list.add(nums[i + 1]);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
