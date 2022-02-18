package com.wz.other;

/**
 * Anti-theft security devices are activated inside a bank. You are given a 0-indexed binary string array bank representing the floor plan of the bank,
 * which is an m x n 2D matrix. bank[i] represents the ith row, consisting of '0's and '1's. '0' means the cell is empty, while'1' means the cell has a security device.
 * There is one laser beam between any two security devices if both conditions are met:
 * 1. The two devices are located on two different rows: r1 and r2, where r1 < r2.
 * 2. For each row i where r1 < i < r2, there are no security devices in the ith row.
 * Laser beams are independent, i.e., one beam does not interfere nor join with another.
 * Return the total number of laser beams in the bank.
 *
 * Example 1:
 * @link ../../../../resource/NumberOfLaserBeamsInBank1.jpg
 * Input: bank = ["011001","000000","010100","001000"]
 * Output: 8
 * Explanation: Between each of the following device pairs, there is one beam. In total, there are 8 beams:
 *  * bank[0][1] -- bank[2][1]
 *  * bank[0][1] -- bank[2][3]
 *  * bank[0][2] -- bank[2][1]
 *  * bank[0][2] -- bank[2][3]
 *  * bank[0][5] -- bank[2][1]
 *  * bank[0][5] -- bank[2][3]
 *  * bank[2][1] -- bank[3][2]
 *  * bank[2][3] -- bank[3][2]
 * Note that there is no beam between any device on the 0th row with any on the 3rd row.
 * This is because the 2nd row contains security devices, which breaks the second condition.
 *
 * Example 2:
 * @link ../../../../resource/NumberOfLaserBeamsInBank2.jpg
 * Input: bank = ["000","111","000"]
 * Output: 0
 * Explanation: There does not exist two devices located on two different rows.
 *
 * Constraints:
 * 1. m == bank.length
 * 2. n == bank[i].length
 * 3. 1 <= m, n <= 500
 * 4. bank[i][j] is either '0' or '1'.
 */
public class NumberOfLaserBeamsInBank {
    public static void main(String[] args) {
        System.out.println(numberOfBeams(new String[]{"011001", "000000", "010100", "001000"}));
    }

    /**
     * 找出 1 的数量不为 0 的行，这些相邻行之间的 1 的个数乘积之和就是最终结果
     */
    public static int numberOfBeams(String[] bank) {
        int result = 0, preCount = 0;
        for (String str : bank) {
            int cur = count(str);
            if (cur != 0) {
                result += cur * preCount;
                preCount = cur;
            }
        }
        return result;
    }

    private static int count(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}
