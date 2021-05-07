package com.wz.heap;

import java.util.PriorityQueue;

/**
 * There is a school that has classes of students and each class will be having a final exam.
 * You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class,
 * there are totali total students, but only passi number of students will pass the exam.
 * You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed
 * to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class
 * in a way that maximizes the average pass ratio across all the classes.
 * The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the
 * total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.
 * Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 * Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * Output: 0.78333
 * Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
 *
 * Example 2:
 * Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
 * Output: 0.53485
 *
 * Constraints:
 * 1. 1 <= classes.length <= 10^5
 * 2. classes[i].length == 2
 * 3. 1 <= passi <= totali <= 10^5
 * 4. 1 <= extraStudents <= 10^5
 */
public class MaximumAveragePassRatio {
    public static void main(String[] args) {
        System.out.println(maxAverageRatio(new int[][]{{1, 2}, {3, 5}, {2, 2}}, 2));
        System.out.println(maxAverageRatio(new int[][]{{2, 4}, {3, 9}, {4, 5}, {2, 10}}, 4));
    }

    /**
     * 大根堆，优先向 添加一名学生后通过率增加值最大的班级 中添加学生，因此堆中元素需要按照 添加一名学生后通过率增加值 进行降序排列
     * 然后依次取出堆顶元素添加一名学生再放入堆中，直到用完全部学生，最后计算堆中所有班级的通过率
     */
    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        // 按照添加学生后的通过率增加值降序排列
        PriorityQueue<Students> queue = new PriorityQueue<>(n, (o1, o2) -> Double.compare(o2.delta, o1.delta));

        for (int[] array : classes) {
            int pass = array[0], total = array[1];
            // 添加一名学生后的通过率增加值
            double delta = ((double) (pass + 1)) / (total + 1) - ((double) pass / total);
            queue.offer(new Students(delta, pass, total));
        }

        for (int i = 0; i < extraStudents; i++) {
            Students cur = queue.poll();
            // 向当前班级添加一名学生
            int pass = cur.pass + 1, total = cur.total + 1;
            // 添加一名学生后的通过率增加值
            double delta = ((double) (pass + 1)) / (total + 1) - ((double) pass / total);
            queue.offer(new Students(delta, pass, total));
        }

        double totalRation = 0;
        while (!queue.isEmpty()) {
            Students cur = queue.poll();
            int pass = cur.pass, total = cur.total;
            double ratio = ((double) pass / total);
            totalRation += ratio;
        }
        return totalRation / n;
    }

    private static class Students {
        // 通过率增加值
        public double delta;
        public int pass;
        public int total;

        public Students(double delta, int pass, int total) {
            this.delta = delta;
            this.pass = pass;
            this.total = total;
        }
    }
}
