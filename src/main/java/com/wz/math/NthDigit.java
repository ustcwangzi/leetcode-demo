package com.wz.math;

/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 *
 * Example 1:
 * Input:
 * 3
 * Output:
 * 3
 *
 * Example 2:
 * Input:
 * 11
 * Output:
 * 0
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */
public class NthDigit {
    public static void main(String[] args) {
        System.out.println(findNthDigit(11));
    }

    /**
     * 该序列含有以下规律：
     * 1-9，有9个一位数，当n小于等于9的时候，可以在里面找到值
     * 10-99，有90个两位数，当n大于9且小于等于180+9=189时，可以在里面找到值
     * 100-999，有900个三位数，当n大于189且小于900x3+189=2889时，可以在里面找到值
     * ...
     * 可以将此问题分解为三个步骤：
     * 1. 计算该数字的位数，确定范围
     * 2. 找出该数字
     * 3. 确定是该数字中的第几位数
     *
     * 例如：303
     * 第一步，因为 189<303<2889，所以要找的是一个三位数，303-189=114，此时n变成114
     * 第二步，要找的数变成了三位数中的第114位，那就可以计算出三位数中的第114位数是 100+(114-1)/3=137，
     *        这里减1是因为在字符串中，索引是从0开始的，而序列字符串是从1开始
     * 第三步，计算是137中的第几位数，(114-1)%3=2，也就是137的第2位（从0开始）数7，就是结果。
     */
    public static int findNthDigit(int n) {
        long start = 1, count = 1, num = 9;
        while (n > num * count) {
            n -= num * count;
            count += 1;
            start *= 10;
            num *= 10;
        }

        start += (n - 1) / count;
        String result = start + "";
        long index = (n - 1) % count;
        return result.charAt((int) index) - '0';
    }
}
