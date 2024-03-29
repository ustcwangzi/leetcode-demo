package com.wz.other;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.
 * Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack.
 * The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.
 * You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}".
 * For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3,
 * and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2.
 * Note that a function can be called multiple times, possibly recursively.
 * A function's exclusive time is the sum of execution times for all function calls in the program.
 * For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.
 * Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.
 *
 * Example 1:
 * @link ../../../../resource/ExclusiveTimeOfFunctions.jpg
 * Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 * Output: [3,4]
 * Explanation:
 * Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
 * Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
 * Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
 * So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
 *
 * Example 2:
 * Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
 * Output: [7,1]
 * Explanation:
 * Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
 * Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
 * Function 0 (initial call) resumes execution then immediately calls function 1.
 * Function 1 starts at the beginning of time 6, executes 1 units of time, and ends at the end of time 6.
 * Function 0 resumes execution at the beginning of time 6 and executes for 2 units of time.
 * So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1 spends 1 unit of total time executing.
 *
 * Example 3:
 * Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"]
 * Output: [8,1]
 *
 * Constraints:
 * 1. 1 <= n <= 100
 * 2. 1 <= logs.length <= 500
 * 3. 0 <= function_id < n
 * 4. 0 <= timestamp <= 10^9
 * 5. No two start events will happen at the same timestamp.
 * 6. No two end events will happen at the same timestamp.
 * 7. Each function has an "end" log for each "start" log.
 */
public class ExclusiveTimeOfFunctions {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(exclusiveTime(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6"))));
        System.out.println(Arrays.toString(exclusiveTime(2, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "1:start:7", "1:end:7", "0:end:8"))));
    }

    /**
     * 用栈 stack，遍历每个 log，然后把三部分分开，id,type,time
     * 如果栈为空，则直接将任务加入栈中，同时将 preTime 更新为当前 curTime；如果栈不空，说明之前肯定有任务在跑，需要更新运行时间
     * 如果当前是 start，则将上一个任务运行时间的增加值为 curTime-preTime，同时将 preTime 更新为 curTime，并将当前任务入栈
     * 如果当前是 end，说明上一个任务需要停止了，那么将栈顶元素取出，运行时间增加值为 curTime-preTime+1，同时将 preTime 更新为 curTime+1，代表下一个任务的开始时间
     */
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        int preTime = 0;
        Stack<Integer> stack = new Stack<>();
        for (String log : logs) {
            String[] array = log.split(":");
            int id = Integer.parseInt(array[0]), curTime = Integer.parseInt(array[2]);
            if (stack.isEmpty()) {
                stack.push(id);
                preTime = curTime;
                continue;
            }

            if (array[1].equals("start")) {
                int interval = curTime - preTime;
                result[stack.peek()] += interval;
                stack.push(id);
                preTime = curTime;
            } else {
                int interval = curTime - preTime + 1;
                result[stack.pop()] += interval;
                preTime = curTime + 1;
            }
        }
        return result;
    }
}
