package com.wz.other;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
 *
 * Example 1:
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 *
 * Example 2:
 * Input: nums = [0]
 * Output: 0
 *
 * Example 3:
 * Input: nums = [8,10,2]
 * Output: 10
 *
 * Constraints:
 * 1. 1 <= nums.length <= 2 * 10^5
 * 2. 0 <= nums[i] <= 2^31 - 1
 */
public class MaximumXOROfTwoNumbersInArray {
    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
        System.out.println(findMaximumXOR(new int[]{8, 10, 2}));
    }

    /**
     * 若 a^b=c，那么 a=b^c，假设 tmp 是要验证的当前最大值，因此遍历 Set 时，和 tmp 异或后的结果仍在 Set 中，就更新 result 为 tmp
     * 以 [3,10,5,25,2,8] 为例，其二进制为 [11,1010,101,11001,10,1000]
     * 希望用二进制来拼出结果，最终结果 28 的二进制数为 11100，里面有三个 1，来找都是谁贡献了这三个 1
     * 在 i=4 时，数字 25 贡献了最高位的 1，在 i=3 时，数字 25 贡献了次高位的 1，在 i=2 时，数字 5 贡献了第三位的 1。
     * 而一旦某个数贡献了 1，那么之后在需要贡献 1 时，此数就可以再继续贡献 1。
     * 而一旦有两个数贡献了 1 后，那么之后的 1 就基本上只跟这两个数有关了，其他数字有 1 也贡献不出来。
     * 验证方法里使用了前面提到的性质，a^b=t，如果 t 是所求结果话，可以先假定一个 t，然后验证，如果 a^t=b 成立，说明满足要求
     */
    public static int findMaximumXOR(int[] nums) {
        int result = 0, mask = 0;
        for (int i = 31; i >= 0; --i) {
            mask |= (1 << i);

            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }

            int tmp = result | (1 << i);
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    result = tmp;
                    break;
                }
            }
        }
        return result;
    }
}
