package com.wz.math;

import java.util.Arrays;

/**
 * We distribute some number of candies, to a row of n = num_people people in the following way:
 * We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to the last person.
 * Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second person,
 * and so on until we give 2 * n candies to the last person.
 * This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach the end)
 * until we run out of candies.  The last person will receive all of our remaining candies (not necessarily one more than the previous gift).
 * Return an array (of length num_people and sum candies) that represents the final distribution of candies.
 *
 * Example 1:
 * Input: candies = 7, num_people = 4
 * Output: [1,2,3,1]
 * Explanation:
 * On the first turn, ans[0] += 1, and the array is [1,0,0,0].
 * On the second turn, ans[1] += 2, and the array is [1,2,0,0].
 * On the third turn, ans[2] += 3, and the array is [1,2,3,0].
 * On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1].
 *
 * Example 2:
 * Input: candies = 10, num_people = 3
 * Output: [5,2,3]
 * Explanation:
 * On the first turn, ans[0] += 1, and the array is [1,0,0].
 * On the second turn, ans[1] += 2, and the array is [1,2,0].
 * On the third turn, ans[2] += 3, and the array is [1,2,3].
 * On the fourth turn, ans[0] += 4, and the final array is [5,2,3].
 */
public class DistributeCandiesToPeople {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(distributeCandies(7, 4)));
        System.out.println(Arrays.toString(distributeCandies(10, 3)));
    }

    /**
     * 结果数组的索引是从0开始的，代表第一个人，那他被分配的糖果数量是索引值加1，判断candies是不是比当前需要分配出去的糖果大，取两者的较小值。
     * 如果candies剩余的数量比当前需要分配出去的糖果数量大，就可以继续分配；如果candies剩余的数量比当前需要分配出去的糖果数量小，
     * 说明当前这次分配时最后一次分配，只能将剩余的糖果数量全部分给当前此人了
     */
    public static int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        for (int i = 0; candies > 0; i++) {
            result[i % num_people] += Math.min(candies, i + 1);
            candies -= i + 1;
        }
        return result;
    }
}
