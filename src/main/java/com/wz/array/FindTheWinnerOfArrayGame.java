package com.wz.array;

/**
 * Given an integer array arr of distinct integers and an integer k.
 * A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]). In each round of the game,
 * we compare arr[0] with arr[1], the larger integer wins and remains at position 0 and the smaller integer moves to
 * the end of the array. The game ends when an integer wins k consecutive rounds.
 * Return the integer which will win the game.
 * It is guaranteed that there will be a winner of the game.
 *
 * Example 1:
 * Input: arr = [2,1,3,5,4,6,7], k = 2
 * Output: 5
 * Explanation: Let's see the rounds of the game:
 * Round |       arr       | winner | win_count
 *   1   | [2,1,3,5,4,6,7] | 2      | 1
 *   2   | [2,3,5,4,6,7,1] | 3      | 1
 *   3   | [3,5,4,6,7,1,2] | 5      | 1
 *   4   | [5,4,6,7,1,2,3] | 5      | 2
 * So we can see that 4 rounds will be played and 5 is the winner because it wins 2 consecutive games.
 *
 * Example 2:
 * Input: arr = [3,2,1], k = 10
 * Output: 3
 * Explanation: 3 will win the first 10 rounds consecutively.
 */
public class FindTheWinnerOfArrayGame {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 3, 5, 4, 6, 7};
        System.out.println(getWinner(arr, 2));

        arr = new int[]{3, 2, 1};
        System.out.println(getWinner(arr, 10));
    }

    /**
     * 分别用 cur 和 count 来记录当前较大的数以及连续获胜的次数，如果找到连续获胜 k 次的数就返回
     * 遍历完整个数组也没有找到连续获胜 k 次的数，直接返回 cur 即可
     */
    public static int getWinner(int[] arr, int k) {
        int cur = arr[0], count = 0;
        for (int i = 1; i < arr.length; i++) {
            // 找到更大的数，更新 cur，同时count置0
            if (arr[i] > cur) {
                cur = arr[i];
                count = 0;
            }
            if (++count == k) {
                return cur;
            }
        }
        return cur;
    }
}
