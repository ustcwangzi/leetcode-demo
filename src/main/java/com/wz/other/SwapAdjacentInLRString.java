package com.wz.other;

/**
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX",
 * or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end,
 * return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 * Example 1:
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: true
 * Explanation: We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *
 * Example 2:
 * Input: start = "LLR", end = "RRL"
 * Output: false
 *
 * Constraints:
 * 1. 1 <= start.length <= 10^4
 * 2. start.length == end.length
 * 3. Both start and end will only consist of characters in 'L', 'R', and 'X'.
 */
public class SwapAdjacentInLRString {
    public static void main(String[] args) {
        System.out.println(canTransform("RXXLRXRXL", "XRLXXRRLX"));
        System.out.println(canTransform("XXRXXLXXXX", "XXXXRXXLXX"));
    }

    /**
     * 双指针
     * L 和 R 可以移动，因此只需要 start 和 end 这两个字符串里 L 和 R 的相对位置相同就可以
     * 又考虑到，L 只能向左移动，R 只能向右移动，因此对应位置的 L，start 中的 L 只能在 end 中 L 的右边（或者同一位置）
     * 对应位置的 R，start 中的 R 只能在 end 中的 R 的左边（或者同一位置）
     */
    public static boolean canTransform(String start, String end) {
        int i = 0, j = 0, n = start.length();
        while (true) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && end.charAt(j) == 'X') {
                j++;
            }
            if (i == n && j == n) {
                return true;
            }
            if (i == n || j == n) {
                return false;
            }
            // 去掉 X 后的字符串必须相等
            if (start.charAt(i) != end.charAt(j)) {
                return false;
            }
            // start 中的 R 必须出现在左边，start 中的 L 必须出现在右边
            if (start.charAt(i) == 'R' && i > j || start.charAt(i) == 'L' && i < j) {
                return false;
            }
            i++;
            j++;
        }
    }
}
