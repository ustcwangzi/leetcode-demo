package com.wz.practice;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述
 * 给定一个数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
 * 子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
 *
 * 示例1
 * 输入：[2,3,4,5]
 * 返回值：4
 *
 * 示例2
 * 输入：[2,2,3,4,3]
 * 返回值：3
 * 说明：[2,3,4]是最长子数组
 *
 * 示例3
 * 输入：[2,2,3,4,8,99,3]
 * 返回值：5
 * 说明：最长子数组为[2,3,4,8,99]
 */
public class MaxLengthOfNoRepeatedSubarray {
    public static void main(String[] args) {
        System.out.println(maxLength(new int[]{2, 3, 4, 5}));
        System.out.println(maxLength(new int[]{2, 2, 3, 4, 8, 99, 3}));
    }

    /**
     * 双指针，使用 set 判断 arr[left,right] 元素是否重复，若重复则 left 右移，同时将 arr[left] 从 set 中移除
     * 否则，更新最大长度，同时将 arr[right] 加入 set 中、然后 right 右移
     */
    public static int maxLength(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int left = 0, right = 0, result = 0;
        while (right < arr.length) {
            if (set.contains(arr[right])) {
                set.remove(arr[left++]);
                continue;
            }
            result = Math.max(result, right - left + 1);
            set.add(arr[right++]);
        }
        return result;
    }
}
