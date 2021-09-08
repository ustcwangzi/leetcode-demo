package com.wz.other;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * You are given two integer arrays persons and times. In an election, the ith vote was cast for persons[i] at time times[i].
 * For each query at a time t, find the person that was leading the election at time t.
 * Votes cast at time t will count towards our query. In the case of a tie, the most recent vote (among tied candidates) wins.
 * Implement the TopVotedCandidate class:
 * 1. TopVotedCandidate(int[] persons, int[] times) Initializes the object with the persons and times arrays.
 * 2. int q(int t) Returns the number of the person that was leading the election at time t according to the mentioned rules.
 *
 * Example 1:
 * Input
 * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
 * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
 * Output
 * [null, 0, 1, 1, 0, 0, 1]
 * Explanation
 * TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
 * topVotedCandidate.q(3); // return 0, At time 3, the votes are [0], and 0 is leading.
 * topVotedCandidate.q(12); // return 1, At time 12, the votes are [0,1,1], and 1 is leading.
 * topVotedCandidate.q(25); // return 1, At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
 * topVotedCandidate.q(15); // return 0
 * topVotedCandidate.q(24); // return 0
 * topVotedCandidate.q(8); // return 1
 *
 * Constraints:
 * 1. 1 <= persons.length <= 5000
 * 2. times.length == persons.length
 * 3. 0 <= persons[i] < persons.length
 * 4. 0 <= times[i] <= 10^9
 * 5. times is sorted in a strictly increasing order.
 * 6. times[0] <= t <= 10^9
 * 7. At most 104 calls will be made to q.
 */
public class TopVotedCandidate {
    public static void main(String[] args) {
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(
                new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30});
        System.out.println(topVotedCandidate.q(3));
        System.out.println(topVotedCandidate.q(12));
        System.out.println(topVotedCandidate.q(25));
        System.out.println(topVotedCandidate.q(15));
        System.out.println(topVotedCandidate.q(24));
        System.out.println(topVotedCandidate.q(8));
    }

    /**
     * 投票时间 -> leader
     */
    private final TreeMap<Integer, Integer> timeMap = new TreeMap<>();

    /**
     * 在时间点 times[i]，i 这个人把选票投给了 persons[i]，求出任意时间点 t 时得票最多的人
     * 最笨的办法就是每次都从开头统计到当前时间点，找出得票最高者，但会 TLE
     * 正确的方法是要在每个投票时间点统计出当前 leader，查询的时候，查找小于等于查询时间点 t 的投票时间点，返回该时间点的 leader 即可
     * 因此比较适合使用 TreeMap 来保存投票时间点和 leader 之间的映射，另外可以使用 map 来统计每个人的得票数
     * 遍历 persons、times，依次计算每个人的得票数，并更新每个投票时间点的 leader
     */
    public TopVotedCandidate(int[] persons, int[] times) {
        // person -> count
        Map<Integer, Integer> countMap = new HashMap<>();
        int leader = -1, maxCount = 0;
        for (int i = 0; i < persons.length; i++) {
            int person = persons[i], time = times[i];
            int count = countMap.getOrDefault(person, 0) + 1;
            countMap.put(person, count);
            if (count >= maxCount) {
                maxCount = count;
                if (person != leader) {
                    leader = person;
                }
            }
            timeMap.put(time, leader);
        }
    }

    public int q(int t) {
        return timeMap.get(timeMap.floorKey(t));
    }
}
