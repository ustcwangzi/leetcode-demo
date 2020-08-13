package com.wz.array;

import java.util.Arrays;

/**
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you could get.
 * Example 1:
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 */
public class MaximumSwap {
    public static void main(String[] args) {
        System.out.println(maximumSwap(2736));
        System.out.println(maximumSwap(9973));
        System.out.println(maximumSwap(1993));
        System.out.println(maximumSwap(98368));
    }

    /**
     * 由于希望置换后的数字最大，那么肯定最好的高位上的小数字和低位上的大数字进行置换，而如果高位上的都是大数字，很有可能就不需要置换
     * 所以需要找到每个数字右边的最大数字(包括其自身)，这样再从高位向低位遍历，如果某一位上的数字小于其右边的最大数字，说明需要调换，
     * 由于最大数字可能不止出现一次，这里希望能跟较低位的数字置换，这样置换后的数字最大，
     * 所以就从低位向高位遍历来找那个最大的数字，找到后进行调换即可。比如对于 1993 这个数：
     * 1 9 9 3
     * 9 9 9 3  (rightMax数组)
     * 9 9 1 3  (1和9进行置换)
     * 建立好rightMax数组后，从头遍历原数字，发现1比9小，于是从末尾往前找9，找到后进行置换，就得到了 9913
     */
    public static int maximumSwap(int num) {
        if (num < 10) {
            return num;
        }

        String str = String.valueOf(num);
        int[] array = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            array[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }

        // 需要包括自身，所以这里直接赋值为原数组
        int[] rightMax = Arrays.copyOf(array, array.length);
        for (int i = array.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i], rightMax[i + 1]);
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == rightMax[i]) {
                continue;
            }

            // 从右到左寻找最佳置换
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] == rightMax[i]) {
                    swap(array, i, j);
                    return convertToString(array);
                }
            }
        }

        return num;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static int convertToString(int[] array) {
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            result = result * 10 + array[i];
        }
        return result;
    }
}
