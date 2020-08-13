package com.wz.array;

/**
 * Given an array nums of integers, return how many of them contain an even number of digits.
 *
 * Example 1:
 * Input: nums = [12,345,2,6,7896]
 * Output: 2
 * Explanation:
 * 12 contains 2 digits (even number of digits).
 * 345 contains 3 digits (odd number of digits).
 * 2 contains 1 digit (odd number of digits).
 * 6 contains 1 digit (odd number of digits).
 * 7896 contains 4 digits (even number of digits).
 * Therefore only 12 and 7896 contain an even number of digits.
 *
 * Example 2:
 * Input: nums = [555,901,482,1771]
 * Output: 1
 * Explanation:
 * Only 1771 contains an even number of digits.
 */
public class FindNumbersWithEvenNumberOfDigits {
    public static void main(String[] args) {
        int[] nums = new int[]{12, 345, 2, 6, 7896};
        System.out.println(findNumbers(nums));
        System.out.println(findNumbers2(nums));

        nums = new int[]{555, 901, 482, 1771};
        System.out.println(findNumbers(nums));
        System.out.println(findNumbers2(nums));
    }

    public static int findNumbers(int[] nums) {
        int result = 0;
        for (int num : nums) {
            int number = 1;
            while (num / 10 > 0) {
                number++;
                num /= 10;
            }
            result += number % 2 == 0 ? 1 : 0;
        }

        return result;
    }

    public static int findNumbers2(int[] nums) {
        int result = 0;
        for (int num : nums) {
            int number = String.valueOf(num).length();
            result += number % 2 == 0 ? 1 : 0;
        }

        return result;
    }
}
