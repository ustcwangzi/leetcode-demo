package com.wz.math;

/**
 * Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors.
 * If there is no such integer in the array, return 0.
 *
 * Example 1:
 * Input: nums = [21,4,7]
 * Output: 32
 * Explanation:
 * 21 has 4 divisors: 1, 3, 7, 21
 * 4 has 3 divisors: 1, 2, 4
 * 7 has 2 divisors: 1, 7
 * The answer is the sum of divisors of 21 only.
 */
public class FourDivisors {
    public static void main(String[] args) {
        System.out.println(sumFourDivisors(new int[]{21, 4, 7}));
    }

    public static int sumFourDivisors(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result += findSumDivisor(num);
        }
        return result;
    }

    public static int findSumDivisor(int number) {
        int count = 0; // to count the number of divisors, here we only need to find until count = 2 as 1 and the number is already considered.
        int sum = number + 1; // as the number and 1 will be divissors
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                if (number / i == i) {
                    return 0; // only the number and 1 as divisors, so return 0
                } else {
                    sum += i + number / i;
                    count += 2;
                }
            }
        }
        return count == 2 ? sum : 0;
    }
}
