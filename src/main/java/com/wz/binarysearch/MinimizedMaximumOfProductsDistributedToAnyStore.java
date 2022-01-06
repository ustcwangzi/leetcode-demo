package com.wz.binarysearch;

import java.util.Arrays;

/**
 * You are given an integer n indicating there are n specialty retail stores. There are m product types of varying amounts,
 * which are given as a 0-indexed integer array quantities, where quantities[i] represents the number of products of the ith product type.
 * You need to distribute all products to the retail stores following these rules:
 * 1. A store can only be given at most one product type but can be given any amount of it.
 * 2. After distribution, each store will have been given some number of products (possibly 0).
 *    Let x represent the maximum number of products given to any store. You want x to be as small as possible,
 *    i.e., you want to minimize the maximum number of products that are given to any store.
 * Return the minimum possible x.
 *
 * Example 1:
 * Input: n = 6, quantities = [11,6]
 * Output: 3
 * Explanation: One optimal way is:
 * - The 11 products of type 0 are distributed to the first four stores in these amounts: 2, 3, 3, 3
 * - The 6 products of type 1 are distributed to the other two stores in these amounts: 3, 3
 * The maximum number of products given to any store is max(2, 3, 3, 3, 3, 3) = 3.
 *
 * Example 2:
 * Input: n = 7, quantities = [15,10,10]
 * Output: 5
 * Explanation: One optimal way is:
 * - The 15 products of type 0 are distributed to the first three stores in these amounts: 5, 5, 5
 * - The 10 products of type 1 are distributed to the next two stores in these amounts: 5, 5
 * - The 10 products of type 2 are distributed to the last two stores in these amounts: 5, 5
 * The maximum number of products given to any store is max(5, 5, 5, 5, 5, 5, 5) = 5.
 *
 * Example 3:
 * Input: n = 1, quantities = [100000]
 * Output: 100000
 * Explanation: The only optimal way is:
 * - The 100000 products of type 0 are distributed to the only store.
 * The maximum number of products given to any store is max(100000) = 100000.
 *
 * Constraints:
 * 1. m == quantities.length
 * 2. 1 <= m <= n <= 10^5
 * 3. 1 <= quantities[i] <= 10^5
 */
public class MinimizedMaximumOfProductsDistributedToAnyStore {
    public static void main(String[] args) {
        System.out.println(minimizedMaximum(6, new int[]{11, 6}));
        System.out.println(minimizedMaximum(7, new int[]{15, 10, 10}));
    }

    /**
     * n 个商店，m 种商品，第 i 种商品的数量为 quantities[i]，将所有商品分配到商店中，每个商店最多有一种商品，最小化分配商品的最大个数
     * 二分查找，思路与 {@link KokoEatingBananas} 类似，商品数最多为 quantities[] 的最大值，最少为 1，对该范围进行二分查找
     * 对于每个可能的结果 target，计算分给 n 个商店能否分配完成
     */
    public static int minimizedMaximum(int n, int[] quantities) {
        int left = 1, right = Arrays.stream(quantities).max().getAsInt(), result = 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (valid(quantities, n, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private static boolean valid(int[] quantities, int n, int target) {
        int count = 0;
        for (int quantity : quantities) {
            // 向上取整
            count += Math.ceil((double) quantity / target);
        }
        return count <= n;
    }
}
