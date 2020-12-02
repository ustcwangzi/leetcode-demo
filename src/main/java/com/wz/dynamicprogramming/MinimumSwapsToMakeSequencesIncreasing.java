package com.wz.dynamicprogramming;

import com.wz.array.BestTimeToBuyAndSellStockWithTransactionFee;

import java.util.Arrays;

/**
 * We have two integer sequences A and B of the same non-zero length.
 * We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.
 * At the end of some number of swaps, A and B are both strictly increasing.
 * (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.
 * It is guaranteed that the given input always makes it possible.
 *
 * Example:
 * Input: A = [1,3,5,4], B = [1,2,3,7]
 * Output: 1
 * Explanation:
 * Swap A[3] and B[3].  Then the sequences are:
 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 * which are both strictly increasing.
 *
 * Note:
 * 1. A, B are arrays with the same length, and that length will be in the range [1, 1000].
 * 2. A[i], B[i] are integer values in the range [0, 2000].
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    public static void main(String[] args) {
        System.out.println(minSwap(new int[]{1, 3, 5, 4}, new int[]{1, 2, 3, 7}));
    }

    /**
     * 当前位置 i 是否交换，只取决于和前面一位是否是严格递增的，而前一位也有交换或不交换两种状态，那么前一位的不同状态也会影响到当前是否交换，
     * 与 {@link BestTimeToBuyAndSellStockWithTransactionFee} 类似
     * 这里也需要维护两种状态，swap 和 noSwap，那么 swap[i] 表示范围 [0,i] 的子数组同时严格递增且当前位置 i 需要交换的最小交换次数，
     * noSwap[i] 表示范围 [0,i] 的子数组同时严格递增且当前位置 i 不交换的最小交换次数，两个数组里的值都初始化为 n。
     * 由于需要跟前一个数字比较，所以从第二个数字开始遍历，那么就需要给 swap 和 noSwap 数组的第一个数字提前赋值，
     * swap[0] 赋值为 1，因为其表示i位置需要交换，noSwap[0] 赋值为 0，因为其表示 i 位置不需要交换
     * 由于这道题限制了一定能通过交换生成两个同时严格递增的数组，那么两个数组当前位置和前一个位置之间的关系只有两种，
     * 一种是不用交换，当前位置的数字已经分别大于前一个位置；另一种是需要交换后，当前位置的数字才能分别大于前一个数字。
     * 如果当前位置已经分别大于前一位置的数了，是不需要再进行交换的，但是 swap[i] 限定了必须要交换当前位置 i，
     * 既然当前位置要交换，那么前一个位置 i-1 也要交换，同时交换才能继续保证同时递增，这样 swap[i] 就可以赋值为 swap[i-1] + 1 。
     * 而 noSwap[i] 直接赋值为 noSwap[i-1] 即可，因为不需要交换了。
     * 第二种情况是需要交换当前位置，才能保证递增。那么 swap[i] 正好也是要交换当前位置，而前一个位置不能交换，
     * 那么即可以用 noSwap[i-1] + 1 来更新 swap[i]。而 noSwap[i] 是不能交换当前位置，那么可以通过交换前一个位置来同样实现递增，
     * 即可以用 swap[i-1] 来更新 noSwap[i]。当循环结束后，在 swap[n-1] 和 noSwap[n-1] 中返回较小值即可
     */
    public static int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] swap = new int[n], noSwap = new int[n];
        Arrays.fill(swap, n);
        Arrays.fill(noSwap, n);
        swap[0] = 1;
        noSwap[0] = 0;

        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                swap[i] = swap[i - 1] + 1;
                noSwap[i] = noSwap[i - 1];
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                swap[i] = Math.min(swap[i], noSwap[i - 1] + 1);
                noSwap[i] = Math.min(noSwap[i], swap[i - 1]);
            }
        }
        return Math.min(swap[n - 1], noSwap[n - 1]);
    }
}
