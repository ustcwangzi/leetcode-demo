package com.wz.string;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.
 * The 'closest' is defined as absolute difference minimized between two integers.
 *
 * Example 1:
 * Input: "123"
 * Output: "121"
 *
 * Note:
 * 1. The input n is a positive integer represented by string, whose length will not exceed 18.
 * 2. If there is a tie, return the smaller one as answer.
 */
public class FindTheClosestPalindrome {
    public static void main(String[] args) {
        System.out.println(nearestPalindromic("123"));
    }

    /**
     * If num length is high then it will be of type 1000..1
     * If num length is low then it will be of form 9999..9
     * If same then depending on what is before the middle element we have a total of 3 cases :
     * 23456 / 23432 (This is best, if we generate 23332 or 23532 they both will be at a greater distance).
     * So why should we consider to look for (235.. or 233..). For cases like below :
     * 23499 / 23432 (99 - 32 = 67)
     * 23499 / 23532 (132 - 99 = 33)
     * 99423 / 99499 (99 - 32 = 67)
     * 99423 / 99399 (123 - 99 = 24)
     */
    public static String nearestPalindromic(String n) {
        int length = n.length();
        int mid = length % 2 == 0 ? length / 2 - 1 : length / 2;
        long left = Long.parseLong(n.substring(0, mid + 1)), num = Long.parseLong(n);

        // candidate has different number of digits
        List<Long> candidates = new ArrayList<>();
        // candidate has less digits (999...9)
        candidates.add((long) Math.pow(10, length - 1) - 1);
        // candidate has more digits (10000...1)
        candidates.add((long) Math.pow(10, length) + 1);
        // candidate has same number of digits
        // 23456
        candidates.add(mirrorLeftSide(left, length % 2 == 0));
        // 23499
        candidates.add(mirrorLeftSide(left + 1, length % 2 == 0));
        // 99234
        candidates.add(mirrorLeftSide(left - 1, length % 2 == 0));

        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> {
            if (Math.abs(a - num) == Math.abs(b - num)) {
                return Long.compare(a - num, b - num);
            }
            return Math.abs(a - num) < Math.abs(b - num) ? -1 : 1;
        });
        pq.addAll(candidates);
        if (pq.peek() == num) {
            pq.poll();
        }

        return String.valueOf(pq.peek());
    }

    private static long mirrorLeftSide(long left, boolean even) {
        long res = left;
        if (!even) {
            left = left / 10;
        }
        while (left > 0) {
            res = res * 10 + left % 10;
            left /= 10;
        }
        return res;
    }
}
