package com.wz.math;

import java.util.HashMap;
import java.util.Map;

/**
 * In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you
 * how many other rabbits have the same color as them. Those answers are placed in an array.
 * Return the minimum number of rabbits that could be in the forest.
 *
 * Examples:
 * Input: answers = [1, 1, 2]
 * Output: 5
 * Explanation:
 * The two rabbits that answered "1" could both be the same color, say red.
 * The rabbit than answered "2" can't be red or the answers would be inconsistent.
 * Say the rabbit that answered "2" was blue.
 * Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
 * The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
 *
 * Input: answers = [10, 10, 10]
 * Output: 11
 *
 * Input: answers = []
 * Output: 0
 *
 * Note:
 * answers will have length at most 1000.
 * Each answers[i] will be an integer in the range [0, 999].
 */
public class RabbitsInForest {
    public static void main(String[] args) {
        System.out.println(numRabbits(new int[]{1, 1, 2}));
        System.out.println(numRabbits(new int[]{10, 10, 10}));
    }

    /**
     * 有一堆成了精的兔子，有着不同的颜色，每个兔子会告诉你森林中还有多少个和其颜色相同的兔子，当然并不是所有的兔子多出现在数组中，
     * 所以要根据兔子们的回答，来估计森林中最少有多少只能确定的兔子。
     *
     * 例子1给的数字是 [1, 1, 2]，第一只兔子说森林里还有另一只兔子跟其颜色一样，第二只兔子也说还有另一只兔子和其颜色一样，
     * 那么为了使兔子总数最少，可以让前两只兔子是相同的颜色，可以使其回答不会矛盾。第三只兔子说森林里还有两只兔子和其颜色一样，
     * 那么这只兔的颜色就不能和前两只兔子的颜色相同了，否则就会跟前面两只兔子的回答矛盾了，因为根据第三只兔子的描述，
     * 森林里共有三只这种颜色的兔子，所有总共可以推断出最少有五只兔子。
     * 对于例子2，[10, 10, 10] 来说，这三只兔子都说森林里还有10只跟其颜色相同的兔子，那么这三只兔子颜色可以相同，所以总共有11只兔子。
     * 来看一个比较tricky的例子，[0, 0, 1, 1, 1]，前两只兔子都说森林里没有兔子和其颜色相同了，那么这两只兔子就是森林里独一无二的兔子，
     * 且颜色并不相同，所以目前已经确定了两只。然后后面三只都说森林里还有一只兔子和其颜色相同，那么这三只兔子就不可能颜色都相同了，
     * 但可以让两只颜色相同，另外一只颜色不同，那么就是说还有一只兔子并没有在数组中，所以森林中最少有6只兔子。
     *
     * 分析完了这几个例子，可以发现，如果某个兔子回答的数字是 x，那么说明森林里共有 x+1 个相同颜色的兔子，最多允许 x+1 个兔子同时回答 x 个，
     * 一旦超过了 x+1 个兔子，那么就得再增加了 x+1 个新兔子了。
     * 所以可以使用一个 HashMap 来建立某种颜色兔子的总个数和在数组中还允许出现的个数之间的映射，然后遍历数组中的每个兔子，
     * 如果该兔子回答了 x 个，若该颜色兔子的总个数 x+1 不在HashMap中，或者值为0了，将这 x+1 个兔子加入结果 result 中，
     * 然后将其映射值设为x，表示在数组中还允许出现 x 个也回答 x 的兔子；否则的话，将映射值自减1即可
     */
    public static int numRabbits(int[] answers) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int ans : answers) {
            if (!map.containsKey(ans + 1) || map.get(ans + 1) == 0) {
                result += ans + 1;
                map.put(ans + 1, ans);
            } else {
                map.put(ans + 1, map.get(ans + 1) - 1);
            }
        }
        return result;
    }
}
