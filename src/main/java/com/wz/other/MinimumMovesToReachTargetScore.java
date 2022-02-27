package com.wz.other;

/**
 * You are playing a game with integers. You start with the integer 1 and you want to reach the integer target.
 * In one move, you can either:
 * 1. Increment the current integer by one (i.e., x = x + 1).
 * 2. Double the current integer (i.e., x = 2 * x).
 * You can use the increment operation any number of times, however, you can only use the double operation at most maxDoubles times.
 * Given the two integers target and maxDoubles, return the minimum number of moves needed to reach target starting with 1.
 *
 * Example 1:
 * Input: target = 5, maxDoubles = 0
 * Output: 4
 * Explanation: Keep incrementing by 1 until you reach target.
 *
 * Example 2:
 * Input: target = 19, maxDoubles = 2
 * Output: 7
 * Explanation: Initially, x = 1
 * Increment 3 times so x = 4
 * Double once so x = 8
 * Increment once so x = 9
 * Double again so x = 18
 * Increment once so x = 19
 *
 * Example 3:
 * Input: target = 10, maxDoubles = 4
 * Output: 4
 * Explanation: Initially, x = 1
 * Increment once so x = 2
 * Double once so x = 4
 * Increment once so x = 5
 * Double again so x = 10
 *
 * Constraints:
 * 1. 1 <= target <= 10^9
 * 2. 0 <= maxDoubles <= 100
 */
public class MinimumMovesToReachTargetScore {
    public static void main(String[] args) {
        System.out.println(minMoves(19, 2));
        System.out.println(minMoves(10, 4));
    }

    /**
     * 从 1 转换到 target，等价于从 target 转换到 1
     * 当 target > 1 && maxDoubles > 0 时，若 target 为奇数，则 target--，同时次数增加
     * 若 target 为偶数，则 target/2，同时次数增加
     * 最后 maxDoubles 肯定用完了，只能继续通过减一操作将 target 变为 1
     */
    public static int minMoves(int target, int maxDoubles) {
        int count = 0;
        while (target > 1 && maxDoubles > 0) {
            if ((target & 1) == 1) {
                target--;
                count++;
            }
            target /= 2;
            maxDoubles--;
            count++;
        }
        return count + target - 1;
    }
}
