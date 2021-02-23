package com.wz.greedy;

/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.
 * You begin the journey with an empty tank at one of the gas stations.
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the
 * circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
 *
 * Example 1:
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 *
 * Example 2:
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 * Constraints:
 * 1. gas.length == n
 * 2. cost.length == n
 * 3. 1 <= n <= 10^4
 * 4. 0 <= gas[i], cost[i] <= 10^4
 */
public class GasStation {
    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    /**
     * 贪心
     * 从 0 开始试探，累加剩余油量 rest += gas[i]-cost[i]，一旦在 i 处遇到 rest < 0，那么就说明当前选择的起点不行，
     * 需要重新选择，此时不应该回去使用 start+1 作为新起点，因为在 start 处，一定有 gas >= cost，
     * 说明 start+1 到 i 处的总 gas 一定小于总 cost，选择其中任何一个作为起点还是不行的，所以应该跳过这些点
     * 以 i+1 作为新起点，遍历到 n-1 处就可以结束了，如果找到了可能的起点，还要进行验证，总的剩余油量 total 是否大于等于 0，
     * 其实本质就是：这个起点将路径分为前后两段，前段总的余量为负，即油不够用，要想有解，那么后段油量应该为正，此时才可能有解，
     * 要做的是找到这个分割点作为起点，然后验证；反之，如果前段就为正了，那么可以直接选择前面的点为起点；如果整段加起来都是负的，则无解
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, start = 0, rest = 0, total = 0;
        for (int i = 0; i < n; i++) {
            rest += gas[i] - cost[i];
            // 为了验证整个数组是否 gas > cost
            total += gas[i] - cost[i];
            if (rest < 0) {
                start = i + 1;
                rest = 0;
            }
        }
        return total >= 0 ? start : -1;
    }
}
