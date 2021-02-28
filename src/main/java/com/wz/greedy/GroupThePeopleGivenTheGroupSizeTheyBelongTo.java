package com.wz.greedy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * There are n people that are split into some unknown number of groups. Each person is labeled with a unique ID from 0 to n - 1.
 * You are given an integer array groupSizes, where groupSizes[i] is the size of the group that person i is in.
 * For example, if groupSizes[1] = 3, then person 1 must be in a group of size 3.
 * Return a list of groups such that each person i is in a group of size groupSizes[i].
 * Each person should appear in exactly one group, and every person must be in a group. If there are multiple answers,
 * return any of them. It is guaranteed that there will be at least one valid solution for the given input.
 *
 * Example 1:
 * Input: groupSizes = [3,3,3,3,3,1,3]
 * Output: [[5],[0,1,2],[3,4,6]]
 * Explanation:
 * The first group is [5]. The size is 1, and groupSizes[5] = 1.
 * The second group is [0,1,2]. The size is 3, and groupSizes[0] = groupSizes[1] = groupSizes[2] = 3.
 * The third group is [3,4,6]. The size is 3, and groupSizes[3] = groupSizes[4] = groupSizes[6] = 3.
 * Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
 *
 * Constraints:
 * 1. groupSizes.length == n
 * 2. 1 <= n <= 500
 * 3. 1 <= groupSizes[i] <= n
 */
public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {
    public static void main(String[] args) {
        System.out.println(groupThePeople(new int[]{3, 3, 3, 3, 3, 1, 3}));
    }

    /**
     * 使用 map 记录每个 groupSize 下的元素集合，如果元素个数已达到指定个数，则将当前元素集合放入结果集中
     */
    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int curSize = groupSizes[i];
            // 记录每个 groupSize 下的元素
            map.putIfAbsent(curSize, new LinkedList<>());
            List<Integer> curGroup = map.get(curSize);
            curGroup.add(i);

            // 达到指定 size
            if (curGroup.size() == curSize) {
                result.add(curGroup);
                map.remove(curSize);
            }
        }

        return result;
    }
}
