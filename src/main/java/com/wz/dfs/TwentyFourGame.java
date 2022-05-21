package com.wz.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9].
 * You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.
 * You are restricted with the following rules:
 * - The division operator '/' represents real division, not integer division.
 *   For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
 * - Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
 *   For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
 * - You cannot concatenate numbers together
 *   For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
 * Return true if you can get such expression that evaluates to 24, and false otherwise.
 *
 * Example 1:
 * Input: cards = [4,1,8,7]
 * Output: true
 * Explanation: (8-4) * (7-1) = 24
 *
 * Example 2:
 * Input: cards = [1,2,1,2]
 * Output: false
 *
 * Constraints:
 * 1. cards.length == 4
 * 2. 1 <= cards[i] <= 9
 */
public class TwentyFourGame {
    public static void main(String[] args) {
        System.out.println(judgePoint24(new int[]{4, 1, 8, 7}));
        System.out.println(judgePoint24(new int[]{1, 2, 1, 2}));
    }

    /**
     * 通过 DFS 找到所有的组合，任意组合能得到结果则返回 true
     * 首先获取两个数字，计算这两个数字能得到的所有结果，然后遍历每一种结果，对于每一种结果，先加入到 next 集合中
     * 再从 cards 中获取之前没有选择的数字，同样加入到 next 集合中，接着使用 next 集合进行下一次 DFS
     * 若当前 DFS 入参集合中只有一个元素，说明已选择全部元素进行计算，此时判断结果是否是 24 即可
     */
    public static boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>(cards.length);
        Arrays.stream(cards).mapToDouble(Double::valueOf).forEach(list::add);
        return dfs(list);
    }

    private static boolean dfs(List<Double> cards) {
        if (cards.size() == 1) {
            return Math.abs(cards.get(0) - 24) < 0.001;
        }

        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                // 先选择两个数字并计算所有能得到的结果
                double a = cards.get(i), b = cards.get(j);
                List<Double> combinations = Arrays.asList(a + b, a - b, b - a, a * b, a / b, b / a);
                for (double combination : combinations) {
                    List<Double> next = new ArrayList<>();
                    next.add(combination);

                    // 将其余数字加入到集合中进行下一次的 DFS
                    for (int k = 0; k < cards.size(); k++) {
                        if (k != i && k != j) {
                            next.add(cards.get(k));
                        }
                    }
                    if (dfs(next)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
