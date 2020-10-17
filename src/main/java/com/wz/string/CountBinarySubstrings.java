package com.wz.string;

/**
 * Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's,
 * and all the 0's and all the 1's in these substrings are grouped consecutively.
 * Substrings that occur multiple times are counted the number of times they occur.
 *
 * Example:
 * Input: "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's:
 * "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 */
public class CountBinarySubstrings {
    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("00110011"));
    }

    /**
     * 统计具有相同0和1的个数，且0和1各自都在一起(即0和1不能交替出现)的子字符串的个数
     * 以 00110011 为例，当第一个1出现的时候，由于前面有两个0，所以肯定能组成01，再遇到下一个1时，此时1有2个，0有2个，能组成0011，
     * 下一个遇到0时，此时0的个数重置为1，而1的个数有两个，所以一定有10，同理，下一个还为0，就会有1100存在，之后的也是这样分析。
     * 可以发现只要分别统计0和1的个数，而且如果当前遇到的是1，那么只要之前统计的0的个数大于当前1的个数，就一定有一个对应的子字符串，
     * 而一旦前一个数字和当前的数字不一样的时候，那么当前数字的计数要重置为1
     * 使用 pre 和 cur 两个变量分别表示前面的数字个数和当前数字个数，遍历，如果当前数字和前面的数字相同，则 cur 自增1，
     * 否则 pre 赋值为 cur，cur 重置为1，然后判断如果 pre 大于等于 cur，结果加1
     */
    public static int countBinarySubstrings(String s) {
        int result = 0;
        char pre = 0, cur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cur++;
            } else {
                pre = cur;
                cur = 1;
            }
            if (pre >= cur) {
                result++;
            }
        }
        return result;
    }
}
