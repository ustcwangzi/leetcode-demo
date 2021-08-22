package com.wz.other;

import java.util.Random;

/**
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).
 * We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1].
 * pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3],
 * the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).
 * More formally, the probability of picking index i is w[i] / sum(w).
 *
 * Example 1:
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 *
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has probability of 1/4.
 *
 * Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 *
 * Constraints:
 * 1. 1 <= w.length <= 10000
 * 2. 1 <= w[i] <= 10^5
 * 3. pickIndex will be called at most 10000 times.
 */
public class RandomPickWithWeight {
    public static void main(String[] args) {
        RandomPickWithWeight randomPickWithWeight = new RandomPickWithWeight(new int[]{1, 3});
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(randomPickWithWeight.pickIndex());
            }
            System.out.println();
        }
    }

    private final int[] preSum;

    /**
     * 根据权重来随机取点，比如 [1,3]，表示有两个点，权重分别为 1 和 3，那么就是说一个点的出现概率是四分之一，另一个出现的概率是四分之三
     * 由于权重是 1 和 3，相加为 4，那么假设有 4 个点，然后随机等概率取一个点，随机到第一个点后就表示原来的第一个点，随机到后三个点就表示原来的第二个点，这样就可以保证有权重的随机了
     * 那么可以建立权重数组的累加和数组，比如 [1,3,2]，累加和数组为 [1,4,6]，整个的权重和为 6，先随机出 [0,5]，随机到 0 则为第一个点，随机到 1,2,3 则为第二个点，随机到 4,5 则为第三个点
     * 所以随机出一个数字 value 后，然后使用二分查找在累加和数组中查找第一个大于随机数 value 的数字所在坐标，就是结果
     */
    public RandomPickWithWeight(int[] w) {
        preSum = new int[w.length];
        preSum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            preSum[i] = preSum[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int value = new Random().nextInt(preSum[preSum.length - 1]);
        int left = 0, right = preSum.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (preSum[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
