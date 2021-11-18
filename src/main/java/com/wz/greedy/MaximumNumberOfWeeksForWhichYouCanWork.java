package com.wz.greedy;

/**
 * There are n projects numbered from 0 to n - 1. You are given an integer array milestones where each milestones[i] denotes the number of milestones the ith project has.
 * You can work on the projects following these two rules:
 * 1. Every week, you will finish exactly one milestone of one project. You must work every week.
 * 2. You cannot work on two milestones from the same project for two consecutive weeks.
 * Once all the milestones of all the projects are finished, or if the only milestones that you can work on will cause you to violate the above rules, you will stop working.
 * Note that you may not be able to finish every project's milestones due to these constraints.
 * Return the maximum number of weeks you would be able to work on the projects without violating the rules mentioned above.
 *
 * Example 1:
 * Input: milestones = [1,2,3]
 * Output: 6
 * Explanation: One possible scenario is:
 * - During the 1st week, you will work on a milestone of project 0.
 * - During the 2nd week, you will work on a milestone of project 2.
 * - During the 3rd week, you will work on a milestone of project 1.
 * - During the 4th week, you will work on a milestone of project 2.
 * - During the 5th week, you will work on a milestone of project 1.
 * - During the 6th week, you will work on a milestone of project 2.
 * The total number of weeks is 6.
 *
 * Example 2:
 * Input: milestones = [5,2,1]
 * Output: 7
 * Explanation: One possible scenario is:
 * - During the 1st week, you will work on a milestone of project 0.
 * - During the 2nd week, you will work on a milestone of project 1.
 * - During the 3rd week, you will work on a milestone of project 0.
 * - During the 4th week, you will work on a milestone of project 1.
 * - During the 5th week, you will work on a milestone of project 0.
 * - During the 6th week, you will work on a milestone of project 2.
 * - During the 7th week, you will work on a milestone of project 0.
 * The total number of weeks is 7.
 * Note that you cannot work on the last milestone of project 0 on 8th week because it would violate the rules.
 * Thus, one milestone in project 0 will remain unfinished.
 *
 * Constraints:
 * 1. n == milestones.length
 * 2. 1 <= n <= 10^5
 * 3. 1 <= milestones[i] <= 10^9
 */
public class MaximumNumberOfWeeksForWhichYouCanWork {
    public static void main(String[] args) {
        System.out.println(numberOfWeeks(new int[]{1, 2, 3}));
        System.out.println(numberOfWeeks(new int[]{5, 2, 1}));
    }

    /**
     * 因为可任意安排各个项目的参与顺序，所以当且仅当其中某一项目的阶段任务数量 max 超过总任务量 sum 的一半时，无法完成全部任务
     * 根据这一特点，可直接结合贪心算法求解：
     * 1. 若不存在超过任务总量一半的项目，即 max * 2 <= sum，所有项目都可被完成，最长工作时间为 sum
     * 2. 若存在超过任务总量一半的项目，即 max * 2 > sum，该项目无法被完成，最长工作时间为剩余项目总时间 * 2 + 1，即 (sum - max) * 2 + 1
     */
    public static long numberOfWeeks(int[] milestones) {
        long sum = 0, max = 0;
        for (int milestone : milestones) {
            sum += milestone;
            max = Math.max(max, milestone);
        }
        return max * 2 > sum ? (sum - max) * 2 + 1 : sum;
    }
}
