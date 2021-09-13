package com.wz.other;

/**
 * In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
 * If the town judge exists, then:
 * 1. The town judge trusts nobody.
 * 2. Everybody (except for the town judge) trusts the town judge.
 * 3. There is exactly one person that satisfies properties 1 and 2.
 * You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
 * Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
 *
 * Example 1:
 * Input: n = 2, trust = [[1,2]]
 * Output: 2
 *
 * Example 2:
 * Input: n = 3, trust = [[1,3],[2,3]]
 * Output: 3
 *
 * Example 3:
 * Input: n = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 *
 * Constraints:
 * 1. 1 <= n <= 1000
 * 2. 0 <= trust.length <= 104
 * 3. trust[i].length == 2
 * 4. All the pairs of trust are unique.
 * 5. ai != bi
 * 6. 1 <= ai, bi <= n
 */
public class FindTheTownJudge {
    public static void main(String[] args) {
        System.out.println(findJudge(3, new int[][]{{1, 3}, {2, 3}}));
        System.out.println(findJudge(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}));
    }

    /**
     * 小镇有 n 个人，其中有一个法官，法官不相信任何人，而其他所有人都信任法官，找出这个法官，不存在的话返回 -1
     * 由于信任是有方向的，所以是一个有向图，因为法官不相信任何人，所以其没有出度，而所有人都信任他，则入度为 n-1
     * 遍历 trust，使用数组统计每个节点的出度和入度，然后找出出度为 0、入度为 n-1 的节点即可
     */
    public static int findJudge(int n, int[][] trust) {
        int[] inDegree = new int[n + 1], outDegree = new int[n + 1];
        for (int[] cur : trust) {
            outDegree[cur[0]]++;
            inDegree[cur[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == n - 1 && outDegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
