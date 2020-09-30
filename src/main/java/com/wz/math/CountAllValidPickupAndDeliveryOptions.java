package com.wz.math;

/**
 * Given n orders, each order consist in pickup and delivery services.
 * Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 1
 * Output: 1
 * Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
 *
 * Example 2:
 * Input: n = 2
 * Output: 6
 * Explanation: All possible orders:
 * (P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
 * This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
 */
public class CountAllValidPickupAndDeliveryOptions {
    public static void main(String[] args) {
        System.out.println(countOrders(1));
        System.out.println(countOrders(2));
    }

    /**
     * 最后一步，假设 n-1 个 pair 已经排序好了，需要 insert 最后一个，现在有 2*(n-1) = 2*n-2的元素
     * 那么 first element 有 2*n-1 个位子，second element 就有 2*n 个可能性，总共 (2*n-1)*2*n 种可能
     * 题目要求 Di 必须在 Pi 后面，那么所有可能性 /2; 那么第n个pair的可能性就是 (2*n-1) * n, 这是最外层的一种可能，
     * 里面全部是 sub problem，n从1到n - 1，全部是乘积
     */
    public static int countOrders(int n) {
        long result = 1, mod = (long) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            result = result * (i * 2 - 1) * i % mod;
        }
        return (int) result;
    }
}
