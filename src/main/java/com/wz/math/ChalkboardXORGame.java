package com.wz.math;

/**
 * We are given non-negative integers nums[i] which are written on a chalkboard.
 * Alice and Bob take turns erasing exactly one number from the chalkboard, with Alice starting first.
 * If erasing a number causes the bitwise XOR of all the elements of the chalkboard to become 0, then that player loses.
 * (Also, we'll say the bitwise XOR of one element is that element itself, and the bitwise XOR of no elements is 0.)
 * Also, if any player starts their turn with the bitwise XOR of all the elements of the chalkboard equal to 0, then that player wins.
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 *
 * Example:
 * Input: nums = [1, 1, 2]
 * Output: false
 * Explanation:
 * Alice has two choices: erase 1 or erase 2.
 * If she erases 1, the nums array becomes [1, 2]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 2 = 3.
 * Now Bob can remove any element he wants, because Alice will be the one to erase the last element and she will lose.
 * If Alice erases 2 first, now nums becomes [1, 1]. The bitwise XOR of all the elements of the chalkboard is 1 XOR 1 = 0. Alice will lose.
 *
 * Notes:
 * 1 <= N <= 1000.
 * 0 <= nums[i] <= 2^16.
 */
public class ChalkboardXORGame {
    public static void main(String[] args) {
        System.out.println(xorGame(new int[]{1, 1, 2}));
    }

    /**
     * 某个选手在开始移除数字之前，如果数组的亦或值为0的话，选手直接获胜，那么先手爱丽丝在开始开始之前也应该检查一遍数组的亦或值，
     * 如果是0的话，直接获胜。再来分析亦或值不为0的情况，既然不为0，那么亦或值肯定是有一个值的，假设其是 x。
     * 这个数组个数一旦是偶数的话，现在数字个数是偶数，且亦或值不为0，说明数组中的数字不全相同，因为偶数个相同数字的亦或值为0，
     * 那么爱丽丝只要移除一个不为 x 的数字就行了，这样移除后数组的亦或值也不会是0，那么由于鲍勃也是个机智的boy，
     * 他也不会移除一个使得剩余数组亦或值为0的数字，d但到最后一个数字时，鲍勃别无选择只能移除最后一个数字，此时数组为0，亦或值为0，爱丽丝获胜。
     * 那么为什么奇数个数字且亦或值不为0时，爱丽丝一定会输？因为即便爱丽丝先移除掉了一个数字，使得数组亦或值仍不为0，
     * 那么此时鲍勃面对的情况就是偶数个数字使得数组亦或值不为0，这跟上面推论爱丽丝一定会赢的情况一样，鲍勃也是个聪明的孩子，所以爱丽丝会输
     */
    public static boolean xorGame(int[] nums) {
        int x = 0, n = nums.length;
        for (int num : nums) {
            x ^= num;
        }
        return x == 0 || n % 2 == 0;
    }
}
