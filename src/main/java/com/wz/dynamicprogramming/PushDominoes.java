package com.wz.dynamicprogramming;

/**
 * There are N dominoes in a line, and we place each domino vertically upright.
 * In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 * @link ../../../../resource/PushDominoes.jpg
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left.
 * Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 * Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left;
 * S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
 * Return a string representing the final state.
 *
 * Example 1:
 * Input: ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 *
 * Example 2:
 * Input: "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second domino.
 *
 * Note:
 * 1. 0 <= N <= 10^5
 * 2. String dominoes contains only 'L', 'R' and '.'
 */
public class PushDominoes {
    public static void main(String[] args) {
        System.out.println(pushDominoes(".L.R...LR..L.."));
    }

    /**
     * 某个位置的骨牌会不会倒，并且朝哪个方向倒，是由左右两边受到的力的大小决定的，那么可以分为下列四种情况：
     * 1）R....R  ->  RRRRRR
     * 这是当两个向右推的操作连在一起时，那么中间的骨牌毫无悬念的都要向右边倒去。
     * 2）L....L  ->  LLLLLL
     * 同理，当两个向左推的操作连在一起时，那么中间的骨牌毫无悬念的都要向左边倒去。
     * 3）L....R  ->  L....R
     * 当左边界的骨牌向左推，右边界的骨牌向右推，那么中间的骨牌不会收到力，所以依然保持坚挺。
     * 4）R....L  -> RRRLLL   or   R.....L  ->  RRR.LLL
     * 当左边界的骨牌向右推，右边界的骨牌向左推时，就要看中间的骨牌个数了，若是偶数，那么对半分，若是奇数，那么最中间的骨牌保持站立，其余的对半分。
     */
    public static String pushDominoes(String dominoes) {
        char[] array = dominoes.toCharArray();
        int n = array.length, left, right = -1;
        for (int i = 0; i < n; ++i) {
            if (array[i] == 'L') {
                left = i;
                if (right > -1) {
                    while (left - 1 > right + 1) {
                        array[left - 1] = 'L';
                        array[right + 1] = 'R';
                        left--;
                        right++;
                    }
                    right = -1;
                } else {
                    for (int j = i - 1; j >= 0; --j) {
                        if (array[j] == 'L') {
                            break;
                        }
                        array[j] = 'L';
                    }
                }
            } else if (array[i] == 'R') {
                if (right > -1) {
                    for (int j = right + 1; j < i; ++j) {
                        array[j] = 'R';
                    }
                }
                right = i;
            } else {
                if (i == n - 1 && right > -1) {
                    for (int j = right + 1; j < n; ++j) {
                        array[j] = 'R';
                    }
                }
            }
        }

        return new String(array);
    }
}
