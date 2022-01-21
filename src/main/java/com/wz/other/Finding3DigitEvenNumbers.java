package com.wz.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an integer array digits, where each element is a digit. The array may contain duplicates.
 * You need to find all the unique integers that follow the given requirements:
 * 1. The integer consists of the concatenation of three elements from digits in any arbitrary order.
 * 2. The integer does not have leading zeros.
 * 3. The integer is even.
 * For example, if the given digits were [1, 2, 3], integers 132 and 312 follow the requirements.
 * Return a sorted array of the unique integers.
 *
 * Example 1:
 * Input: digits = [2,1,3,0]
 * Output: [102,120,130,132,210,230,302,310,312,320]
 * Explanation: All the possible integers that follow the requirements are in the output array.
 * Notice that there are no odd integers or integers with leading zeros.
 *
 * Example 2:
 * Input: digits = [2,2,8,8,2]
 * Output: [222,228,282,288,822,828,882]
 * Explanation: The same digit can be used as many times as it appears in digits.
 * In this example, the digit 8 is used twice each time in 288, 828, and 882.
 *
 * Example 3:
 * Input: digits = [3,7,5]
 * Output: []
 * Explanation: No even integers can be formed using the given digits.
 *
 * Constraints:
 * 1. 3 <= digits.length <= 100
 * 2. 0 <= digits[i] <= 9
 */
public class Finding3DigitEvenNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findEvenNumbers(new int[]{2, 1, 3, 0})));
        System.out.println(Arrays.toString(findEvenNumbers(new int[]{2, 2, 8, 8, 2})));
    }

    /**
     * 先对 digits 中的数字统计出现次数
     * 三位偶数的范围是 100~998，直接从 100 遍历到 998，步长为 2，然后检查每一位是否在 digits 中都够用
     * 注意：检查完之后要将 digits 的计数恢复，以免影响下一次遍历
     */
    public static int[] findEvenNumbers(int[] digits) {
        int[] count = new int[10];
        for (int num : digits) {
            count[num]++;
        }

        List<Integer> result = new ArrayList<>();
        int a, b, c;
        boolean isTarget;
        for (int i = 100; i < 999; i += 2) {
            a = i / 100;
            b = i / 10 % 10;
            c = i % 10;
            isTarget = true;
            if (--count[a] < 0) {
                isTarget = false;
            }
            if (--count[b] < 0) {
                isTarget = false;
            }
            if (--count[c] < 0) {
                isTarget = false;
            }
            if (isTarget) {
                result.add(i);
            }
            count[a]++;
            count[b]++;
            count[c]++;
        }

        int[] array = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            array[i] = result.get(i);
        }
        return array;
    }
}
