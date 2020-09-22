package com.wz.math;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Given a single positive integer x, we will write an expression of the form x (op1) x (op2) x (op3) x ...
 * where each operator op1, op2, etc. is either addition, subtraction, multiplication, or division (+, -, *, or /).
 * For example, with x = 3, we might write 3 * 3 / 3 + 3 - 3 which is a value of 3.
 * When writing such an expression, we adhere to the following conventions:
 * The division operator (/) returns rational numbers.
 * There are no parentheses placed anywhere.
 * We use the usual order of operations: multiplication and division happens before addition and subtraction.
 * It's not allowed to use the unary negation operator (-).
 * For example, "x - x" is a valid expression as it only uses subtraction, but "-x + x" is not because it uses negation.
 * We would like to write an expression with the least number of operators such that the expression equals the given target.
 * Return the least number of operators used.
 *
 * Example 1:
 * Input: x = 3, target = 19
 * Output: 5
 * Explanation: 3 * 3 + 3 * 3 + 3 / 3.  The expression contains 5 operations.
 *
 * Example 2:
 * Input: x = 5, target = 501
 * Output: 8
 * Explanation: 5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5.  The expression contains 8 operations.
 */
public class LeastOperatorsToExpressNumber {
    public static void main(String[] args) {
        System.out.println(leastOpsExpressTarget(3, 19));
        System.out.println(leastOpsExpressTarget(5, 501));
    }

    /**
     * 1. 整个解法的根源基于将target转换为x进制的表示方法；
     * 2. 每次优先尝试x的满足条件的最高次幂，即 exp=logx(t); lower = t - x^exp; higher = x^(exp+1) - t;
     *    要形成当前的t，一定是先形成lower或者higher，然后再另lower或higher和t的差值成为新的t，继续计算，最终将每次构成x幂次所需的操作数相加。
     * 3. 构成1只能靠+x/x, 需要操作数2，因为还有前面的加号，之后对于x^1=+x, x^2=+x*x, x^3=+x*x*x...x^c, 所需的操作数就是 c.
     * 4. 将求解过程看作一个有向图，用dijkstra搜索最短路径。
     */
    public static int leastOpsExpressTarget(int x, int target) {
        if (x == target) {
            return 0;
        }
        if (target == 1) {
            // x/x=1
            return 1;
        }

        // 当前总符号数，剩余target
        TreeSet<int[]> bst = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            else return Integer.compare(a[1], b[1]);
        });

        Set<Integer> visited = new HashSet<>();
        bst.add(new int[]{0, target});
        while (!bst.isEmpty()) {
            int[] entry = bst.first();
            bst.remove(entry);
            int c = entry[0];
            int t = entry[1];
            if (visited.contains(t)) {
                continue;
            }
            if (t == 0) {
                return c - 1;
            }
            visited.add(t);
            int exp = (int) (Math.log(t) / Math.log(x));
            int lower = (int) Math.pow(x, exp);
            int higher = (int) Math.pow(x, exp + 1);
            if (!visited.contains(t - lower)) {
                bst.add(new int[]{c + (exp == 0 ? 2 : exp), t - lower});
            }
            if (!visited.contains(higher - t)) {
                bst.add(new int[]{c + exp + 1, higher - t});
            }
        }

        return -1;
    }
}
