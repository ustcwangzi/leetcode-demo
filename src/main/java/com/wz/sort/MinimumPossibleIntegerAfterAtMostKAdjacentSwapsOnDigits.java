package com.wz.sort;

/**
 * You are given a string num representing the digits of a very large integer and an integer k. You are allowed to swap any two adjacent digits of the integer at most k times.
 * Return the minimum integer you can obtain also as a string.
 *
 * Example 1:
 * 4321 -> 3421 -> 3412 -> 3142 -> 1342
 * Input: num = "4321", k = 4
 * Output: "1342"
 * Explanation: The steps to obtain the minimum integer from 4321 with 4 adjacent swaps are shown.
 *
 * Example 2:
 * Input: num = "100", k = 1
 * Output: "010"
 * Explanation: It's ok for the output to have leading zeros, but the input is guaranteed not to have any leading zeros.
 *
 * Example 3:
 * Input: num = "36789", k = 1000
 * Output: "36789"
 * Explanation: We can keep the number without any swaps.
 *
 * Constraints:
 * 1. 1 <= num.length <= 3 * 10^4
 * 2. num consists of only digits and does not contain leading zeros.
 * 3. 1 <= k <= 10^4
 */
public class MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits {
    public static void main(String[] args) {
        System.out.println(minInteger("4321", 4));
        System.out.println(minInteger("100", 1));
    }

    /**
     * 冒泡排序，只能交换 k 次
     * 1. 从 index+1 ～ index+k+1 找到最小的元素 min 和对应的 minIndex
     * 2. 从 index+1 ～ minIndex-1，元素进行右移一位
     * 3. 将 min 放到 index 的位置上
     * 本次操作后保证 index 上为可操作步骤内的最小值，并且操作了 minIndex-index 次
     * 下次从 index+1 开始操作，重复以上过程
     */
    public static String minInteger(String num, int k) {
        char[] array = num.toCharArray();
        helper(array, 0, k);
        return new String(array);
    }

    public static void helper(char[] array, int index, int k) {
        if (k == 0 || index == array.length) {
            return;
        }
        int min = array[index], minIndex = index;
        for (int i = index + 1; i < Math.min(index + k + 1, array.length); i++) {
            if (array[i] < min) {
                min = array[i];
                minIndex = i;
            }
        }

        char temp = array[minIndex];
        // 等价于 for (int i = minIndex; i > index; i--) {
        //            array[i] = array[i - 1];
        //        }
        if (minIndex - index >= 0) {
            System.arraycopy(array, index, array, index + 1, minIndex - index);
        }
        array[index] = temp;
        helper(array, index + 1, k - (minIndex - index));
    }
}
