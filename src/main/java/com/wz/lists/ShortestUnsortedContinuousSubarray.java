package com.wz.lists;

import java.util.Arrays;

/**
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order, too.
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 */
public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray1(nums));
        System.out.println(findUnsortedSubarray2(nums));
    }

    /**
     * 对原数组进行复制，然后复制数组进行排序
     * 从数组起始位置开始，两个数组相互比较，当对应位置数字不同的时候停止
     * 再从末尾开始，对应位置上比较，也是遇到不同的数字时停止，这样中间一段就是最短无序连续子数组了
     */
    public static int findUnsortedSubarray1(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.parallelSort(copy);

        int i = 0, j = nums.length - 1;
        while (i < nums.length && nums[i] == copy[i]) {
            i++;
        }
        while (j > i && nums[j] == copy[j]) {
            j--;
        }

        return j - i + 1;
    }

    /**
     * 如果当前元素比它前面的元素中的最大的值小，那它就在待排序的子数组里
     * 如果当前元素比它后面元素中的最小值要大，那它也需要包含在待排序的子数组里。
     * 用 max 来保存遍历过的元素中的最大值，用 min 来保存从数组尾部遍历的元素中的最小值
     * 然后只要通过遍历找到，最后一位待排序元素和最前面的待排序元素就可以了
     */
    public static int findUnsortedSubarray2(int[] nums) {
        int length = nums.length;
        // 为了使 end-begin+1 == 0
        int start = -1, end = -2;
        int min = nums[length - 1], max = nums[0];

        for (int i = 1; i < length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[length - i - 1]);

            if (nums[i] < max) {
                end = i;
            }
            if (nums[length - 1 - i] > min) {
                start = length - 1 - i;
            }
        }

        return end - start + 1;
    }
}
