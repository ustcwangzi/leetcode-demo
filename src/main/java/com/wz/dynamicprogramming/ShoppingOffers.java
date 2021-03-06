package com.wz.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In LeetCode Store, there are some kinds of items to sell. Each item has a price.
 *
 * However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.
 *
 * You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.
 *
 * Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.
 *
 * You could use any of special offers as many times as you want.
 *
 * Example 1:
 * Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * Output: 14
 * Explanation:
 * There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
 * In special offer 1, you can pay $5 for 3A and 0B
 * In special offer 2, you can pay $10 for 1A and 2B.
 * You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
 *
 * Example 2:
 * Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 * Output: 11
 * Explanation:
 * The price of A is $2, and $3 for B, $4 for C.
 * You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.
 * You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.
 * You cannot add more items, though only $9 for 2A ,2B and 1C.
 * Note:
 * There are at most 6 kinds of items, 100 special offers.
 * For each item, you need to buy at most 6 of them.
 * You are not allowed to buy more items than you want, even if that would lower the overall price.
 */
public class ShoppingOffers {
    public static void main(String[] args) {
        List<List<Integer>> special = new ArrayList<>();
        special.add(Arrays.asList(3, 0, 5));
        special.add(Arrays.asList(1, 2, 10));
        System.out.println(shoppingOffers(Arrays.asList(2, 5), special, Arrays.asList(3, 2)));
    }

    /**
     * DFS
     * 先求出不使用优惠时的结果，然后遍历每个券，使用 tmp 记录用券的情况下还需要的商品份数，然后遍历每一个商品，
     * 如果某个商品需要的份数小于券中提供的份数，说明当前券不可用，直接 break，否则表明该券可用，
     * 将还缺少的份数保存在 remain 中，更新结果 result，对 remain 调用递归并且加上使用该券需要付的钱数
     */
    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int result = 0;
        // 不用券时的原始金额
        for (int i = 0; i < price.size(); i++) {
            result += price.get(i) * needs.get(i);
        }

        int temp;
        // 用券之后还缺少的份数
        List<Integer> remain = Arrays.asList(new Integer[needs.size()]);
        for (List<Integer> offer : special) {
            int i = 0;
            for (; i < needs.size(); i++) {
                temp = needs.get(i) - offer.get(i);
                // 超过需要的份数，不能用券
                if (temp < 0) {
                    break;
                }
                remain.set(i, temp);
            }

            if (i == needs.size()) {
                result = Math.min(result, shoppingOffers(price, special, remain) + offer.get(offer.size() - 1));
            }
        }
        return result;
    }
}
