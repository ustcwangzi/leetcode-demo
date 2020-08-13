package com.wz.array;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * Given nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * Example 2:
 * Given nums = [0,0,1,1,1,1,2,3,3],
 * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It doesn't matter what values are set beyond the returned length.
 */
public class RemoveDuplicatesII {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int size = removeDuplicates(nums);
        for (int i = 0; i < size; i++) {
            System.out.print(nums[i] + " ");
        }

        System.out.println();

        nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        size = removeDuplicates(nums);
        for (int i = 0; i < size; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * 思路与{@link RemoveDuplicates}类似，需要增加twice来标志是否已有两个相同元素
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int size = 1;
        boolean twice = false;
        for (int i = 1; i < nums.length; i++) {
            // 未满两个或者元素不同时，满足条件
            if (!twice || nums[i] != nums[i - 1]) {
                nums[size++] = nums[i];
            }
            if (size - 2 >= 0) {
                twice = nums[size - 1] == nums[size - 2];
            }
        }
        return size;
    }
}
