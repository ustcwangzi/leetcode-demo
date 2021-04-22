package com.wz.array;

import java.util.ArrayList;
import java.util.List;

/**
 * There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in clockwise order.
 * More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n,
 * and moving clockwise from the nth friend brings you to the 1st friend.
 * The rules of the game are as follows:
 * 1. Start at the 1st friend.
 * 2. Count the next k friends in the clockwise direction including the friend you started at. The counting wraps around the circle and may count some friends more than once.
 * 3. The last friend you counted leaves the circle and loses the game.
 * 4. If there is still more than one friend in the circle, go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
 * 5. Else, the last friend in the circle wins the game.
 * Given the number of friends, n, and an integer k, return the winner of the game.
 *
 * Example 1:
 * @see ../../../../resource/FindTheWinnerOfTheCircularGame.jpg
 * Input: n = 5, k = 2
 * Output: 3
 * Explanation: Here are the steps of the game:
 * 1) Start at friend 1.
 * 2) Count 2 friends clockwise, which are friends 1 and 2.
 * 3) Friend 2 leaves the circle. Next start is friend 3.
 * 4) Count 2 friends clockwise, which are friends 3 and 4.
 * 5) Friend 4 leaves the circle. Next start is friend 5.
 * 6) Count 2 friends clockwise, which are friends 5 and 1.
 * 7) Friend 1 leaves the circle. Next start is friend 3.
 * 8) Count 2 friends clockwise, which are friends 3 and 5.
 * 9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner.
 *
 * Constraints:
 * 1 <= k <= n <= 500
 */
public class FindTheWinnerOfTheCircularGame {
    public static void main(String[] args) {
        System.out.println(findTheWinner(5, 2));
        System.out.println(findTheWinner2(5, 2));
    }

    /**
     * 约瑟夫问题，方案一：直接使用 list 模拟整个过程
     */
    public static int findTheWinner(int n, int k) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 1; i < n; i++) {
            list.add(i);
        }

        int cur = 0;
        while (list.size() > 1) {
            cur = (cur + k - 1) % list.size();
            list.remove(cur);
        }
        return list.get(0);
    }

    /**
     * 方案二：递推公式 f(n) = (f(n-1) + k) % n
     */
    public static int findTheWinner2(int n, int k) {
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = (result + k) % i;
        }
        return result + 1;
    }
}
