package com.wz.other;

/**
 * Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums.
 * If there are multiple answers, you may return any of them.
 *
 * Example 1:
 * Input: nums = ["01","10"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "00" would also be correct.
 *
 * Example 2:
 * Input: nums = ["111","011","001"]
 * Output: "101"
 * Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 *
 * Constraints:
 * 1. n == nums.length
 * 2. 1 <= n <= 16
 * 3. nums[i].length == n
 * 4. nums[i] is either '0' or '1'.
 * 5. All the strings of nums are unique.
 */
public class FindUniqueBinaryString {
    public static void main(String[] args) {
        System.out.println(findDifferentBinaryString(new String[]{"111", "011", "001"}));
    }

    /**
     * Cantor's Diagonalization
     * 遍历数组，从 nums[0] 的第一个字符生成一个不同字符、从 nums[1] 的第一个字符生成二个不同字符 ...，最后的结果就是唯一的
     * 如 [111, 011, 001]
     * 111 第一位是 1，生成 0
     * 011 第二位是 1，生成 0
     * 001 第三位是 1，生成 0
     */
    public static String findDifferentBinaryString(String[] nums) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            builder.append(nums[i].charAt(i) == '1' ? '0' : '1');
        }
        return builder.toString();
    }
}
