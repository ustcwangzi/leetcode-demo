package com.wz.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An array nums of length n is beautiful if:
 * 1. nums is a permutation of the integers in the range [1, n].
 * 2. For every 0 <= i < j < n, there is no index k with i < k < j where 2 * nums[k] == nums[i] + nums[j].
 * Given the integer n, return any beautiful array nums of length n. There will be at least one valid answer for the given n.
 *
 * Example 1:
 * Input: n = 4
 * Output: [2,1,4,3]
 *
 * Example 2:
 * Input: n = 5
 * Output: [3,1,2,5,4]
 *
 * Constraints:
 * 1 <= n <= 1000
 */
public class BeautifulArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(beautifulArray(4)));
        System.out.println(Arrays.toString(beautifulArray(5)));
    }

    /**
     * 2 * nums[i] != nums[j] + nums[k]，当 nums[j]，nums[k] 一个是奇数、一个是偶数时，它们的和必为奇数，一定满足条件
     * 如果将数组分成两个部分，一部分全奇数、一部分全偶数，并且这两个部分是 Beautiful-Array，那么将它们合并，得到的也是 Beautiful-Array
     * 那么如何得到这两个部分呢？需要一些数学证明，很容易证明得到 Beautiful-Array 满足以下的两个性质：
     * 1. 同乘一个数依然是 Beautiful-Array
     * 2. 同加一个数依然是 Beautiful-Array
     * 得到这两个性质后就可以进行构造了，从 {1} 开始，每次得到 {nums * 2 - 1} 和 {nums * 2}，显然这两个数组是 Beautiful-Array，
     * 并且分别是奇数和偶数数组，将它们合并之后就会得到长度更长的数组，这样就可以构造出来结果需要的数组
     */
    public static int[] beautifulArray(int n) {
        List<Integer> result = new ArrayList<>(n);
        result.add(1);
        while (result.size() < n) {
            List<Integer> tmp = new ArrayList<>();
            for (int num : result) {
                if (num * 2 - 1 <= n) {
                    tmp.add(num * 2 - 1);
                }
            }
            for (int num : result) {
                if (num * 2 <= n) {
                    tmp.add(num * 2);
                }
            }
            result = tmp;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
