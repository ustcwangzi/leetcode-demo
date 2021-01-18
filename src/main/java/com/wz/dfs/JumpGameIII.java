package com.wz.dfs;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 *
 * Example 2:
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 *
 *
 * Constraints:
 * 1. 1 <= arr.length <= 5 * 104
 * 2. 0 <= arr[i] < arr.length
 * 3. 0 <= start < arr.length
 */
public class JumpGameIII {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 3, 0, 3, 1, 2};
        System.out.println(canReach(arr, 5));

        arr = new int[]{3, 0, 2, 1, 2};
        System.out.println(canReach(arr, 2));
    }

    /**
     * DFS
     * 如果当前 start 已超过数组范围则直接返回 false，如果 arr[start] == 0，则直接返回 true
     * 否则，对于每个 start，下一个位置可以选择 start - arr[start] 或 start + arr[start]
     * 同时使用 visited 记录当前位置有没有访问过，只对未访问过的节点进行DFS，以免造成死循环
     */
    public static boolean canReach(int[] arr, int start) {
        return dfs(arr, start, new boolean[arr.length]);
    }

    private static boolean dfs(int[] arr, int start, boolean[] visited) {
        if (start < 0 || start >= arr.length) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        if (!visited[start]) {
            visited[start] = true;
            return dfs(arr, start - arr[start], visited) || dfs(arr, start + arr[start], visited);
        }
        return false;
    }
}
