package com.wz.dynamicprogramming;

/**
 * @link ../../../../resource/MinimumDistanceToTypeWordUsingTwoFingers.jpg
 * You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate,
 * for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1),
 * the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).
 * Given the string word, return the minimum total distance to type such string using only two fingers.
 * The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.
 * Note that the initial positions of your two fingers are considered free so don't count towards your total distance,
 * also your two fingers do not have to start at the first letter or the first two letters.
 *
 * Example 1:
 * Input: word = "CAKE"
 * Output: 3
 * Explanation:
 * Using two fingers, one optimal way to type "CAKE" is:
 * Finger 1 on letter 'C' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
 * Finger 2 on letter 'K' -> cost = 0
 * Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
 * Total distance = 3
 *
 * Example 2:
 * Input: word = "HAPPY"
 * Output: 6
 * Explanation:
 * Using two fingers, one optimal way to type "HAPPY" is:
 * Finger 1 on letter 'H' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
 * Finger 2 on letter 'P' -> cost = 0
 * Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
 * Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
 * Total distance = 6
 *
 * Constraints:
 * 1. 2 <= word.length <= 300
 * 2. Each word[i] is an English uppercase letter.
 */
public class MinimumDistanceToTypeWordUsingTwoFingers {
    public static void main(String[] args) {
        System.out.println(minimumDistance("HAPPY"));
    }

    public static int minimumDistance(String word) {
        if (word == null || word.length() == 0) {
            return 0;
        }
        int n = word.length();
        // 27，只是为了char为null的时候，index可以包含null的情况，设置为0；
        int[][][] dp = new int[27][27][n];
        // 刚开始设置为null，这样两个指头走上第一char的时候，cost 会return 0；
        return dfs(word, 0, dp, null, null);
    }

    private static int dfs(String word, int index, int[][][] dp, Character c1, Character c2) {
        if (index == word.length()) {
            return 0;
        }
        int index1 = c1 == null ? 0 : c1 - 'A' + 1;
        int index2 = c2 == null ? 0 : c2 - 'A' + 1;
        if (dp[index1][index2][index] != 0) {
            return dp[index1][index2][index];
        }

        char next = word.charAt(index);
        dp[index1][index2][index] = Math.min(cost(c1, next) + dfs(word, index + 1, dp, next, c2),
                cost(c2, next) + dfs(word, index + 1, dp, c1, next));
        return dp[index1][index2][index];
    }

    private static int cost(Character c1, Character c2) {
        if (c1 == null || c2 == null) {
            return 0;
        }
        int len1 = c1 - 'A';
        int len2 = c2 - 'A';
        int x1 = len1 / 6;
        int x2 = len2 / 6;
        int y1 = len1 % 6;
        int y2 = len2 % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
