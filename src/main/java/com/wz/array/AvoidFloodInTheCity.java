package com.wz.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water. If it rains over a lake which is full of water, there will be a flood. Your goal is to avoid the flood in any lake.
 * Given an integer array rains where:
 * rains[i] > 0 means there will be rains over the rains[i] lake.
 * rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
 * Return an array ans where:
 * ans.length == rains.length
 * ans[i] == -1 if rains[i] > 0.
 * ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
 * If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.
 * Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes. (see example 4)
 *
 * Example 1:
 * Input: rains = [1,2,3,4]
 * Output: [-1,-1,-1,-1]
 * Explanation: After the first day full lakes are [1]
 * After the second day full lakes are [1,2]
 * After the third day full lakes are [1,2,3]
 * After the fourth day full lakes are [1,2,3,4]
 * There's no day to dry any lake and there is no flood in any lake.
 *
 * Example 2:
 * Input: rains = [1,2,0,0,2,1]
 * Output: [-1,-1,2,1,-1,-1]
 * Explanation: After the first day full lakes are [1]
 * After the second day full lakes are [1,2]
 * After the third day, we dry lake 2. Full lakes are [1]
 * After the fourth day, we dry lake 1. There is no full lakes.
 * After the fifth day, full lakes are [2].
 * After the sixth day, full lakes are [1,2].
 * It is easy that this scenario is flood-free. [-1,-1,1,2,-1,-1] is another acceptable scenario.
 *
 * Example 3:
 * Input: rains = [1,2,0,1,2]
 * Output: []
 * Explanation: After the second day, full lakes are  [1,2]. We have to dry one lake in the third day.
 * After that, it will rain over lakes [1,2]. It's easy to prove that no matter which lake you choose to dry in the 3rd day, the other one will flood.
 *
 * Example 4:
 * Input: rains = [69,0,0,0,69]
 * Output: [-1,69,1,1,-1]
 * Explanation: Any solution on one of the forms [-1,69,x,y,-1], [-1,x,69,y,-1] or [-1,x,y,69,-1] is acceptable where 1 <= x,y <= 10^9
 */
public class AvoidFloodInTheCity {
    public static void main(String[] args) {
        int[] rains = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(avoidFlood(rains)));

        rains = new int[]{1, 2, 0, 0, 2, 1};
        System.out.println(Arrays.toString(avoidFlood(rains)));

        rains = new int[]{1, 2, 0, 1, 2};
        System.out.println(Arrays.toString(avoidFlood(rains)));

        rains = new int[]{69, 0, 0, 0, 69};
        System.out.println(Arrays.toString(avoidFlood(rains)));
    }

    /**
     * 贪心
     * 在遍历的过程中用 TreeSet 记录可以抽干的时刻，同时用 HashMap 记录湖泊最后一次装水的时刻
     * 遍历中，如果遇到一个湖泊即将溢出，则在 HashMap 中寻找到上一次该湖泊装满水的时刻，接着在 TreeSet 寻找该时刻之后最小的可以抽干的时刻
     * 在那个时刻抽干这个湖泊的水，然后从有序集合中删除掉这个时刻；若不存在一个可以抽干的时刻，返回空数组
     */
    public static int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] result = new int[n];
        // 记录上一次装水的时刻，key:湖泊，value:时刻
        Map<Integer, Integer> map = new HashMap<>();
        // 记录可以排水的时刻
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                set.add(i);
                continue;
            }

            if (map.containsKey(rains[i])) {
                // 寻找上次装满水之后的可以排水的最小时刻
                Integer day = set.higher(map.get(rains[i]));
                if (day == null) {
                    // 无法排水
                    return new int[0];
                }
                // 排水
                result[day] = rains[i];
                set.remove(day);
            }
            map.put(rains[i], i);
            result[i] = -1;
        }

        for (int i : set) {
            result[i] = 1;
        }
        return result;
    }
}
