package com.wz.twopointer;

/**
 * A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false.
 * He wants to confuse the students by maximizing the number of consecutive questions with the same answer (multiple trues or multiple falses in a row).
 * You are given a string answerKey, where answerKey[i] is the original answer to the ith question.
 * In addition, you are given an integer k, the maximum number of times you may perform the following operation:
 * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
 * Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.
 *
 * Example 1:
 * Input: answerKey = "TTFF", k = 2
 * Output: 4
 * Explanation: We can replace both the 'F's with 'T's to make answerKey = "TTTT".
 * There are four consecutive 'T's.
 *
 * Example 2:
 * Input: answerKey = "TFFT", k = 1
 * Output: 3
 * Explanation: We can replace the first 'T' with an 'F' to make answerKey = "FFFT".
 * Alternatively, we can replace the second 'T' with an 'F' to make answerKey = "TFFF".
 * In both cases, there are three consecutive 'F's.
 *
 * Example 3:
 * Input: answerKey = "TTFTTFTT", k = 1
 * Output: 5
 * Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
 * Alternatively, we can replace the second 'F' to make answerKey = "TTFTTTTT".
 * In both cases, there are five consecutive 'T's.
 *
 * Constraints:
 * 1. n == answerKey.length
 * 2. 1 <= n <= 5 * 10^4
 * 3. answerKey[i] is either 'T' or 'F'
 * 4. 1 <= k <= n
 */
public class MaximizeTheConfusionOfAnExam {
    public static void main(String[] args) {
        System.out.println(maxConsecutiveAnswers("TTFF", 2));
        System.out.println(maxConsecutiveAnswers("TTFTTFTT", 1));
    }

    public static int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(findMax(answerKey, k, 'T'), findMax(answerKey, k, 'F'));
    }

    /**
     * 滑动窗口，初始时 left、right 都指向开始位置，然后 right 逐步右移，每次右移时判断当前字符和目标是否相等，若不等，则 k--
     * 当 k 小于 0 时，说明 [left,right] 区间内需要变更的字符太多，k 不够，此时需要 left 右移来增加 k
     * 然后重新计算当前窗口大小，并更新最大窗口大小，最后返回最大窗口长度
     */
    private static int findMax(String answerKey, int k, char target) {
        int left = 0, max = 0;
        for (int right = 0; right < answerKey.length(); right++) {
            if (answerKey.charAt(right) != target) {
                k--;
            }

            while (k < 0) {
                if (answerKey.charAt(left) != target) {
                    k++;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
