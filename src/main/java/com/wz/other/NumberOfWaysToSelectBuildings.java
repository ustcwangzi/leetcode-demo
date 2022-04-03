package com.wz.other;

/**
 * You are given a 0-indexed binary string s which represents the types of buildings along a street where:
 * - s[i] = '0' denotes that the ith building is an office and
 * - s[i] = '1' denotes that the ith building is a restaurant.
 * As a city official, you would like to select 3 buildings for random inspection.
 * However, to ensure variety, no two consecutive buildings out of the selected buildings can be of the same type.
 * For example, given s = "001101", we cannot select the 1st, 3rd, and 5th buildings as that would form "011"
 * which is not allowed due to having two consecutive buildings of the same type.
 * Return the number of valid ways to select 3 buildings.
 *
 * Example 1:
 * Input: s = "001101"
 * Output: 6
 * Explanation:
 * The following sets of indices selected are valid:
 * - [0,2,4] from "001101" forms "010"
 * - [0,3,4] from "001101" forms "010"
 * - [1,2,4] from "001101" forms "010"
 * - [1,3,4] from "001101" forms "010"
 * - [2,4,5] from "001101" forms "101"
 * - [3,4,5] from "001101" forms "101"
 * No other selection is valid. Thus, there are 6 total ways.
 *
 * Example 2:
 * Input: s = "11100"
 * Output: 0
 * Explanation: It can be shown that there are no valid selections.
 *
 * Constraints:
 * 1. 3 <= s.length <= 10^5
 * 2. s[i] is either '0' or '1'.
 */
public class NumberOfWaysToSelectBuildings {
    public static void main(String[] args) {
        System.out.println(numberOfWays("001101"));
    }

    /**
     * 只有两种满足条件的字符串 101 or 010
     * - 101，中间是 0，计算左右两侧 1 的个数即可
     * - 010，中间是 1，计算左右两侧 0 的个数即可
     * 遍历字符串，分别统计 0、1 的个数并记录在 zero[]、one[] 中
     * 再次遍历字符串：
     * - 如果当前是 '0'，则满足条件的个数为: (左侧 1 的个数) *  (右侧 1 的个数)
     * - 如果当前是 '1'，则满足条件的个数为: (左侧 0 的个数) *  (右侧 0 的个数)
     */
    public static long numberOfWays(String s) {
        int n = s.length(), zeroCount = 0, oneCount = 0;
        int[] zero = new int[n], one = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            } else {
                oneCount++;
            }
            zero[i] = zeroCount;
            one[i] = oneCount;
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                result += (long) one[i] * (oneCount - one[i]);
            } else {
                result += (long) zero[i] * (zeroCount - zero[i]);
            }
        }
        return result;
    }
}
