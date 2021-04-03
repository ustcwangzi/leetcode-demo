package com.wz.sort;

import java.util.PriorityQueue;

/**
 * N cars are going to the same destination along a one lane road.  The destination is target miles away.
 * Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
 * A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
 * The distance between these two cars is ignored - they are assumed to have the same position.
 * A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 * How many car fleets will arrive at the destination?
 *
 * Example 1:
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * Output: 3
 * Explanation:
 * The cars starting at 10 and 8 become a fleet, meeting each other at 12.
 * The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 and 3 become a fleet, meeting each other at 6.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 *
 * Note:
 * 1. 0 <= N <= 10 ^ 4
 * 2. 0 < target <= 10 ^ 6
 * 3. 0 < speed[i] <= 10 ^ 6
 * 4. 0 <= position[i] < target
 * 5. All initial positions are different.
 */
public class CarFleet {
    public static void main(String[] args) {
        int[] position = new int[]{10, 8, 0, 5, 3}, speed = new int[]{2, 4, 1, 1, 3};
        System.out.println(carFleet(12, position, speed));
    }

    /**
     * 路上有一系列的车，车在不同的位置，且分别有着不同的速度，但行驶的方向都相同，如果后方的车在到达终点之前追上前面的车了，
     * 那么它就会尾随在其后，且速度降至和前面的车相同，可以看作是一个车队，当然，单独的一辆车也可以看作是一个车队，问共有多少个车队到达终点
     *
     * 其实并不需要知道车的相遇位置，只关心是否能组成车队一同到达终点，最简单的方法就是看时间，
     * 假如车B在车A的后面，而车B到终点的时间小于等于车A，那么就知道车A和B一定会组成车队一起到达终点
     * 因此，可以从离终点最近的一辆车开始，依次计算出每辆车到达终点的时间，若后面的车到达时间小于等于前面的车，则会组成车队。
     * 反之，说明无法追上前面的车，于是自己会形成一个新的车队，且是车头，则 result++
     */
    public static int carFleet(int target, int[] position, int[] speed) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < position.length; i++) {
            queue.offer(new int[]{position[i], speed[i]});
        }

        int result = 0;
        // 记录前一个车到达终点需要的时间
        double preTime = 0;
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            double curTime = (double) (target - pair[0]) / pair[1];
            // 当前所需时间较小，会追上前面的车，即会和前面的车组成车队
            if (curTime <= preTime) {
                continue;
            }
            // 无法组成车队，形成一个新车队
            preTime = curTime;
            result++;
        }

        return result;
    }
}
