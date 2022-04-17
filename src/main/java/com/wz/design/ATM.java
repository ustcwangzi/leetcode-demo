package com.wz.design;

import java.util.Arrays;

/**
 * There is an ATM machine that stores banknotes of 5 denominations: 20, 50, 100, 200, and 500 dollars.
 * Initially the ATM is empty. The user can use the machine to deposit or withdraw any amount of money.
 * When withdrawing, the machine prioritizes using banknotes of larger values.
 * - For example, if you want to withdraw $300 and there are 2 $50 banknotes, 1 $100 banknote, and 1 $200 banknote,
 *   then the machine will use the $100 and $200 banknotes.
 * - However, if you try to withdraw $600 and there are 3 $200 banknotes and 1 $500 banknote,
 *   then the withdraw request will be rejected because the machine will first try to use the $500 banknote and then
 *   be unable to use banknotes to complete the remaining $100. Note that the machine is not allowed to use the $200 banknotes instead of the $500 banknote.
 * Implement the ATM class:
 * - ATM() Initializes the ATM object.
 * - void deposit(int[] banknotesCount) Deposits new banknotes in the order $20, $50, $100, $200, and $500.
 * - int[] withdraw(int amount) Returns an array of length 5 of the number of banknotes that will be handed to the user in the order $20, $50, $100, $200, and $500,
 *   and update the number of banknotes in the ATM after withdrawing. Returns [-1] if it is not possible (do not withdraw any banknotes in this case).
 *
 * Example 1:
 * Input
 * ["ATM", "deposit", "withdraw", "deposit", "withdraw", "withdraw"]
 * [[], [[0,0,1,2,1]], [600], [[0,1,0,1,1]], [600], [550]]
 * Output
 * [null, null, [0,0,1,0,1], null, [-1], [0,1,0,0,1]]
 * Explanation
 * ATM atm = new ATM();
 * atm.deposit([0,0,1,2,1]); // Deposits 1 $100 banknote, 2 $200 banknotes,
 *                           // and 1 $500 banknote.
 * atm.withdraw(600);        // Returns [0,0,1,0,1]. The machine uses 1 $100 banknote
 *                           // and 1 $500 banknote. The banknotes left over in the
 *                           // machine are [0,0,0,2,0].
 * atm.deposit([0,1,0,1,1]); // Deposits 1 $50, $200, and $500 banknote.
 *                           // The banknotes in the machine are now [0,1,0,3,1].
 * atm.withdraw(600);        // Returns [-1]. The machine will try to use a $500 banknote
 *                           // and then be unable to complete the remaining $100,
 *                           // so the withdraw request will be rejected.
 *                           // Since the request is rejected, the number of banknotes
 *                           // in the machine is not modified.
 * atm.withdraw(550);        // Returns [0,1,0,0,1]. The machine uses 1 $50 banknote
 *                           // and 1 $500 banknote.
 *
 * Constraints:
 * 1. banknotesCount.length == 5
 * 2. 0 <= banknotesCount[i] <= 10^9
 * 3. 1 <= amount <= 10^9
 * 4. At most 5000 calls in total will be made to withdraw and deposit.
 * 5. At least one call will be made to each function withdraw and deposit.
 */
public class ATM {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.deposit(new int[]{0, 0, 1, 2, 1});
        System.out.println(Arrays.toString(atm.withdraw(600)));
        atm.deposit(new int[]{0, 1, 0, 1, 1});
        System.out.println(Arrays.toString(atm.withdraw(600)));
        System.out.println(Arrays.toString(atm.withdraw(550)));
    }

    long[] banknotes;
    long[] counts;

    /**
     * 使用两个数组 banknotes[]、counts[] 分别存储 面额、剩余数量
     * - 存钱时，直接更新剩余数量 counts[]
     * - 取钱时，因为要求只能使用最大面额，因此从大到小遍历 banknotes[]，优先使用大于 amount 的面额
     *   使用之后，更新结果数组，同时 amount 减少对应金额。遍历结束时，若 amount != 0，说明无法满足条件，直接返回{-1}
     *   否则是满足条件的，更新 counts[]，将对应的剩余数量减少
     */
    public ATM() {
        banknotes = new long[]{20, 50, 100, 200, 500};
        counts = new long[5];
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < 5; i++) {
            counts[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        long[] result = new long[5];
        for (int i = 4; i >= 0 && amount > 0; i--) {
            long take = Math.min(amount / banknotes[i], counts[i]);
            result[i] = take;
            amount -= take * banknotes[i];
        }

        if (amount != 0) {
            return new int[]{-1};
        }
        for (int i = 0; i < 5; i++) {
            counts[i] -= result[i];
        }
        return Arrays.stream(result).mapToInt(cur -> (int) cur).toArray();
    }
}
