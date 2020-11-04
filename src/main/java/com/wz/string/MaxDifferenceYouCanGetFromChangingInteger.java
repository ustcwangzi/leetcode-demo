package com.wz.string;

/**
 * You are given an integer num. You will apply the following steps exactly two times:
 *
 * Pick a digit x (0 <= x <= 9).
 * Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
 * Replace all the occurrences of x in the decimal representation of num by y.
 * The new integer cannot have any leading zeros, also the new integer cannot be 0.
 * Let a and b be the results of applying the operations to num the first and second times, respectively.
 *
 * Return the max difference between a and b.
 * Example 1:
 *
 * Input: num = 555
 * Output: 888
 * Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
 * The second time pick x = 5 and y = 1 and store the new integer in b.
 * We have now a = 999 and b = 111 and max difference = 888
 * Example 2:
 *
 * Input: num = 9
 * Output: 8
 * Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
 * The second time pick x = 9 and y = 1 and store the new integer in b.
 * We have now a = 9 and b = 1 and max difference = 8
 * Constraints:
 *
 * 1 <= num <= 10^8
 */
public class MaxDifferenceYouCanGetFromChangingInteger {
    public static void main(String[] args) {
        System.out.println(maxDiff(555));
    }

    /**
     * 对 num 进行两次操作：把 num 中的所有 x 都替换成 y。两次操作分别得到 a 和 b，求 a 和 b 的最大差值
     * 把不是 9 的数字全部替换成 9 得到最大的数
     * 若最高位不是 1，把最高位对应的数字全部替换成 1，得到最小的数
     * 若最高位是 1，则从第二个位置开始的数字全部替换换成 0，得到最小的数
     */
    public static int maxDiff(int num) {
        String str = Integer.toString(num);
        String max = str, min = str;

        // 替换为9，得到最大的数
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '9') {
                max = str.replace(str.charAt(i) + "", "9");
                break;
            }
        }

        // 最高位换成1
        if (str.charAt(0) != '1') {
            min = str.replace(str.charAt(0) + "", "1");
            return Integer.parseInt(max) - Integer.parseInt(min);
        }

        // 替换为1，得到最大的数
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1') {
                min = str.replace(str.charAt(i) + "", "0");
                break;
            }
        }

        return Integer.parseInt(max) - Integer.parseInt(min);
    }
}
