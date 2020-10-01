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

    /**
     * 如果一个数的每一位之和是3的倍数，那个这个数可以被3整除。
     * 假设整个数组之和是3的倍数：
     * ①如果要得到的最大的 3 的倍数，那么要得到这个最大的3的倍数，可以对数组进行排序，最后按顺序输出这个数组即可。例如如果是从小到大排序，那么倒序输出这个数组就是最大的3的倍数。
     * 接下来要解决的问题是如果整个数组之和不是3的倍数：
     * 假设数组之和是sum，remainder = sum%3 != 0，可以考虑到remainder = 1 或者remainder=2。
     * ②如果remainder=1，那么我们需要删除一个最小的且余3为1的即可、或者删除两个最小的且余3为2的数字即可。
     * ③如果remainder=2，那么我们需要删除一个最小的且余3为2的即可、或者删除两个最小的且余3为1的数字即可。
     */
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
