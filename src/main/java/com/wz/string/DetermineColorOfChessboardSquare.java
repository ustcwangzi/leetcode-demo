package com.wz.string;

/**
 * You are given coordinates, a string that represents the coordinates of a square of the chessboard. Below is a chessboard for your reference.
 * @link ../../../../resource/DetermineColorOfChessboardSquare.jpg
 * Return true if the square is white, and false if the square is black.
 * The coordinate will always represent a valid chessboard square. The coordinate will always have the letter first, and the number second.
 *
 * Example 1:
 * Input: coordinates = "a1"
 * Output: false
 * Explanation: From the chessboard above, the square with coordinates "a1" is black, so return false.
 *
 * Example 2:
 * Input: coordinates = "h3"
 * Output: true
 * Explanation: From the chessboard above, the square with coordinates "h3" is white, so return true.
 *
 * Constraints:
 * 1. coordinates.length == 2
 * 2. 'a' <= coordinates[0] <= 'h'
 * 3. '1' <= coordinates[1] <= '8'
 */
public class DetermineColorOfChessboardSquare {
    public static void main(String[] args) {
        System.out.println(squareIsWhite("a1"));
        System.out.println(squareIsWhite("h3"));
    }

    /**
     * 将横坐标即 'a'～'h' 转换成数字 0～7，然后观察棋盘可以发现：
     * 偶奇为黑，偶偶为白，奇偶为黑，奇奇为白，即当两个数字的奇偶相同的时候为白色
     */
    public static boolean squareIsWhite(String coordinates) {
        int x = coordinates.charAt(0) - 'a', y = coordinates.charAt(1) - '0';
        boolean xEven = x % 2 == 0, yEven = y % 2 == 0;
        return xEven == yEven;
    }
}
