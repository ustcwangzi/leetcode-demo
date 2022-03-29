package com.wz.greedy;

/**
 * You are given a 0-indexed integer array nums. The array nums is beautiful if:
 * - nums.length is even.
 * - nums[i] != nums[i + 1] for all i % 2 == 0.
 * Note that an empty array is considered beautiful.
 * You can delete any number of elements from nums.
 * When you delete an element, all the elements to the right of the deleted element will be shifted one unit to the left
 * to fill the gap created and all the elements to the left of the deleted element will remain unchanged.
 * Return the minimum number of elements to delete from nums to make it beautiful.
 *
 * Example 1:
 * Input: nums = [1,1,2,3,5]
 * Output: 1
 * Explanation: You can delete either nums[0] or nums[1] to make nums = [1,2,3,5] which is beautiful. It can be proven you need at least 1 deletion to make nums beautiful.
 *
 * Example 2:
 * Input: nums = [1,1,2,2,3,3]
 * Output: 2
 * Explanation: You can delete nums[0] and nums[5] to make nums = [1,2,2,3] which is beautiful. It can be proven you need at least 2 deletions to make nums beautiful.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^5
 */
public class MinimumDeletionsToMakeArrayBeautiful {
    public static void main(String[] args) {
        System.out.println(minDeletion(new int[]{1, 1, 2, 2, 3, 3}));
        System.out.println(minDeletion(new int[]{1, 1, 2, 3, 5}));
    }

    /**
     * 使用 pre 记录每个偶数位置的元素，每次统计一组满足条件的元素（即两个），遍历数组
     * 若 pre == -1，说明该组元素还未开始，将当前元素赋值给 pre，继续遍历下一个
     * 若 pre != -1 && num != pre，说明该组元素满足条件，count+=2，同时该组元素已结束，重新将 pre 设置为 -1
     * 最后，所有满足条件的元素个数为 count，最终需要删除的元素个数就是 n - count
     */
    public static int minDeletion(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int pre = -1, count = 0;
        for (int num : nums) {
            if (pre == -1) {
                pre = num;
                continue;
            }
            if (num != pre) {
                count += 2;
                pre = -1;
            }
        }

        return nums.length - count;
    }
}
