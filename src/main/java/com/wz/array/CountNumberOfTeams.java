package com.wz.array;

/**
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 * You have to form a team of 3 soldiers amongst them under the following rules:
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 *
 * Example 1:
 * Input: rating = [2,5,3,4,1]
 * Output: 3
 * Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
 *
 * Example 2:
 * Input: rating = [2,1,3]
 * Output: 0
 * Explanation: We can't form any team given the conditions.
 *
 * Example 3:
 * Input: rating = [1,2,3,4]
 * Output: 4
 */
public class CountNumberOfTeams {
    public static void main(String[] args) {
        int[] rating = new int[]{2, 5, 3, 4, 1};
        System.out.println(numTeams(rating));

        rating = new int[]{2, 1, 3};
        System.out.println(numTeams(rating));

        rating = new int[]{1, 2, 3, 4};
        System.out.println(numTeams(rating));
    }

    /**
     * 枚举中间的位置 i，
     * 1. 位置 i 左边小于 rating[i] 的元素个数 leftMin
     * 2. 位置 i 左边大于 rating[i] 的元素个数 leftMax
     * 3. 位置 i 右边小于 rating[i] 的元素个数 rightMin
     * 4. 位置 i 右边大于 rating[i] 的元素个数 rightMax
     * 则 leftMin * rightMax + leftMax * rightMin 就是中间位置 i 所贡献的次数，累加到 result
     */
    public static int numTeams(int[] rating) {
        int result = 0;
        for (int i = 0; i < rating.length; i++) {
            int leftMin = 0, leftMax = 0, rightMin = 0, rightMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (rating[j] < rating[i]) {
                    leftMin++;
                } else {
                    leftMax++;
                }
            }
            for (int j = i + 1; j < rating.length; j++) {
                if (rating[j] < rating[i]) {
                    rightMin++;
                } else {
                    rightMax++;
                }
            }
            result += leftMin * rightMax + leftMax * rightMin;
        }
        return result;
    }
}
