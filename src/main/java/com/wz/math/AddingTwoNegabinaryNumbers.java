package com.wz.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two numbers arr1 and arr2 in base -2, return the result of adding them together.
 * Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.
 * For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.
 * A number arr in array format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.
 * Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.
 *
 * Example 1:
 * Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
 * Output: [1,0,0,0,0]
 * Explanation: arr1 represents 11, arr2 represents 5, the output represents 16.
 *
 * Note:
 * 1 <= arr1.length <= 1000
 * 1 <= arr2.length <= 1000
 * arr1 and arr2 have no leading zeros
 * arr1[i] is 0 or 1
 * arr2[i] is 0 or 1
 */
public class AddingTwoNegabinaryNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(addNegabinary(new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 1})));
    }

    /**
     * 进行位运算的时候最大的难点在于进位怎么获得
     * 当sum = 0 / 1的时候，carry = 0
     * 当sum > 1的时候，carry = -1
     * 当sum < 0的时候，carry = 1
     * sum = 0 -> carry = 0, result = 0
     * sum = 1 -> carry = 0, result = 1
     * sum = 2 -> carry = -1, result = 0
     * sum = 3 -> carry = -1, result = 1
     * sum = -1 -> carry = 1, result = 1
     */
    public static int[] addNegabinary(int[] arr1, int[] arr2) {
        List<Integer> resultList = new ArrayList<>();
        int len1 = arr1.length, len2 = arr2.length;
        int carry = 0;
        for (int i = 0; i < Math.max(len1, len2) || carry != 0; i++) {
            int d1 = i < len1 ? arr1[len1 - 1 - i] : 0;
            int d2 = i < len2 ? arr2[len2 - 1 - i] : 0;
            int sum = d1 + d2 + carry;
            resultList.add(Math.abs(sum) % 2);
            if (sum > 1) {
                carry = -1;
            } else if (sum < 0) {
                carry = 1;
            } else {
                carry = 0;
            }
        }

        int idx = resultList.size() - 1;
        while (idx >= 1) {
            if (resultList.get(idx) == 0) {
                idx -= 1;
            } else {
                break;
            }
        }

        int[] result = new int[idx + 1];
        while (idx >= 0) {
            result[result.length - 1 - idx] = resultList.get(idx--);
        }

        return result;
    }
}
