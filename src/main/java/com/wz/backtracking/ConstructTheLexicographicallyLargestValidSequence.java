package com.wz.backtracking;

import java.util.Arrays;

/**
 * Given an integer n, find a sequence that satisfies all of the following:
 * 1. The integer 1 occurs once in the sequence.
 * 2. Each integer between 2 and n occurs twice in the sequence.
 * 3. For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
 * The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.
 * Return the lexicographically largest sequence. It is guaranteed that under the given constraints, there is always a solution.
 * A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position where a and b differ,
 * sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0] is lexicographically
 * larger than [0,1,5,6] because the first position they differ is at the third number, and 9 is greater than 5.
 *
 * Example 1:
 * Input: n = 3
 * Output: [3,1,2,3,2]
 * Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.
 *
 * Constraints:
 * 1 <= n <= 20
 */
public class ConstructTheLexicographicallyLargestValidSequence {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructDistancedSequence(3)));
    }

    public static int[] constructDistancedSequence(int n) {
        boolean[] visited = new boolean[n + 1];
        int[] result = new int[n * 2 - 1];
        dfs(result, 0, visited);
        return result;
    }

    private static boolean dfs(int[] result, int index, boolean[] visited) {
        if (index == result.length) {
            return true;
        }
        if (result[index] != 0) {
            return dfs(result, index + 1, visited);
        }

        for (int i = visited.length - 1; i >= 1; i--) {
            if (visited[i]) {
                continue;
            }
            if (i == 1 || (index + i < result.length && result[index + i] == 0)) {
                result[index] = i;
                if (i > 1) {
                    result[index + i] = i;
                }
                visited[i] = true;
                if (dfs(result, index + 1, visited)) {
                    return true;
                }
                result[index] = 0;
                if (i != 1) {
                    result[index + i] = 0;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
