package com.wz.other;

/**
 * You are given a 0-indexed string street. Each character in street is either 'H' representing a house or '.' representing an empty space.
 * You can place buckets on the empty spaces to collect rainwater that falls from the adjacent houses.
 * The rainwater from a house at index i is collected if a bucket is placed at index i - 1 and/or index i + 1.
 * A single bucket, if placed adjacent to two houses, can collect the rainwater from both houses.
 * Return the minimum number of buckets needed so that for every house, there is at least one bucket collecting rainwater from it, or -1 if it is impossible.
 *
 * Example 1:
 * Input: street = "H..H"
 * Output: 2
 * Explanation:
 * We can put buckets at index 1 and index 2.
 * "H..H" -> "HBBH" ('B' denotes where a bucket is placed).
 * The house at index 0 has a bucket to its right, and the house at index 3 has a bucket to its left.
 * Thus, for every house, there is at least one bucket collecting rainwater from it.
 *
 * Example 2:
 * Input: street = ".H.H."
 * Output: 1
 * Explanation:
 * We can put a bucket at index 2.
 * ".H.H." -> ".HBH." ('B' denotes where a bucket is placed).
 * The house at index 1 has a bucket to its right, and the house at index 3 has a bucket to its left.
 * Thus, for every house, there is at least one bucket collecting rainwater from it.
 *
 * Example 3:
 * Input: street = ".HHH."
 * Output: -1
 * Explanation:
 * There is no empty space to place a bucket to collect the rainwater from the house at index 2.
 * Thus, it is impossible to collect the rainwater from all the houses.
 *
 * Constraints:
 * 1. 1 <= street.length <= 105
 * 2. street[i] is either'H' or '.'.
 */
public class MinimumNumberOfBucketsRequiredToCollectRainwaterFromHouses {
    public static void main(String[] args) {
        System.out.println(minimumBuckets("H..H"));
        System.out.println(minimumBuckets(".HHH."));
    }

    /**
     * 遍历字符串，对于当前位置 i，先检查下一个位置 i+1 是否为 '.'，若是则可以在下一个位置放置 bucket
     * 若不是，则检查前一个位置 i-1 是否为 '.'，若是则可以在前一个位置放置 bucket
     * 若左右两边都不是 '.'，说明没有位置可以放置 bucket，直接返回 -1
     */
    public static int minimumBuckets(String street) {
        int count = 0;
        for (int i = 0; i < street.length(); i++) {
            if (street.charAt(i) != 'H') {
                continue;
            }
            if (i + 1 < street.length() && street.charAt(i + 1) == '.') {
                count++;
                i += 2;
                continue;
            }
            if (i - 1 >= 0 && street.charAt(i - 1) == '.') {
                count++;
                continue;
            }
            return -1;
        }
        return count;
    }
}
