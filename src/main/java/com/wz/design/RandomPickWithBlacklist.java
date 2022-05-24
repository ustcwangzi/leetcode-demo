package com.wz.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * You are given an integer n and an array of unique integers blacklist. Design an algorithm to pick a random integer in the range [0, n - 1]
 * that is not in blacklist. Any integer that is in the mentioned range and not in blacklist should be equally likely to be returned.
 * Optimize your algorithm such that it minimizes the number of calls to the built-in random function of your language.
 * Implement the Solution class:
 * - Solution(int n, int[] blacklist) Initializes the object with the integer n and the blacklisted integers blacklist.
 * - int pick() Returns a random integer in the range [0, n - 1] and not in blacklist.
 *
 * Example 1:
 * Input
 * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
 * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
 * Output
 * [null, 0, 4, 1, 6, 1, 0, 4]
 * Explanation
 * Solution solution = new Solution(7, [2, 3, 5]);
 * solution.pick(); // return 0, any integer from [0,1,4,6] should be ok. Note that for every call of pick,
 *                  // 0, 1, 4, and 6 must be equally likely to be returned (i.e., with probability 1/4).
 * solution.pick(); // return 4
 * solution.pick(); // return 1
 * solution.pick(); // return 6
 * solution.pick(); // return 1
 * solution.pick(); // return 0
 * solution.pick(); // return 4
 *
 * Constraints:
 * 1. 1 <= n <= 10^9
 * 2. 0 <= blacklist.length <- min(10^5, n - 1)
 * 3. 0 <= blacklist[i] < n
 * 4. All the values of blacklist are unique.
 * 5. At most 2 * 10^4 calls will be made to pick.
 */
public class RandomPickWithBlacklist {
    public static void main(String[] args) {
        RandomPickWithBlacklist solution = new RandomPickWithBlacklist(7, new int[]{2, 3, 5});
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
    }

    private final Random random;
    private final int size;
    private final Map<Integer, Integer> map;

    /**
     * 要求尽量少调用系统方法，即 Random
     * 假设黑名单集中在后半部分，那么每次只需要在前半部分随机选取一个元素即可
     * 可以将前半部分命中黑名单的元素和后半部分命中白名单的元素建立映射，每次取到前面的黑名单元素时就映射到白名单元素上
     */
    public RandomPickWithBlacklist(int N, int[] blacklist) {
        random = new Random();
        // 前半部分的大小
        size = N - blacklist.length;
        map = new HashMap<>(blacklist.length);
        for (int black : blacklist) {
            map.put(black, -1);
        }

        int index = N - 1;
        for (int black : blacklist) {
            // 属于后半部分，不用映射
            if (black >= size) {
                continue;
            }
            // 找到可以映射的索引
            while (map.containsKey(index)) {
                index--;
            }
            // 将前半部分黑名单元素映射到后半部分白名单元素
            map.put(black, index--);
        }
    }

    public int pick() {
        int num = random.nextInt(size);
        // map 中存在，说明需要映射，否则直接使用即可
        return map.getOrDefault(num, num);
    }
}
