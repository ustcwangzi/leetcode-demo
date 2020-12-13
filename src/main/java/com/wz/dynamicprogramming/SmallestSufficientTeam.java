package com.wz.dynamicprogramming;

import java.util.*;

/**
 * In a project, you have a list of required skills req_skills, and a list of people.
 * The i-th person people[i] contains a list of skills that person has.
 * Consider a sufficient team: a set of people such that for every required skill in req_skills,
 * there is at least one person in the team who has that skill.  We can represent these teams by the index of each person:
 * for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 * Return any sufficient team of the smallest possible size, represented by the index of each person.
 * You may return the answer in any order.  It is guaranteed an answer exists.
 *
 * Example 1:
 * Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
 * Output: [0,2]
 * Example 2:
 * Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],
 * ["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * Output: [1,2]
 *
 * Constraints:
 * 1. 1 <= req_skills.length <= 16
 * 2. 1 <= people.length <= 60
 * 3. 1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
 * 4. Elements of req_skills and people[i] are (respectively) distinct.
 * 5. req_skills[i][j], people[i][j][k] are lowercase English letters.
 * 6. Every skill in people[i] is a skill in req_skills.
 * 7. It is guaranteed a sufficient team exists.
 */
public class SmallestSufficientTeam {
    public static void main(String[] args) {
        String[] req_skills = new String[]{"java", "nodejs", "reactjs"};
        List<List<String>> people = new LinkedList<>();
        people.add(Collections.singletonList("java"));
        people.add(Collections.singletonList("nodejs"));
        people.add(Arrays.asList("nodejs", "reactjs"));
        System.out.println(Arrays.toString(smallestSufficientTeam(req_skills, people)));
    }

    /**
     * 1、二进制表示技能：题目的技能数目<=16，完全可以用1，表示第一种技能，10表示第二种技能，100表示第三种技能，以此类推。
     * 进而可以用一个数字来表示当前拥有的技能总数，像5=4+1，就是拥有了技能1和技能3。1<<n -1就是表示拥有了所有的技能。
     * 2、动态规划：遍历每个人的技能，根据每个人的技能去更新dp的值。
     * 状态：dp[state] 表示掌握技能的状态为 state 时，需要的最少人数。根据上面的举例：dp[0] =1 ; dp[1] = 1; dp[3] = 2;
     * list[state] 表示这个状态下 人员的编号
     * 转移方程： dp[nextState] = min{dp[nextState], dp[preState | p[i]] + 1} ;
     * (p[i] 是第i个人掌握技能的状态，preState | p[i] == dp[nextState] ) ;
     * 结果要覆盖所有的技能，那么要求的状态就是 (1<<(n)-1) ,所以最少的人数就是dp[1<<(n) -1];
     * 结果就是list[(1<<n) - 1] ，人员编号的状态随着 dp 的转移而转移
     */
    public static int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int m = people.size(), n = req_skills.length;
        int[] dp = new int[1 << n];
        List<Integer>[] res = new ArrayList[1 << n];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(req_skills[i], 1 << i);
        }
        for (int i = 0; i < (1 << n); i++) {
            dp[i] = -1;
            res[i] = new ArrayList<>();
        }
        dp[0] = 0;
        for (int i = 0; i < m; i++) {
            int skill = 0;
            //获取当前人的技能类
            for (String str : people.get(i)) {
                skill |= (map.get(str) == null ? 0 : map.get(str));
            }
            for (int st = 0; st < (1 << n); st++) {
                if (dp[st] == -1) {
                    continue;
                }
                int newState = skill | st;
                if (dp[newState] == -1 || dp[st] + 1 < dp[newState]) {
                    //更新人员数量
                    dp[newState] = dp[st] + 1;
                    res[newState].clear();
                    //更新人员的编号
                    res[newState].addAll(res[st]);
                    res[newState].add(i);
                }
            }
        }

        int[] result = new int[res[(1 << n) - 1].size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res[(1 << n) - 1].get(i);
        }
        return result;
    }
}
