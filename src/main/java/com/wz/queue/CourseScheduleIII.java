package com.wz.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi]
 * indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 * Return the maximum number of courses that you can take.
 *
 * Example 1:
 * Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
 * Output: 3
 * Explanation:
 * There are totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 *
 * Example 2:
 * Input: courses = [[1,2]]
 * Output: 1
 *
 * Example 3:
 * Input: courses = [[3,2],[4,3]]
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= courses.length <= 10^4
 * 2. 1 <= durationi, lastDayi <= 10^4
 */
public class CourseScheduleIII {
    public static void main(String[] args) {
        System.out.println(scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}));
        System.out.println(scheduleCourse(new int[][]{{1, 2}}));
        System.out.println(scheduleCourse(new int[][]{{3, 2}, {4, 3}}));
    }

    /**
     * 贪心 + 优先级队列
     * 对课程按照 lastDay 进行排序，使用 curTime 记录当前时间，然后遍历排序后的课程，先完成 lastDay 较小的
     * 那么当前时间 curTime 更新为 curTime + duration，同时将 duration 加入队列中，表示完成该课程
     * 如果此时 curTime > lastDay，说明有可能完成不了，为了完成更多的课程，从队列中去除 duration 最大的那个
     */
    public static int scheduleCourse(int[][] courses) {
        Arrays.parallelSort(courses, Comparator.comparingInt(o -> o[1]));
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int curTime = 0;
        // 优先完成结束时间早的
        for (int[] course : courses) {
            int duration = course[0], lastDay = course[1];
            curTime += duration;
            queue.offer(duration);
            // 为了完成更多的课程，移除耗时最大的
            if (curTime > lastDay) {
                curTime -= queue.poll();
            }
        }
        return queue.size();
    }
}
