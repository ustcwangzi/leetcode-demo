package com.wz.other;

import java.util.*;

/**
 * You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.
 * Return a list answer of size 2 where:
 * - answer[0] is a list of all players that have not lost any matches.
 * - answer[1] is a list of all players that have lost exactly one match.
 * The values in the two lists should be returned in increasing order.
 * Note:
 * You should only consider the players that have played at least one match.
 * The testcases will be generated such that no two matches will have the same outcome.
 *
 * Example 1:
 * Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
 * Output: [[1,2,10],[4,5,7,8]]
 * Explanation:
 * Players 1, 2, and 10 have not lost any matches.
 * Players 4, 5, 7, and 8 each have lost one match.
 * Players 3, 6, and 9 each have lost two matches.
 * Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
 *
 * Example 2:
 * Input: matches = [[2,3],[1,3],[5,4],[6,4]]
 * Output: [[1,2,5,6],[]]
 * Explanation:
 * Players 1, 2, 5, and 6 have not lost any matches.
 * Players 3 and 4 each have lost two matches.
 * Thus, answer[0] = [1,2,5,6] and answer[1] = [].
 *
 * Constraints:
 * 1. 1 <= matches.length <= 10^5
 * 2. matches[i].length == 2
 * 3. 1 <= winneri, loseri <= 10^5
 * 4. winneri != loseri
 * 5. All matches[i] are unique.
 */
public class FindPlayersWithZeroOrOneLosses {
    public static void main(String[] args) {
        System.out.println(findWinners(new int[][]{{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {4, 9}, {10, 4}, {10, 9}}));
        System.out.println(findWinners(new int[][]{{2, 3}, {1, 3}, {5, 4}, {6, 4}}));
    }

    /**
     * 使用 map 统计每个 player 输的次数，因为要求最终结果有序，这次使用 TreeMap，遍历数组
     * match[0] 是获胜方，本次没输，但有可能之前输过，因此从 map 中获取之前的次数即可
     * match[1] 是失败方，本次又输了，需要将失败次数加一
     * 遍历 map，将失败次数等于 0 和 等于 1 的分别加入对应的结果集中即可
     */
    public static List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] match : matches) {
            map.put(match[0], map.getOrDefault(match[0], 0));
            map.put(match[1], map.getOrDefault(match[1], 0) + 1);
        }

        List<Integer> noLost = new LinkedList<>(), oneLost = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                noLost.add(entry.getKey());
            }
            if (entry.getValue() == 1) {
                oneLost.add(entry.getKey());
            }
        }

        List<List<Integer>> result = new ArrayList<>(2);
        result.add(noLost);
        result.add(oneLost);
        return result;
    }
}
