package com.wz.array;

import java.util.Arrays;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contain a single digit.
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * <p>
 * Example 2:
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] digits = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(plusOne(digits)));

        digits = new int[]{1, 2, 9};
        System.out.println(Arrays.toString(plusOne(digits)));

        digits = new int[]{9, 9, 9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    /**
     * 从右到左遍历，如果当前值小于9，则直接加1返回即可，否则说明加1后需要进位，将本位置设为0，然后继续检查
     * 遍历结束后，如果位置0处大于0说明不需要再进位，直接返回，否则说明需要继续进位，数组总长度加1
     */
    public static int[] plusOne(int[] digits) {
        int size = digits.length;
        while (--size >= 0) {
            if (digits[size] < 9) {
                digits[size] += 1;
                return digits;
            } else {
                digits[size] = 0;
            }
        }
        // 不需要再进位
        if (digits[0] > 0) {
            return digits;
        }

        // 需要继续进位
        int[] result = new int[digits.length + 1];
        System.arraycopy(digits, 0, result, 1, digits.length);
        result[0] = 1;
        return result;
    }
}
