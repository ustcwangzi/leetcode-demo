package com.wz.greedy;

import java.util.Arrays;

/**
 * You are given an array people where people[i] is the weight of the ith person,
 * and an infinite number of boats where each boat can carry a maximum weight of limit.
 * Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
 * Return the minimum number of boats to carry every given person.
 *
 * Example 1:
 * Input: people = [1,2], limit = 3
 * Output: 1
 * Explanation: 1 boat (1, 2)
 *
 * Example 2:
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 *
 * Constraints:
 * 1. 1 <= people.length <= 5 * 10^4
 * 2. 1 <= people[i] <= limit <= 3 * 10^4
 */
public class BoatsToSavePeople {
    public static void main(String[] args) {
        System.out.println(numRescueBoats(new int[]{1, 2}, 3));
        System.out.println(numRescueBoats(new int[]{3, 2, 2, 1}, 3));
    }

    /**
     * 对数组进行排序，使用 left、right 分别指向数组的开始、结束，即当前最轻和最重的人
     * 当 left<= right 时进行循环，由于每个船最多乘坐两人，所以先将重的人上船，如果还可以上一个当前最轻的，则最轻的也上船
     */
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.parallelSort(people);

        int left = 0, right = people.length - 1, result = 0;
        while (left <= right) {
            // 轻的需要判断能否上船
            if (people[left] + people[right] <= limit) {
                left++;
            }
            // 重的一定上船
            right--;
            result++;
        }
        return result;
    }
}
