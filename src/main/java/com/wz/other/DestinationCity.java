package com.wz.other;

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
        paths.add(Arrays.asList("London", "New York"));
        paths.add(Arrays.asList("New York", "Lima"));
        paths.add(Arrays.asList("Lima", "Sao Paulo"));
        System.out.println(destCity(paths));
    }

    /**
     * 只有入度没有出度的节点就是终点
     * 因此可以先遍历 paths 使用 set 记录所有的起点，然后再遍历 paths，找到 set 中不存在的目的地
     */
    public static String destCity(List<List<String>> paths) {
        Set<String> source = new HashSet<>();
        for (List<String> path : paths) {
            source.add(path.get(0));
        }

        for (List<String> path : paths) {
            if (!source.contains(path.get(1))) {
                return path.get(1);
            }
        }

        return "";
    }
}
