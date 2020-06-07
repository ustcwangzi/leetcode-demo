package com.wz.lists;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 * Input: [3,2,3]
 * Output: [3]
 *
 * Example 2:
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElementII {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3};
        System.out.println(majorityElement(nums));

        nums = new int[]{1, 1, 1, 3, 3, 2, 2, 2};
        System.out.println(majorityElement(nums));
    }

    /**
     * 摩尔投票法 Moore Voting，思路与{@link MajorityElement#majorityElement2(int[])}类似
     * 任意一个数组出现次数大于n/3的数最多有两个，使用投票法的核心是找出两个候选数进行投票，需要两遍遍历
     * 第一遍历找出两个候选数，第二遍遍历重新投票验证这两个候选数是否为符合题意的数即可
     * 由于{@link MajorityElement}中限定了一定会有众数存在，因此省略了验证候选众数的步骤，但本题没有这种限定，所以要验证
     */
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new LinkedList<>();
        int candidate1 = 0, count1 = 0, candidate2 = 0, count2 = 0;
        // 找出两个候选数
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        // 验证候选数
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            }
        }

        int limit = nums.length / 3;
        if (count1 > limit) {
            result.add(candidate1);
        }
        if (count2 > limit) {
            result.add(candidate2);
        }

        return result;
    }
}
