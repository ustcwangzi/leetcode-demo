package com.wz.other;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 * Implement the RecentCounter class:
 * 1. RecentCounter() Initializes the counter with zero recent requests.
 * 2. int ping(int t) Adds a new request at time t, where t represents some time in milliseconds,
 *    and returns the number of requests that has happened in the past 3000 milliseconds (including the new request).
 *    Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
 * 3. It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
 *
 * Example 1:
 * Input
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * Output
 * [null, 1, 2, 3, 3]
 * Explanation
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
 * recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
 *
 * Constraints:
 * 1. 1 <= t <= 10^9
 * 2. Each test case will call ping with strictly increasing values of t.
 * 3. At most 104 calls will be made to ping.
 */
public class RecentCounter {
    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(100));
        System.out.println(recentCounter.ping(3001));
        System.out.println(recentCounter.ping(3002));
    }

    private final Deque<Integer> deque;


    public RecentCounter() {
        deque = new LinkedList<>();
    }

    /**
     * 每次调用 ping() 会传入一个 t，然后计算过去 3000ms 内的 ping 次数
     * 双端队列，每次 ping 将 t 入队，同时判断 t 和 队首元素之差是否超过 3000，超过就删掉，剩下的都是在 3000 内的
     */
    public int ping(int t) {
        deque.offer(t);
        while (!deque.isEmpty() && t - deque.peek() > 3000) {
            deque.poll();
        }
        return deque.size();
    }
}
