package com.wz.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given a positive integer num. You may swap any two digits of num that have the same parity (i.e. both odd digits or both even digits).
 * Return the largest possible value of num after any number of swaps.
 *
 * Example 1:
 * Input: num = 1234
 * Output: 3412
 * Explanation: Swap the digit 3 with the digit 1, this results in the number 3214.
 * Swap the digit 2 with the digit 4, this results in the number 3412.
 * Note that there may be other sequences of swaps but it can be shown that 3412 is the largest possible number.
 * Also note that we may not swap the digit 4 with the digit 1 since they are of different parities.
 *
 * Example 2:
 * Input: num = 65875
 * Output: 87655
 * Explanation: Swap the digit 8 with the digit 6, this results in the number 85675.
 * Swap the first digit 5 with the digit 7, this results in the number 87655.
 * Note that there may be other sequences of swaps but it can be shown that 87655 is the largest possible number.
 *
 * Constraints:
 * 1 <= num <= 10^9
 */
public class LargestNumberAfterDigitSwapsByParity {
    public static void main(String[] args) {
        System.out.println(largestInteger(1234));
        System.out.println(largestInteger(65875));
        System.out.println(largestInteger(247));
    }

    /**
     * 就是将 num 中的奇数、偶数排序后，重新组成新的数字
     * 遍历 num，将 奇数元素、偶数元素 分别存储在 奇数列表、偶数列表 中
     * 然后对两个列表进行逆序排序，最后根据 num 对应位置元素的奇偶选择从 奇数列表 或 偶数列表 获取元素加到结果集
     */
    public static int largestInteger(int num) {
        char[] array = String.valueOf(num).toCharArray();
        List<Character> evenList = new ArrayList<>(array.length / 2), oddList = new ArrayList<>(array.length / 2);
        for (char cur : array) {
            if ((cur & 1) == 0) {
                evenList.add(cur);
            } else {
                oddList.add(cur);
            }
        }
        evenList.sort(Collections.reverseOrder());
        oddList.sort(Collections.reverseOrder());

        StringBuilder builder = new StringBuilder();
        int evenIndex = 0, oddIndex = 0;
        for (char cur : array) {
            if ((cur & 1) == 0) {
                builder.append(evenList.get(evenIndex++));
            } else {
                builder.append(oddList.get(oddIndex++));
            }
        }
        return Integer.parseInt(builder.toString());
    }
}
