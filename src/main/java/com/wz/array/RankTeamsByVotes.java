package com.wz.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In a special ranking system, each voter gives a rank from highest to lowest to all teams participated in the competition.
 * The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position,
 * we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved.
 * If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.
 * Given an array of strings votes which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.
 * Return a string of all teams sorted by the ranking system.
 *
 * Example 1:
 * Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
 * Output: "ACB"
 * Explanation: Team A was ranked first place by 5 voters. No other team was voted as first place so team A is the first team.
 * Team B was ranked second by 2 voters and was ranked third by 3 voters.
 * Team C was ranked second by 3 voters and was ranked third by 2 voters.
 * As most of the voters ranked C second, team C is the second team and team B is the third.
 *
 * Example 2:
 * Input: votes = ["WXYZ","XYZW"]
 * Output: "XWYZ"
 * Explanation: X is the winner due to tie-breaking rule. X has same votes as W for the first position but
 *              X has one vote as second position while W doesn't have any votes as second position.
 *
 * Example 3:
 * Input: votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]
 * Output: "ZMNAGUEDSJYLBOPHRQICWFXTVK"
 * Explanation: Only one voter so his votes are used for the ranking.
 *
 * Example 4:
 * Input: votes = ["BCA","CAB","CBA","ABC","ACB","BAC"]
 * Output: "ABC"
 * Explanation:
 * Team A was ranked first by 2 voters, second by 2 voters and third by 2 voters.
 * Team B was ranked first by 2 voters, second by 2 voters and third by 2 voters.
 * Team C was ranked first by 2 voters, second by 2 voters and third by 2 voters.
 * There is a tie and we rank teams ascending by their IDs.
 *
 * Example 5:
 * Input: votes = ["M","M","M","M"]
 * Output: "M"
 * Explanation: Only team M in the competition so it has the first rank.
 *
 *
 * Constraints:
 * 1 <= votes.length <= 1000
 * 1 <= votes[i].length <= 26
 * votes[i].length == votes[j].length for 0 <= i, j < votes.length.
 * votes[i][j] is an English upper-case letter.
 * All characters of votes[i] are unique.
 * All the characters that occur in votes[0] also occur in votes[j] where 1 <= j < votes.length.
 */
public class RankTeamsByVotes {
    public static void main(String[] args) {
        String[] votes = new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"};
        System.out.println(rankTeams(votes));

        votes = new String[]{"WXYZ", "XYZW"};
        System.out.println(rankTeams(votes));

        votes = new String[]{"BCA", "CAB", "CBA", "ABC", "ACB", "BAC"};
        System.out.println(rankTeams(votes));
    }

    /**
     * 参赛团队的排名次序依照其所获"排位第一"的票数决定，如果存在多个团队并列的情况，将继续考虑其"排位第二"的票数，以此类推
     * 如果在考虑完所有投票情况后仍然出现并列现象，则根据团队字母的字母顺序进行排名
     * 因此首先想到的就是将投票结果存在 list 中，然后按照 名次票数降序、队名升序 进行排序
     * 先用 teamMap 存储对于每个队的投票情况，key 是队名，value 是各名次的得票数，然后遍历 teamMap 将得票情况放入 list，最后排序
     */
    public static String rankTeams(String[] votes) {
        if (votes.length == 1 || votes[0].length() == 1) {
            return votes[0];
        }

        int teamNum = votes[0].length();
        // 存储每个队的得票情况
        Map<Character, Team> teamMap = new HashMap<>(teamNum);
        for (String vote : votes) {
            char[] array = vote.toCharArray();
            for (int i = 0; i < teamNum; i++) {
                Team team = teamMap.getOrDefault(array[i], new Team(array[i], new int[teamNum]));
                team.votes[i]++;
                teamMap.put(array[i], team);
            }
        }

        List<Team> teamList = new ArrayList<>(teamNum);
        teamList.addAll(teamMap.values());

        teamList.sort((o1, o2) -> {
            int len = o1.votes.length;
            // 名次票数降序
            for (int i = 0; i < len; i++) {
                if (o1.votes[i] != o2.votes[i]) {
                    return o2.votes[i] - o1.votes[i];
                }
            }
            // 队名升序
            return o1.name - o2.name;
        });

        StringBuilder result = new StringBuilder();
        for (Team team : teamList) {
            result.append(team.name);
        }
        return result.toString();
    }

    private static class Team {
        private char name;
        // 得到 第一、第二 ... 的投票情况
        private int[] votes;

        public Team(char name, int[] votes) {
            this.name = name;
            this.votes = votes;
        }
    }
}
