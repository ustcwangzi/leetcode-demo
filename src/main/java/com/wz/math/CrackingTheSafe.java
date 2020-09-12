package com.wz.math;

import java.util.HashSet;
import java.util.Set;

/**
 * There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.
 * While entering a password, the last n digits entered will automatically be matched against the correct password.
 * For example, assuming the correct password is "345", if you type "012345",
 * the box will open because the correct password matches the suffix of the entered password.
 * Return any password of minimum length that is guaranteed to open the box at some point of entering it.
 *
 * Example 1:
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 *
 * Example 2:
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 *
 * Note:
 * n will be in the range [1, 4].
 * k will be in the range [1, 10].
 * k^n will be at most 4096.
 */
public class CrackingTheSafe {
    public static void main(String[] args) {
        System.out.println(crackSafe(1, 2));
        System.out.println(crackSafe(2, 2));
    }

    /**
     * 给了k个数字，值为0到k-1，可以组成n位密码。让找一个万能钥匙串，能破解任意的n位密码组合，
     * 这里对于破解的定义为只要密码是钥匙串的子串就可以破解了，要求出最短的一个万能钥匙串。
     * 来看一个例子，n=2，k=2，那么密码的组合有四种，
     * 00，01，10，11
     * 所以 00110 就是一种钥匙串，因为密码 00 (00110), 01 (00110), 10 (00110), 11 (00110), 分别都包括在钥匙串中。
     * 可以发现，为了尽可能的使钥匙串变短，密码之间尽可能要相互重叠，比如 00 和 01，就共享一个0，如果是3个数，012 和 120 共享两个数 "12"，
     * 再进一步们可以发现，两个长度为n的密码最好能共享 n-1 个数字，这样累加出来的钥匙串肯定是最短的。
     *
     * 密码共有n位，每一个位可以有k个数字，总共不同的密码总数就有k的n次方个。先从n位都是0的密码开始，取出钥匙串的最后 n-1 个数字，
     * 然后在后面依次添加其他数字，用一个 HashSet 来记录所有遍历过的密码，这样如果不在集合中，说明是一个新密码，
     * 而生成这个新密码也只是多加了一个数字，能保证钥匙串最短，这是一种贪婪的解法，相当的巧妙，
     * 就拿题目中的例子2来说明吧，n=2, k=2，最多有4个密码。开始时 res 初始化为 00，需要遍历4次。
     * 1. 第一次遍历时，先取出最后一个数字0，此时先尝试再后面添加1，可组成新密码 01，不在 HashSet 中，将其加入 HashSet，
     *    并且将这个1加到 res 后面，变为 001，然后断开内部 for 循环。
     * 2. 开始进行第2次遍历，取出 res 中最后一个数字1，先尝试在后面添加1，可组成新密码 11，不在 HashSet 中，将其加入 HashSet，
     *    并且将这个1加到 res 后面，变为 0011，然后断开内部 for 循环。
     * 3. 开始进行第3次遍历，取出 res 中最后一个数字1，先尝试在后面添加1，可组成密码 11，已在 HashSet 中，跳过；
     *    尝试在后面添加0，可组成密码 10，不在 HashSet 中，将其加入 HashSet，并且将这个0加到 res 后面，变为 00110，然后断开内部 for 循环。
     * 4. 开始进行第4次遍历，取出 res 中最后一个数字0，先尝试在后面添加1，可组成密码 01，已在 HashSet 中，跳过；
     *    尝试在后面添加0，可组成密码 00，已在 HashSet 中，跳过，循环结束。
     * 这样最终的 res 为 00110
     */
    private static String crackSafe(int n, int k) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            res.append("0");
        }

        Set<String> visited = new HashSet<>();
        visited.add(res.toString());
        helper(n, k, visited, res);

        return res.toString();
    }

    private static void helper(int n, int k, Set<String> visited, StringBuilder res) {
        if (visited.size() == Math.pow(k, n)) {
            return;
        }

        String pre = res.substring(res.length() - n + 1);
        for (int i = k - 1; i >= 0; --i) {
            String cur = pre + i;
            if (visited.contains(cur)) {
                continue;
            }
            visited.add(cur);
            res.append(i);
            helper(n, k, visited, res);
        }
    }
}
