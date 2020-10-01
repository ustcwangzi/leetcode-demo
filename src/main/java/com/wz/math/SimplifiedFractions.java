package com.wz.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive)
 * such that the denominator is less-than-or-equal-to n. The fractions can be in any order.
 *
 * Example 1:
 * Input: n = 2
 * Output: ["1/2"]
 * Explanation: "1/2" is the only unique fraction with a denominator less-than-or-equal-to 2.
 *
 * Example 2:
 * Input: n = 3
 * Output: ["1/2","1/3","2/3"]
 */
public class SimplifiedFractions {
    public static void main(String[] args) {
        System.out.println(simplifiedFractions(2));
        System.out.println(simplifiedFractions(3));
    }

    /**
     * 键在于计算分子和分母的最大公约数是否是1
     */
    public static List<String> simplifiedFractions(int n) {
        List<String> result = new ArrayList<>();
        for (int molecule = 1; molecule < n; molecule++) {
            for (int denominator = molecule + 1; denominator <= n; denominator++) {
                if (gcd(molecule, denominator) == 1) {
                    String cur = molecule + "/" + denominator;
                    result.add(cur);
                }
            }
        }
        return result;
    }

    private static int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}
