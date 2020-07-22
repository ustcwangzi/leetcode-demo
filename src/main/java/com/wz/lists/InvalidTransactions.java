package com.wz.lists;

import java.util.*;

/**
 * A transaction is possibly invalid if:
 * the amount exceeds $1000, or;
 * if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
 * Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.
 * Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.
 *
 * Example 1:
 * Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
 * Output: ["alice,20,800,mtv","alice,50,100,beijing"]
 * Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes,
 * have the same name and is in a different city. Similarly the second one is invalid too.
 *
 * Example 2:
 * Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
 * Output: ["alice,50,1200,mtv"]
 *
 * Example 3:
 * Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
 * Output: ["bob,50,1200,mtv"]
 *
 * Constraints:
 * transactions.length <= 1000
 * Each transactions[i] takes the form "{name},{time},{amount},{city}"
 * Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
 * Each {time} consist of digits, and represent an integer between 0 and 1000.
 * Each {amount} consist of digits, and represent an integer between 0 and 2000.
 */
public class InvalidTransactions {
    public static void main(String[] args) {
        String[] transactions = new String[]{"alice,20,800,mtv", "alice,50,100,beijing"};
        System.out.println(invalidTransactions(transactions));

        transactions = new String[]{"alice,20,800,mtv", "alice,50,1200,mtv"};
        System.out.println(invalidTransactions(transactions));

        transactions = new String[]{"alice,20,800,mtv", "bob,50,1200,mtv"};
        System.out.println(invalidTransactions(transactions));
    }

    /**
     * 如果出现下述两种情况，交易无效：
     * 1. 交易金额超过 ¥1000
     * 2. 和另一个城市中同名的另一笔交易相隔 <= 60 分钟
     * 将所有相同名字的交易存储到 map 中，然后对相同名字的交易根据时间排序，对排好序的交易判断时间是不是不合法
     */
    public static List<String> invalidTransactions(String[] transactions) {
        Set<String> result = new HashSet<>();
        // 存储同名交易
        Map<String, List<Transaction>> map = new HashMap<>();
        for (String str : transactions) {
            String[] array = str.split(",");
            Transaction transaction = new Transaction(array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2]), array[3]);
            if (transaction.amount > 1000) {
                result.add(str);
            }

            List<Transaction> sameTrans = map.getOrDefault(array[0], new LinkedList<>());
            sameTrans.add(transaction);
            map.put(array[0], sameTrans);
        }

        for (List<Transaction> sameTrans : map.values()) {
            if (sameTrans.size() <= 1) {
                continue;
            }
            // 按时间进行排序
            sameTrans.sort(Comparator.comparingInt(o -> o.time));

            // 比较每笔交易是否合法
            for (int i = 1; i < sameTrans.size(); i++) {
                Transaction cur = sameTrans.get(i);
                for (int j = i - 1; j >= 0; j--) {
                    // 已排序，因此这里是break
                    if (cur.time - sameTrans.get(j).time > 60) {
                        break;
                    }
                    if (cur.city.equals(sameTrans.get(j).city)) {
                        continue;
                    }

                    result.add(cur.toString());
                    result.add(sameTrans.get(j).toString());
                }
            }
        }
        return new ArrayList<>(result);
    }

    private static class Transaction {
        private String name;
        private int time;
        private int amount;
        private String city;

        public Transaction(String name, int time, int amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }

        @Override
        public String toString() {
            return name + "," + time + "," + amount + "," + city;
        }
    }
}
