package com.wz.string;

/**
 * There is a robot starting at position (0, 0), the origin, on a 2D plane. Given a sequence of its moves,
 * judge if this robot ends up at (0, 0) after it completes its moves.
 * The move sequence is represented by a string, and the character moves[i] represents its ith move.
 * Valid moves are R (right), L (left), U (up), and D (down).
 * If the robot returns to the origin after it finishes all of its moves, return true. Otherwise, return false.
 * Note: The way that the robot is "facing" is irrelevant. "R" will always make the robot move to the right once,
 * "L" will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.
 *
 * Example 1:
 * Input: moves = "UD"
 * Output: true
 * Explanation: The robot moves up once, and then down once. All moves have the same magnitude,
 * so it ended up at the origin where it started. Therefore, we return true.
 *
 * Example 2:
 * Input: moves = "LL"
 * Output: false
 * Explanation: The robot moves left twice. It ends up two "moves" to the left of the origin.
 * We return false because it is not at the origin at the end of its moves.
 */
public class RobotReturnToOrigin {
    public static void main(String[] args) {
        System.out.println(judgeCircle("UD"));
        System.out.println(judgeCircle("LL"));
    }

    /**
     * 要想使其最后返回原点，那么机器人向左走动的距离应当和向右走动的距离相等，同理，向上走动的距离和向下走动的距离相等；
     * 因此，只需要两个变量记录水平方向和垂直方向是否最后处在原点即可
     */
    public static boolean judgeCircle(String moves) {
        int hDirection = 0, vDirection = 0;
        for (int i = 0; i < moves.length(); i++) {
            switch (moves.charAt(i)) {
                case 'U':
                    vDirection++;
                    break;
                case 'D':
                    vDirection--;
                    break;
                case 'L':
                    hDirection--;
                    break;
                case 'R':
                    hDirection++;
                default:
                    break;
            }
        }
        return vDirection == 0 && hDirection == 0;
    }
}
