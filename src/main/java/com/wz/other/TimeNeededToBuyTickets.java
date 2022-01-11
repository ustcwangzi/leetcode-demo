package com.wz.other;

/**
 * There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.
 * You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].
 * Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line
 * (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.
 * Return the time taken for the person at position k (0-indexed) to finish buying tickets.
 *
 * Example 1:
 * Input: tickets = [2,3,2], k = 2
 * Output: 6
 * Explanation:
 * - In the first pass, everyone in the line buys a ticket and the line becomes [1, 2, 1].
 * - In the second pass, everyone in the line buys a ticket and the line becomes [0, 1, 0].
 * The person at position 2 has successfully bought 2 tickets and it took 3 + 3 = 6 seconds.
 *
 * Example 2:
 * Input: tickets = [5,1,1,1], k = 0
 * Output: 8
 * Explanation:
 * - In the first pass, everyone in the line buys a ticket and the line becomes [4, 0, 0, 0].
 * - In the next 4 passes, only the person in position 0 is buying tickets.
 * The person at position 0 has successfully bought 5 tickets and it took 4 + 1 + 1 + 1 + 1 = 8 seconds.
 *
 * Constraints:
 * 1. n == tickets.length
 * 2. 1 <= n <= 100
 * 3. 1 <= tickets[i] <= 100
 * 4. 0 <= k < n
 */
public class TimeNeededToBuyTickets {
    public static void main(String[] args) {
        System.out.println(timeRequiredToBuy(new int[]{2, 3, 2}, 2));
        System.out.println(timeRequiredToBuy(new int[]{5, 1, 1, 1}, 0));
    }

    /**
     * 遍历 tickets[]，对于每个 tickets[i]
     * 若 tickets[i] < tickets[k]，则会在 tickets[k] 之前减到 0，因此需要全部加到结果中
     * 若 tickets[i] >= tickets[k]，则分两种情况：
     * 1. i 在 k 之前，则需要扣减的次数为 tickets[k]，因此将 tickets[k] 加到结果中
     * 2. i 在 k 之后，因为最后一次扣减到 k 就停止了，即少扣减一次，因此将 tickets[k]-1 加到结果中
     */
    public static int timeRequiredToBuy(int[] tickets, int k) {
        int result = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] < tickets[k]) {
                result += tickets[i];
                continue;
            }
            if (i <= k) {
                result += tickets[k];
            } else {
                result += tickets[k] - 1;
            }
        }
        return result;
    }
}
