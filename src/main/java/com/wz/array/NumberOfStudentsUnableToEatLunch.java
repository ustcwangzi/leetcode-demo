package com.wz.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively.
 * All students stand in a queue. Each student either prefers square or circular sandwiches.
 * The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:
 * If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
 * Otherwise, they will leave it and go to the queue's end.
 * This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
 * You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the ith sandwich in the stack
 * (i = 0 is the top of the stack) and students[j] is the preference of the jth student in the initial queue
 * (j = 0 is the front of the queue). Return the number of students that are unable to eat.
 *
 * Example 1:
 * Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
 * Output: 0
 * Explanation:
 * - Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
 * - Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
 * - Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
 * - Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
 * - Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
 * - Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
 * - Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
 * - Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
 * Hence all students are able to eat.
 *
 * Example 2:
 * Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
 * Output: 3
 *
 * Constraints:
 * 1. 1 <= students.length, sandwiches.length <= 100
 * 2. students.length == sandwiches.length
 * 3. sandwiches[i] is 0 or 1.
 * 4. students[i] is 0 or 1.
 */
public class NumberOfStudentsUnableToEatLunch {
    public static void main(String[] args) {
        System.out.println(countStudents(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}));
        System.out.println(countStudents2(new int[]{1, 1, 0, 0}, new int[]{0, 1, 0, 1}));
        System.out.println(countStudents(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 1, 1}));
        System.out.println(countStudents2(new int[]{1, 1, 1, 0, 0, 1}, new int[]{1, 0, 0, 0, 1, 1}));
    }

    /**
     * 当队首 students 的元素值等于栈顶 sandwiches 的元素值那么表示学生可以拿取三明治
     * 否则学生就需要排到队尾，重复这个过程直到所有的学生都不喜欢这个最顶上的三明治为止
     * 方案一：用队列模拟这个过程，当一次循环结束没人吃到三明治时，停止循环过程
     */
    public static int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new LinkedList<>();
        for (int student : students) {
            queue.offer(student);
        }
        int index = 0;
        while (!queue.isEmpty()) {
            boolean eat = false;
            int size = queue.size();
            // 依次对当前队列中的学生进行尝试
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                // 吃到了三明治
                if (cur == sandwiches[index]) {
                    eat = true;
                    index++;
                } else {
                    queue.offer(cur);
                }
            }
            // 本轮没人吃到三明治，结束
            if (!eat) {
                break;
            }
        }
        return queue.size();
    }

    /**
     * 方案二，直接统计喜欢每种三明治的人数，然后遍历三明治，如果喜欢当前三明治的人数大于 0，则将人数减一，否则直接结束
     */
    public static int countStudents2(int[] students, int[] sandwiches) {
        // 记录两种餐需求的同学数量
        int zero = 0, one = 0;
        for (int student : students) {
            if (student == 0) {
                zero++;
            } else {
                one++;
            }
        }

        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                if (zero > 0) {
                    zero--;
                } else {
                    break;
                }
            } else {
                if (one > 0) {
                    one--;
                } else {
                    break;
                }
            }
        }
        return one + zero;
    }
}
