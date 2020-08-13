package com.wz.array;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 2, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 1, 5};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{2, 2, 0, 4, 3, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 找规律，从后向前找到一个降序下标firstDesc
     * 然后重新从后向前找到第一个比下标firstDesc的值大的下标i
     * 之后交换firstDesc和i，最后将firstDesc+1到结尾进行反转
     * 注意：不存在降序时，将整个数组反转即可
     * 举例说明
     * [1,2,7,4,3,1] ====> [1,3,1,2,4,7]
     * 从后向前遍历，找到第一个降序的数字2、下标1
     * 然后重新从后向前遍历，找比这个数大的第一个数字3，下标4
     * 之后交换这两个数字，变成 [1,3,7,4,2,1]
     * 然后把下标1之后的数字反转，变成 [1,3,1,2,4,7]
     */
    public static void nextPermutation(int[] nums) {
        int firstDesc = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                firstDesc = i - 1;
                break;
            }
        }
        if (firstDesc == -1) {
            reverse(nums, 0, nums.length - 1);
        } else {
            for (int i = nums.length - 1; i > firstDesc; i--) {
                if (nums[i] > nums[firstDesc]) {
                    swap(nums, i, firstDesc);
                    reverse(nums, firstDesc + 1, nums.length - 1);
                    break;
                }
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
