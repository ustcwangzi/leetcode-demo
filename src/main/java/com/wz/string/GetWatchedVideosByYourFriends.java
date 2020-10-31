package com.wz.string;

import java.util.*;

/**
 * There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends,
 * where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for the person with id = i.
 * Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of
 * your friends and so on. In general, the level k of videos are all watched videos by people with the
 * shortest path exactly equal to k with you. Given your id and the level of videos, return the list of videos
 * ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.
 *
 * Example 1:
 * Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
 * Output: ["B","C"]
 * Explanation:
 * You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
 * Person with id = 1 -> watchedVideos = ["C"]
 * Person with id = 2 -> watchedVideos = ["B","C"]
 * The frequencies of watchedVideos by your friends are:
 * B -> 1
 * C -> 2
 *
 * Example 2:
 * Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
 * Output: ["D"]
 * Explanation:
 * You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).
 *
 * Constraints:
 * 1. n == watchedVideos.length == friends.length
 * 2. 2 <= n <= 100
 * 3. 1 <= watchedVideos[i].length <= 100
 * 4. 1 <= watchedVideos[i][j].length <= 8
 * 5. 0 <= friends[i].length < n
 * 6. 0 <= friends[i][j] < n
 * 7. 0 <= id < n
 * 8. 1 <= level < n
 * 9. if friends[i] contains j, then friends[j] contains i
 */
public class GetWatchedVideosByYourFriends {
    public static void main(String[] args) {
        List<List<String>> watchedVideos = new LinkedList<>();
        List<String> videos = new LinkedList<>();
        videos.add("A");
        videos.add("B");
        watchedVideos.add(videos);
        videos = new LinkedList<>();
        videos.add("C");
        watchedVideos.add(videos);
        videos = new LinkedList<>();
        videos.add("B");
        videos.add("C");
        watchedVideos.add(videos);
        videos = new LinkedList<>();
        videos.add("D");
        watchedVideos.add(videos);
        int[][] friends = new int[][]{{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        System.out.println(watchedVideosByFriends(watchedVideos, friends, 0, 1));
        System.out.println(watchedVideosByFriends(watchedVideos, friends, 0, 2));
    }

    /**
     * 先用 BFS 求出对应 level 的 friends，然后统计这些 friends 观看的所有 videos，最后排序
     */
    public static List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        visited[id] = true;
        Map<String, Integer> videoCount = new HashMap<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.add(id);
        // run BFS to find the friends at the given level
        for (int i = 0; i < level && !queue.isEmpty(); i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                for (int friend : friends[queue.pollFirst()]) {
                    if (!visited[friend]) {
                        visited[friend] = true;
                        queue.addLast(friend);
                    }
                }
            }
        }

        // count the frequencies of each video
        while (!queue.isEmpty()) {
            for (String video : watchedVideos.get(queue.pollFirst())) {
                videoCount.compute(video, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }

        //use TreeMap to keep the order of count and TreeSet to keep the order of videos
        Map<Integer, TreeSet<String>> orderMap = new TreeMap<>();
        for (String video : videoCount.keySet()) {
            int count = videoCount.get(video);
            orderMap.putIfAbsent(count, new TreeSet<>());
            orderMap.get(count).add(video);
        }

        List<String> result = new LinkedList<>();
        for (int c : orderMap.keySet()) {
            result.addAll(orderMap.get(c));
        }

        return result;
    }
}
