package com.wz.other;

/**
 * Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
 *
 * Example 1:
 * Input: n = 13, k = 2
 * Output: 10
 * Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 *
 * Example 2:
 * Input: n = 1, k = 1
 * Output: 1
 *
 * Constraints:
 * 1 <= k <= n <= 10^9
 */
public class KthSmallestInLexicographicalOrder {
    public static void main(String[] args) {
        System.out.println(findKthNumber(13, 2));
        System.out.println(findKthNumber(20, 12));
    }

    /**
     * 可以转换为十叉树的先序遍历 @link ../../../../resource/KthSmallestInLexicographicalOrder.jpg
     * 目标是从第 1 个节点开始，移动 k-1 步到目标节点，为了减少移动次数，先计算同一 level 邻居节点之间的步数以跳过不必要的先序遍历
     * 设当前处于 cur 节点，先计算同一 level 邻居节点之间的步数 step，若 step 小于 k，可以直接跳到邻居节点 cur+1，同时令 k -= step
     * 若 step 大于等于 k，需要进入树的下一层 cur*10，同时 k 减一，然后用同样的方法计算，最终到达目标节点
     * 如何计算节点之间的步数，假设当前节点为 cur，邻居节点为 next，若 next <= n，就把同一 level 从 cur 到 next 之间的节点全加上来，
     * 若 next > n，就把 cur 到 n（包括n）之间的节点全加上来，然后进入树的下一层，重复以上操作
     */
    public static int findKthNumber(int n, int k) {
        long cur = 1;
        while (k > 1) {
            long step = calStep(cur, cur + 1, n);
            if (step < k) {
                cur += 1;
                k -= step;
            } else {
                cur *= 10;
                k--;
            }
        }
        return (int) cur;
    }

    private static long calStep(long cur, long next, long n) {
        long step = 0;
        while (cur <= n) {
            step += Math.min(n + 1, next) - cur;
            cur *= 10;
            next *= 10;
        }
        return step;
    }
}
