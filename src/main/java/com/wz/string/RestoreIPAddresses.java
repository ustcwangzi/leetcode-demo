package com.wz.string;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.
 * A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros.
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 *
 * Example 1:
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 *
 * Example 2:
 * Input: s = "010010"
 * Output: ["0.10.0.10","0.100.1.0"]
 */
public class RestoreIPAddresses {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("010010"));
    }

    /**
     * 递归
     * k 代表拆分出来的IP段个数，当 k == 4 并且 剩余字符串 str 为空时，将拆分好的结果放入最终结果中
     * k != 4 时，对于每一段，分别用一位、两位、三位来尝试拆分，可以拆分则递归下去，不可以拆分直接终止
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new LinkedList<>();
        helper(s, 0, "", result);
        return result;
    }

    /**
     * @param str 剩余字符串
     * @param k 拆分出来的IP段个数
     * @param group 拆分好的结果
     * @param result 最终结果
     */
    private static void helper(String str, int k, String group, List<String> result) {
        if (k == 4) {
            if (str.isEmpty()) {
                result.add(group);
            }
            return;
        }

        for (int i = 1; i < 4; i++) {
            if (str.length() < i) {
                break;
            }
            int num = Integer.parseInt(str.substring(0, i));
            // 有效范围是[0, 255]，且以 0 开始时，只能是一位，不能是多位，如 01、010 都是不合法的
            if (num <= 255 && String.valueOf(num).length() == i) {
                helper(str.substring(i), k + 1, group + num + (k == 3 ? "" : "."), result);
            }
        }
    }
}
