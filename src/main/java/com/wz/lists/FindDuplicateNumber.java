package com.wz.lists;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Example 1:
 * Input: [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: [3,1,3,4,2]
 * Output: 3
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 */
public class FindDuplicateNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 2, 2};
        System.out.println(findDuplicate(nums));

        nums = new int[]{3, 1, 3, 4, 2};
        System.out.println(findDuplicate(nums));
    }

    /**
     * 快慢指针
     * 将数组想象成链表，则存在重复元素时代表链表存在环，因此转化成链表求环的问题
     * @see ../../../../resource/FindDuplicateNumber.jpg
     * slow和fast都从起点开始出发，slow的速度为1，fast的速度为2
     * 在红点处相遇，相遇时slow走了a+b，fast走了a+b+c+b，存在a+b+c+b=2*(a+b) => a==c
     * 此时，将fast重新放到起点，速度也为1，则相遇时刚好位于环的入口处
     */
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        // 求出相遇点
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        // 求出环的入口
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
