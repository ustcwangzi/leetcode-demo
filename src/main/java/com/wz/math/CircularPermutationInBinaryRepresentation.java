package com.wz.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given 2 integers n and start. Your task is return any permutation p of (0,1,2.....,2^n -1) such that :
 * 1. p[0] = start
 * 2. p[i] and p[i+1] differ by only one bit in their binary representation.
 * 3. p[0] and p[2^n -1] must also differ by only one bit in their binary representation.
 *
 * Example 1:
 * Input: n = 2, start = 3
 * Output: [3,2,0,1]
 * Explanation: The binary representation of the permutation is (11,10,00,01).
 * All the adjacent element differ by one bit. Another valid permutation is [3,1,0,2]
 *
 * Example 2:
 * Input: n = 3, start = 2
 * Output: [2,6,7,5,4,0,1,3]
 * Explanation: The binary representation of the permutation is (010,110,111,101,100,000,001,011).
 */
public class CircularPermutationInBinaryRepresentation {
    public static void main(String[] args) {
        System.out.println(circularPermutation(2, 3));
        System.out.println(circularPermutation(3, 2));
    }

    /**
     * 下一位格雷码的计算方式：然后从后往前看，看下更新这位后得到的新数字是否在之前出现过，如果有就一直往前看，比如起始是000，
     * 把最后一位改掉得到001，没有出现过，然后接下来，001把最后改掉成为000，出现过，所以把001第二位改掉变成011，没出现过，所以下一个是011
     * 以此类推，然后可以发现G(n)=n^(n>>1)，比如第4位格雷码对应的是4^2=6
     * 然后题目的意思其实就是找一个格雷码序列，然后有个start的offset而已
     */
    public static List<Integer> circularPermutation(int n, int start) {
        int len = (1 << n), pos = 0;
        int[] grayCode = new int[len];
        for (int i = 1; i < len; i++) {
            if ((i & 1) == 1) {
                grayCode[i] = grayCode[i - 1] ^ 1;
            } else {
                int last = grayCode[i - 1] & (-grayCode[i - 1]);
                grayCode[i] = grayCode[i - 1] ^ (last << 1);
            }
            if (grayCode[i] == start) {
                pos = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = pos; i < len; i++) {
            result.add(grayCode[i]);
        }
        for (int i = 0; i < pos; i++) {
            result.add(grayCode[i]);
        }
        return result;
    }
}
