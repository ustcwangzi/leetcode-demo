package com.wz.string;

/**
 * Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.
 * However, you can add any number of parenthesis at any position to change the priority of operations.
 * You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format.
 * Your expression should NOT contain redundant parenthesis.
 *
 * Example:
 * Input: [1000,100,10,2]
 * Output: "1000/(100/10/2)"
 * Explanation:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
 * However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
 * since they don't influence the operation priority. So you should return "1000/(100/10/2)".
 *
 * Other cases:
 * 1000/(100/10)/2 = 50
 * 1000/(100/(10/2)) = 50
 * 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2
 *
 * Note:
 * 1. The length of the input array is [1, 10].
 * 2. Elements in the given array will be in range [2, 1000].
 * 3. There is only one optimal division for each test case.
 */
public class OptimalDivision {
    public static void main(String[] args) {
        System.out.println(optimalDivision(new int[]{1000, 100, 10, 2}));
    }

    /**
     * 数组中n个数字，如果不加括号就是：x1 / x2 / x3 / ... / xn
     * 如何加括号使得其值最大呢，那么就是将x2后面的除数都变成乘数，比如只有三个数字的情况 a / b / c，
     * 如果在后两个数上加上括号 a / (b / c)，实际上就是a / b * c。而且b永远只能当除数，a也永远只能当被除数。
     * 同理，x1只能当被除数，x2只能当除数，但是x3之后的数，只要都将其变为乘数，那么得到的值肯定是最大的，
     * 所以就只有一种加括号的方式，即: x1 / (x2 / x3 / ... / xn)
     */
    public static String optimalDivision(int[] nums) {
        StringBuilder result = new StringBuilder();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                result.append("/");
            }
            if (i == 1 && n > 2) {
                result.append("(");
            }
            result.append(nums[i]);
            if (i == n - 1 && n > 2) {
                result.append(")");
            }
        }
        return result.toString();
    }
}
