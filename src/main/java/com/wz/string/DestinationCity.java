package com.wz.string;

import java.util.*;

/**
 * You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi.
 * Return the destination city, that is, the city without any path outgoing to another city.
 * It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.
 *
 * Example 1:
 * Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * Output: "Sao Paulo"
 * Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
 *
 * Example 2:
 * Input: paths = [["B","C"],["D","B"],["C","A"]]
 * Output: "A"
 * Explanation: All possible trips are:
 * "D" -> "B" -> "C" -> "A".
 * "B" -> "C" -> "A".
 * "C" -> "A".
 * "A".
 * Clearly the destination city is "A".
 *
 * Constraints:
 * 1. 1 <= paths.length <= 100
 * 2. paths[i].length == 2
 * 3. 1 <= cityAi.length, cityBi.length <= 10
 * 4. cityAi != cityBi
 * 5. All strings consist of lowercase and uppercase English letters and the space character.
 */
public class DestinationCity {
    public static void main(String[] args) {
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.asList("B", "C"));
        paths.add(Arrays.asList("D", "B"));
        paths.add(Arrays.asList("C", "A"));
        System.out.println(destCity(paths));
    }

    /**
     * 对所有的站进行统计，如果一个站没有出现在出发点上，那这个站就是终点站
     */
    public static String destCity(List<List<String>> paths) {
        Set<String> start = new HashSet<>();
        for (List<String> list : paths) {
            start.add(list.get(0));
        }

        String destination = "";
        for (List<String> list : paths) {
            if (!start.contains(list.get(1))) {
                destination = list.get(1);
            }
        }
        return destination;
    }
}
