package com.wz.greedy;

/**
 * You are given a binary string binary consisting of only 0's or 1's. You can apply each of the following operations any number of times:
 * Operation 1: If the number contains the substring "00", you can replace it with "10".
 * For example, "00010" -> "10010"
 * Operation 2: If the number contains the substring "10", you can replace it with "01".
 * For example, "00010" -> "00001"
 * Return the maximum binary string you can obtain after any number of operations.
 * Binary string x is greater than binary string y if x's decimal representation is greater than y's decimal representation.
 *
 * Example 1:
 * Input: binary = "000110"
 * Output: "111011"
 * Explanation: A valid transformation sequence can be:
 * "000110" -> "000101"
 * "000101" -> "100101"
 * "100101" -> "110101"
 * "110101" -> "110011"
 * "110011" -> "111011"
 *
 * Constraints:
 * 1. 1 <= binary.length <= 10^5
 * 2. binary consist of '0' and '1'.
 */
public class MaximumBinaryStringAfterChange {
    public static void main(String[] args) {
        System.out.println(maximumBinaryString("000110"));
    }

    /**
     * 1. 首先，字符串中从左往右起的第一个 0 左侧的 1 不能往右移动
     * 2. 将第一个 0 右侧的所有 0 利用操作2连在一起，得到 *0...00* 的形式
     * 3. 利用操作1，将 *0...0* 变成 *1...10* 的形式，得到最终结果
     */
    public static String maximumBinaryString(String binary) {
        int n = binary.length(), cnt0 = 0, cnt1 = 0;
        // 统计左侧连续的 1 的个数
        int idx = 0;
        while (idx < n && binary.charAt(idx) == '1') {
            idx++;
        }
        // 统计左侧连续的 1 之后的 0 和 1 的个数
        for (int i = idx; i < n; i++) {
            if (binary.charAt(i) == '0') {
                cnt0++;
            } else {
                cnt1++;
            }
        }
        // 如果 0 的个数不足 2 个则直接返回
        if (cnt0 <= 1) {
            return binary;
        }

        StringBuilder builder = new StringBuilder();
        // 添加 idx 个 1
        while (idx-- > 0) {
            builder.append('1');
        }

        // 添加 cnt0-1 个 1 和一个 0
        while (--cnt0 > 0) {
            builder.append('1');
        }
        builder.append('0');

        // 添加 cnt1 个 1
        while (cnt1-- > 0) {
            builder.append('1');
        }
        return builder.toString();
    }
}
