package com.wz.math;

/**
 * Given an integer array of digits, return the largest multiple of three that can be formed by concatenating some of the given digits in any order.
 * Since the answer may not fit in an integer data type, return the answer as a string.
 * If there is no answer return an empty string.
 *
 * Example 1:
 * Input: digits = [8,1,9]
 * Output: "981"
 *
 * Example 2:
 * Input: digits = [8,6,7,1,0]
 * Output: "8760"
 */
public class LargestMultipleOfThree {
    public static void main(String[] args) {
        System.out.println(largestMultipleOfThree(new int[]{8, 1, 9}));
    }

    public static String largestMultipleOfThree(int[] digits) {
        int[] digitCount = new int[10];
        int digitSum = 0;
        for (int d : digits) {
            digitSum += d;
            digitCount[d]++;
        }

        for (int i = 0; i < 2; ++i) {
            if (digitSum % 3 == 2) {
                if (digitCount[2] > 0) {
                    digitCount[2]--;
                    digitSum -= 2;
                } else if (digitCount[5] > 0) {
                    digitCount[5]--;
                    digitSum -= 5;
                } else if (digitCount[8] > 0) {
                    digitCount[8]--;
                    digitSum -= 8;
                } else if (digitCount[1] > 0) {
                    digitCount[1] -= 1;
                    digitSum -= 1;
                } else if (digitCount[4] > 0) {
                    digitCount[4] -= 1;
                    digitSum -= 4;
                } else if (digitCount[7] > 0) {
                    digitCount[7] -= 1;
                    digitSum -= 7;
                }
            } else if (digitSum % 3 == 1) {
                if (digitCount[1] > 0) {
                    digitCount[1]--;
                    digitSum -= 1;
                } else if (digitCount[4] > 0) {
                    digitCount[4]--;
                    digitSum -= 4;
                } else if (digitCount[7] > 0) {
                    digitCount[7]--;
                    digitSum -= 7;
                } else if (digitCount[2] > 0) {
                    digitCount[2] -= 1;
                    digitSum -= 2;
                } else if (digitCount[5] > 0) {
                    digitCount[5] -= 1;
                    digitSum -= 5;
                } else if (digitCount[8] > 0) {
                    digitCount[8] -= 1;
                    digitSum -= 8;
                }
            }
        }
        if (digitSum % 3 != 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; --i) {
            for (int j = 0; j < digitCount[i]; ++j) {
                sb.append(i);
            }
        }
        if (sb.length() > 0 && sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }

    /**
     * 删除指定位置的数字构建字符串
     */
    private static String removeAndPrintResult(int[] digits, int ind1, int ind2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i != ind1 && i != ind2) {
                stringBuilder.append(digits[i]);
            }
        }
        return stringBuilder.toString();
    }
}
