package com.wz.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array target and an integer n. In each iteration, you will read a number from  list = {1,2,3..., n}.
 * Build the target array using the following operations:
 * 1. Push: Read a new element from the beginning list, and push it in the array.
 * 2. Pop: delete the last element of the array.
 * 3. If the target array is already built, stop reading more elements.
 * Return the operations to build the target array. You are guaranteed that the answer is unique.
 *
 * Example 1:
 * Input: target = [1,3], n = 3
 * Output: ["Push","Push","Pop","Push"]
 * Explanation:
 * Read number 1 and automatically push in the array -> [1]
 * Read number 2 and automatically push in the array then Pop it -> [1]
 * Read number 3 and automatically push in the array -> [1,3]
 *
 * Example 2:
 * Input: target = [1,2,3], n = 3
 * Output: ["Push","Push","Push"]
 *
 * Constraints:
 * 1. 1 <= target.length <= 100
 * 2. 1 <= target[i] <= n
 * 3. 1 <= n <= 100
 * 4. target is strictly increasing.
 */
public class BuildAnArrayWithStackOperations {
    public static void main(String[] args) {
        System.out.println(buildArray(new int[]{1, 3}, 3));
    }

    /**
     * 用 i 记录当前操作的元素，j 记录当前需要组成的元素 target[j]
     * 如果 i == target[j]，则 Push，i 和 j 右移；否则 Push、Pop，i 右移
     */
    public static List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>(n);
        int j = 0;
        for (int i = 1; i <= n; i++) {
            if (j == target.length) {
                break;
            }

            if (i == target[j]) {
                result.add("Push");
                j++;
            } else {
                result.add("Push");
                result.add("Pop");
            }
        }
        return result;
    }
}
