package com.wz.other;

/**
 * You are playing the Bulls and Cows game with your friend.
 * You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:
 * 1. The number of "bulls", which are digits in the guess that are in the correct position.
 * 2. The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position.
 *    Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.
 *
 * Example 1:
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1807"
 *   |
 * "7810"
 *
 * Example 2:
 * Input: secret = "1123", guess = "0111"
 * Output: "1A1B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1123"        "1123"
 *   |      or     |
 * "0111"        "0111"
 * Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
 *
 * Example 3:
 * Input: secret = "1", guess = "0"
 * Output: "0A0B"
 *
 * Example 4:
 * Input: secret = "1", guess = "1"
 * Output: "1A0B"
 *
 * Constraints:
 * 1. 1 <= secret.length, guess.length <= 1000
 * 2. secret.length == guess.length
 * 3. secret and guess consist of digits only.
 */
public class BullsAndCows {
    public static void main(String[] args) {
        System.out.println(getHint("1807", "7810"));
        System.out.println(getHint("1123", "0111"));
    }

    /**
     * 猜的结果和真实结果做对比，有多少个数字和位置都正确的叫做 bulls，有多少数字正确但位置不对的叫做 cows
     * 两次遍历，第一次遍历找出所有位置相同且值相同的数字，即 bulls，并且记录 secret 中不是 bulls 的数字出现的次数
     * 然后第二次遍历针对 guess 中不是 bulls 的位置，如果在哈希表中存在，代表数字正确，cows自增 1，然后映射值减 1
     */
    public static String getHint(String secret, String guess) {
        int[] count = new int[10];
        int bulls = 0, cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            // 数字和位置都正确
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                count[secret.charAt(i) - '0']++;
            }
        }
        for (int i = 0; i < secret.length(); i++) {
            //  数字正确但位置不对
            if (secret.charAt(i) != guess.charAt(i) && count[guess.charAt(i) - '0'] > 0) {
                cows++;
                count[guess.charAt(i) - '0']--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
