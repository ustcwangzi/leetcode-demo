package com.wz.sort;

/**
 * 给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数
 *
 * 示例1
 * 输入：7,2
 * 返回值："111"
 *
 * 备注：
 * M是32位整数，2<=N<=16.
 */
public class ConvertToNHex {
    public static void main(String[] args) {
        System.out.println(convert(7, 2));
    }

    public static String convert(int m, int n) {
        if (m == 0) {
            return "0";
        }

        char[] nums = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder builder = new StringBuilder();
        boolean flag = m < 0;
        m = Math.abs(m);
        while (m > 0) {
            builder.append(nums[m % n]);
            m /= n;
        }

        String str = builder.reverse().toString();
        return flag ? "-" + str : str;
    }
}
