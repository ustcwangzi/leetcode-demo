package com.wz.string;

/**
 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.
 * Each digit must be rotated - we cannot choose to leave it alone.
 * A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves;
 * 2 and 5 rotate to each other (on this case they are rotated in a different direction, in other words 2 or 5 gets mirrored);
 * 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
 * Now given a positive number N, how many numbers X from 1 to N are good?
 *
 * Example:
 * Input: 10
 * Output: 4
 * Explanation:
 * There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 * Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 *
 * Note:
 * N  will be in range [1, 10000].
 */
public class RotatedDigits {
    public static void main(String[] args) {
        System.out.println(rotatedDigits(10));
    }

    /**
     * 好数字: 把每位上的数字自身翻转一下，能得到一个不同的数字，翻转规则定义为，0，1，和8翻转后还为其本身，2和5，6和9可以互相翻转。
     * 翻转规则中没有提到的数字有三个，3，4，和7，说明这三个数字无法翻转，若一旦被翻转，则无法形成 valid 的数字
     */
    public static int rotatedDigits(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            int n = i;
            boolean isValid = true, isGood = false;
            while (n > 0) {
                int d = n % 10;
                n /= 10;
                if (d == 3 || d == 4 || d == 7) {
                    isValid = false;
                    break;
                } else if (d == 2 || d == 5 || d == 6 || d == 9) {
                    isGood = true;
                }
            }

            if (isValid && isGood) {
                count++;
            }
        }
        return count;
    }
}
