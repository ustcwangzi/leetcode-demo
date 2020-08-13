package com.wz.array;

import java.util.Arrays;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 * Input: [3,2,3]
 * Output: 3
 *
 * Example 2:
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3};
        System.out.println(majorityElement1(nums));
        System.out.println(majorityElement2(nums));

        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement1(nums));
        System.out.println(majorityElement2(nums));
    }

    /**
     * 将数组排序后，索引为n/2的元素就是数组的众数
     */
    public static int majorityElement1(int[] nums) {
        Arrays.parallelSort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票法 Moore Voting
     * 这是一种先假设候选者，然后再进行验证的算法。先将数组中的第一个数假设为过半数，然后进行统计其出现的次数
     * 如果遇到同样的数，则计数器自增1，否则计数器自减1，如果计数器减到了0，则更换下一个数字为候选者。
     * 这是一个很巧妙的设定，也是本算法的精髓所在，为啥遇到不同的要计数器减1呢，为啥减到0了又要更换候选者呢？
     * 首先是有那个强大的前提存在，一定会有一个出现超过半数的数字存在，那么如果计数器减到0了话，
     * 说明目前不是候选者数字的个数已经跟候选者的出现个数相同了，那么这个候选者已经很 weak，不一定能出现超过半数，此时选择更换当前的候选者。
     */
    public static int majorityElement2(int[] nums) {
        int candidate = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count++;
            } else if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}
