package com.wz.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an integer array arr. You have to sort the integers in the array in ascending order by the number of 1's in
 * their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
 * Return the sorted array.
 *
 * Example 1:
 * Input: arr = [0,1,2,3,4,5,6,7,8]
 * Output: [0,1,2,4,8,3,5,6,7]
 * Explantion: [0] is the only integer with 0 bits.
 * [1,2,4,8] all have 1 bit.
 * [3,5,6] have 2 bits.
 * [7] has 3 bits.
 * The sorted array by bits is [0,1,2,4,8,3,5,6,7]
 *
 * Constraints:
 * 1. 1 <= arr.length <= 500
 * 2. 0 <= arr[i] <= 10^4
 */
public class SortIntegersByTheNumberOf1Bits {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortByBits(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8})));
    }

    /**
     * 转成 list，然后按照给定规则进行排序
     */
    public static int[] sortByBits(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        list.sort((o1, o2) -> {
            int count1 = Integer.bitCount(o1), count2 = Integer.bitCount(o2);
            if (count1 == count2) {
                return o1 - o2;
            }
            return count1 - count2;
        });

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
