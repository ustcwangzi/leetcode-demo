package com.wz.string;

/**
 * Given a number s in their binary representation. Return the number of steps to reduce it to 1 under the following rules:
 *
 * If the current number is even, you have to divide it by 2.
 *
 * If the current number is odd, you have to add 1 to it.
 *
 * It's guaranteed that you can always reach to one for all testcases.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1101"
 * Output: 6
 * Explanation: "1101" corressponds to number 13 in their decimal representation.
 * Step 1) 13 is odd, add 1 and obtain 14.
 * Step 2) 14 is even, divide by 2 and obtain 7.
 * Step 3) 7 is odd, add 1 and obtain 8.
 * Step 4) 8 is even, divide by 2 and obtain 4.
 * Step 5) 4 is even, divide by 2 and obtain 2.
 * Step 6) 2 is even, divide by 2 and obtain 1.
 * Example 2:
 *
 * Input: s = "10"
 * Output: 1
 * Explanation: "10" corressponds to number 2 in their decimal representation.
 * Step 1) 2 is even, divide by 2 and obtain 1.
 * Example 3:
 *
 * Input: s = "1"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of characters '0' or '1'
 * s[0] == '1'
 */
public class NumberOfStepsToReduceNumberInBinaryRepresentationToOne {
    public static void main(String[] args) {
        System.out.println(numSteps("1101"));
    }

    /**
     * 给定一个字符串形式的二进制数a，对其进行如下操作：
     * 1、若 a 是偶数则将其除以 2；
     * 2、否则将其减去 1。
     * 进行多少次上面操作能将其最后变为 1
     * 思路是模拟进位加法。从后向前遍历字符串，用 carry 存进位，用 result 记录操作次数。
     * 每次遍历到一个数字时，看一下它与 carry 的和是否是 1，如果是，说明当前位置需要加上 1 再除以 2，也就是要两步；
     * 否则只需要一步，也就是直接除以 2。遍历到字符串的第二个位置的时候就停止，此时若 carry = 0 则说明原数字操作完后只剩下1，
     * 那就直接返回操作次数；若 carry = 1 则说明原数字操作完后发生了最高位的进位，变成了 10，所以操作次数还需要加 1 再返回
     */
    public static int numSteps(String s) {
        int result = 0, carry = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            result++;
            if (s.charAt(i) - '0' + carry == 1) {
                result++;
                carry = 1;
            }
        }

        return result + carry;
    }
}
