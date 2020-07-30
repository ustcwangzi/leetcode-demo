package com.wz.lists;

import java.util.Arrays;

/**
 * Given an integer array arr and a target value target, return the integer value such that when
 * we change all the integers larger than value in the given array to be equal to value,
 * the sum of the array gets as close as possible (in absolute difference) to target.
 * In case of a tie, return the minimum such integer.
 * Notice that the answer is not neccesarilly a number from arr.
 *
 * Example 1:
 * Input: arr = [4,9,3], target = 10
 * Output: 3
 * Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
 *
 * Example 2:
 * Input: arr = [2,3,5], target = 10
 * Output: 5
 *
 * Example 3:
 * Input: arr = [60864,25176,27249,21296,20204], target = 56803
 * Output: 11361
 */
public class SumOfMutatedArrayClosestToTarget {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 9, 3};
        System.out.println(findBestValue(arr, 10));

        arr = new int[]{2, 3, 5};
        System.out.println(findBestValue(arr, 10));

        arr = new int[]{60864, 25176, 27249, 21296, 20204};
        System.out.println(findBestValue(arr, 56803));
    }

    /**
     * 求 value，将数组中所有大于 value 的数换成 value 后，使得数组和最接近 target
     * 对于数组而言，最多是将所有元素全部替换为 value，最少是一个元素都不替换
     * 先对数组进行排序，然后遍历，尝试 value 从 target/n 到 max(arr[i])，对比target的值
     * 1. 如果 target <= arr[i] * (n-i)，返回 round((target-0.0001)/(n-i))
     * 2. 如果 target > arr[i] * (n-i)，那么增大现在的比较对象，同时从 target 中减去 i 之前的元素
     *    以 [3, 4, 9] 为例，3个arr[0] -> 1个arr[0] + 2个arr[1]，因为arr[0] < arr[1]，target -= arr[0]，然后和 arr[1]*2 比较
     *    即 target -= arr[i]，同时 i 后移，然后比较 target 和 arr[i] * (n-i)，重复步骤一和步骤二
     * 注意：由于如果有两个整数到 target 的绝对距离相同时，取较小的值，因此 -0.0001
     */
    public static int findBestValue(int[] arr, int target) {
        int n = arr.length, i = 0;
        Arrays.parallelSort(arr);
        while (i < n && target > arr[i] * (n - i)) {
            // 每向后移动一个位置，表示i及之前的元素都保持不变，只会替换i之后的元素
            target -= arr[i];
            i++;
        }

        return (i == n) ? arr[n - 1] : (int) (Math.round((target - 0.0001) / (n - i)));
    }
}
