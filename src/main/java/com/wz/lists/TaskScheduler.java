package com.wz.lists;

/**
 * Given a char array representing tasks CPU need to do.
 * It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.
 * However, there is a non-negative cooling interval n that means between two same tasks,
 * there must be at least n intervals that CPU are doing different tasks or just be idle.
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * Example:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 */
public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(leastInterval(tasks, 2));
    }

    /**
     * 两个相同任务之间至少隔n个时间点，那么首先应该处理的出现次数最多的那个任务，先确定好这些高频任务，然后再来安排那些低频任务
     * 例如 AAAABBBEEFFGG 3
     * 任务A出现了4次，频率最高，于是在每个A中间加入三个空位，如下：
     * A---A---A---A
     * AB--AB--AB--A   (加入B)
     * ABE-ABE-AB--A   (加入E)
     * ABEFABE-ABF-A   (加入F，每次尽可能填满或者是均匀填充)
     * ABEFABEGABFGA   (加入G)
     *
     * 例如 ACCCEEE 2
     * 任务C和E都出现了三次，那么就将CE看作一个整体，在中间加入一个位置即可：
     * CE-CE-CE
     * CEACE-CE   (加入A)
     *
     * 观察上面两个例子可以发现，都分成了(max - 1)块，再加上最后面的字母，其中max为最大出现次数。
     * 比如例1中，A出现了4次，有A---模块出现3次，再加上最后的A，每个模块长度为4。例2中，CE-出现了2次，再加上最后的CE，每个模块长度为3
     *
     * 即： 模块的次数为任务最大次数减1，模块的长度为n+1，最后加上的字母个数为出现次数最多的任务
     */
    public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        // 任务的最大出现次数，最大出现次数的任务个数
        int maxFreq = 0, maxFreqCount = 0;
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                maxFreqCount = 1;
            } else if (freq[i] == maxFreq) {
                maxFreqCount++;
            }
        }

        // 模块次数 * 模块长度 + 出现次数最多的任务个数
        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + maxFreqCount);
    }
}
