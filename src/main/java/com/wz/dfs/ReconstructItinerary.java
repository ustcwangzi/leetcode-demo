package com.wz.dfs;

import java.util.*;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight.
 * Reconstruct the itinerary in order and return it.
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 * Example 1:
 * @see ../../../../resource/ReconstructItinerary1.jpg
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Example 2:
 * @see ../../../../resource/ReconstructItinerary2.jpg
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 * Constraints:
 * 1. 1 <= tickets.length <= 300
 * 2. tickets[i].length == 2
 * 3. fromi.length == 3
 * 4. toi.length == 3
 * 5. fromi and toi consist of uppercase English letters.
 * 6. fromi != toi
 */
public class ReconstructItinerary {
    public static void main(String[] args) {
        List<List<String>> tickets = new LinkedList<>();
        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));
        System.out.println(findItinerary(tickets));

        tickets = new LinkedList<>();
        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));
        System.out.println(findItinerary(tickets));
    }

    /**
     * DFS + PriorityQueue，与 {@link CourseSchedule} 类似
     * {@link CourseSchedule} 是关于有向图顶点的遍历，本题是关于有向图边的遍历
     * 现将 tickets 转换为有向图，使用邻接表表示，因为最终结果需要的是字母顺序最小的，因此使用 PriorityQueue
     * 然后对有向图进行 DFS，当一个节点的邻接节点全部遍历完的时候，将该节点存入结果中，然后一层层回溯回去
     * 注意最先加入的是最后一个节点，因此使用 addFirst 进行添加，保证最后结果是正序的
     */
    public static List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> result = new LinkedList<>();
        dfs("JFK", buildGraph(tickets), result);
        return result;
    }

    private static void dfs(String node, Map<String, Queue<String>> graph, LinkedList<String> result) {
        Queue<String> neighbors = graph.get(node);
        while (neighbors != null && !neighbors.isEmpty()) {
            String neighbor = neighbors.poll();
            dfs(neighbor, graph, result);
        }
        result.addFirst(node);
    }

    private static Map<String, Queue<String>> buildGraph(List<List<String>> tickets) {
        Map<String, Queue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            graph.get(ticket.get(0)).add(ticket.get(1));
        }
        return graph;
    }
}
