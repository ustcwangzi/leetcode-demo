package com.wz.lists;

import java.util.Arrays;

/**
 * On an infinite number line, the position of the i-th stone is given by stones[i].
 * Call a stone an endpoint stone if it has the smallest or largest position.
 * Each turn, you pick up an endpoint stone and move it to an unoccupied position so that it is no longer an endpoint stone.
 * In particular, if the stones are at say, stones = [1,2,5], you cannot move the endpoint stone at position 5,
 * since moving it to any position (such as 0, or 3) will still keep that stone as an endpoint stone.
 * The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.
 * When the game ends, what is the minimum and maximum number of moves that you could have made?
 * Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]
 *
 * Example 1:
 * Input: [7,4,9]
 * Output: [1,2]
 * Explanation:
 * We can move 4 -> 8 for one move to finish the game.
 * Or, we can move 9 -> 5, 4 -> 6 for two moves to finish the game.
 *
 * Example 2:
 * Input: [6,5,4,3,10]
 * Output: [2,3]
 * We can move 3 -> 8 then 10 -> 7 to finish the game.
 * Or, we can move 3 -> 7, 4 -> 8, 5 -> 9 to finish the game.
 * Notice we cannot move 10 -> 2 to finish the game, because that would be an illegal move.
 *
 * Example 3:
 * Input: [100,101,104,102,103]
 * Output: [0,0]
 */
public class MovingStonesUntilConsecutiveII {
    public static void main(String[] args) {
        int[] stones = new int[]{7, 4, 9};
        System.out.println(Arrays.toString(numMovesStonesII(stones)));

        stones = new int[]{6, 5, 4, 3, 10};
        System.out.println(Arrays.toString(numMovesStonesII(stones)));

        stones = new int[]{100, 101, 104, 102, 103};
        System.out.println(Arrays.toString(numMovesStonesII(stones)));
    }

    /**
     * 在一个长度无限的数轴上，第 i 颗石子的位置为 stones[i]。如果一颗石子的位置最小/最大，那么该石子被称作端点石子。
     * 每个回合，你可以将一颗端点石子拿起并移动到一个未占用的位置，使得该石子不再是一颗端点石子。
     * 值得注意的是，如果石子像 stones = [1,2,5] 这样，你将无法移动位于位置 5 的端点石子，因为无论将它移动到任何位置（例如 0 或 3），
     * 该石子都仍然会是端点石子。当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。
     *
     * 最终的状态是连续的一段区间，可以等价为用一段连续区间（长度为len(stones)）去覆盖原始数组，
     * 分别求最多、最少覆盖的元素个数，很容易想到sliding window
     * 1. 对于下界，要找到一个区间 i...j 把所有的数能扔进去，需要考虑限制条件：
     *    a[j] - a[i] +1 不能大于n（大于n肯定会有空位）
     *    但还有种特殊情况，如1,2,3,4,10, 区间1-4里已经是连续了，10不能直接到5或0，这种情况只能是2， 先1->6, 再10->5
     * 2. 上界是这两种情况之一：全移到左边，全移到右边
     */
    public static int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int i = 0, n = stones.length, min = n;
        int max = Math.max(stones[n - 1] - n + 2 - stones[1], stones[n - 2] - stones[0] - n + 2);

        for (int j = 0; j < n; ++j) {
            while (stones[j] - stones[i] >= n) {
                ++i;
            }
            // j-i+1 == n-1 表示从i到j有n个可能的连续数里的n-1个
            // 第二个判断条件判断n-1个数都连续，还是有一个地方不连续，差1
            if (j - i + 1 == n - 1 && stones[j] - stones[i] == n - 2) {
                min = Math.min(min, 2);
            } else {
                // j-i+1 == n 表示n个数都连续
                min = Math.min(min, n - (j - i + 1));
            }
        }
        return new int[]{min, max};
    }
}
