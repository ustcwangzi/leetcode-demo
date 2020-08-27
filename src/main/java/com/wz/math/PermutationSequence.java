package com.wz.math;

import java.util.ArrayList;
import java.util.List;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 *
 * Note:
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 *
 * Example 1:
 * Input: n = 3, k = 3
 * Output: "213"
 *
 * Example 2:
 * Input: n = 4, k = 9
 * Output: "2314"
 */
public class PermutationSequence {
    public static void main(String[] args) {
        System.out.println(getPermutation(4, 9));
    }

    /**
     * 以 n = 4, k = 17 的情况来分析，所有排列组合情况如下：
     * 1234
     * 1243
     * 1324
     * 1342
     * 1423
     * 1432
     * 2134
     * 2143
     * 2314
     * 2341
     * 2413
     * 2431
     * 3124
     * 3142
     * 3214
     * 3241
     * 3412 <--- k = 17
     * 3421
     * 4123
     * 4132
     * 4213
     * 4231
     * 4312
     * 4321
     * 可以发现，每一位上 1,2,3,4 分别都出现了6次，当最高位上的数字确定了，第二高位每个数字都出现了2次，
     * 当第二高位也确定了，第三高位上的数字都只出现了1次，当第三高位确定了，那么第四高位上的数字也只能出现一次，
     * 下面来看 k = 17 这种情况的每位数字如何确定，由于 k = 17 是转化为数组下标为 16：
     * 最高位可取 1,2,3,4 中的一个，每个数字出现 3! = 6 次（当最高位确定了，后面三位可以任意排列，所以是 3!，那么最高位的数字就会重复 3!次），
     * 所以 k = 16 的第一位数字的下标为 16 / 6 = 2，在 "1234" 中即3被取出。
     * 这里的k是要求的坐标为k的全排列序列，定义 k' 为当最高位确定后，要求的全排序列在新范围中的位置，
     * 同理，k'' 为当第二高为确定后，所要求的全排列序列在新范围中的位置，以此类推，下面来具体看看：
     * 第二位此时从 1,2,4 中取一个，k = 16，则此时的 k' = 16 % (3!) = 4，注意这里为何要取余，
     * 如果对这 24 个数以6个一组来分，那么 k=16 这个位置就是在第三组（k/6 = 2）中的第五个（k%6 = 4）数字。
     * 如下所示，而剩下的每个数字出现 2！= 2 次，所以第二数字的下标为 4 / 2 = 2，在 "124" 中即4被取出。
     * 3124
     * 3142
     * 3214
     * 3241
     * 3412 <--- k' = 4
     * 3421
     * 第三位此时从 1,2 中去一个，k' = 4，则此时的 k'' = 4 % (2!) = 0，如下所示，而剩下的每个数字出现 1！= 1 次，
     * 所以第三个数字的下标为 0 / 1 = 0，在 "12" 中即1被取出。
     * 3412 <--- k'' = 0
     * 3421
     * 第四位是从2中取一个，k'' = 0，则此时的 k''' = 0 % (1!) = 0，如下所示，而剩下的每个数字出现 0！= 1 次，
     * 所以第四个数字的下标为 0 / 1= 0，在 "2" 中即2被取出。
     * 3412 <--- k''' = 0
     *
     * 那么就可以找出规律了
     * a1 = k / (n - 1)!
     * k1 = k
     *
     * a2 = k1 / (n - 2)!
     * k2 = k1 % (n - 2)!
     * ...
     * an-1 = kn-2 / 1!
     * kn-1 = kn-2 % 1!
     *
     * an = kn-1 / 0!
     * kn = kn-1 % 0!
     */
    public static String getPermutation(int n, int k) {
        int[] f = new int[n];
        f[0] = 1;
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] * i;
        }

        --k;
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = n; i >= 1; --i) {
            int j = k / f[i - 1];
            k %= f[i - 1];
            builder.append(list.remove(j));
        }
        return builder.toString();
    }
}
