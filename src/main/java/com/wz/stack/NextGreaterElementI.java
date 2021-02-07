package com.wz.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * You are given two integer arrays nums1 and nums2 both of unique elements, where nums1 is a subset of nums2.
 * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, return -1 for this number.
 *
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation:
 * For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 * For number 1 in the first array, the next greater number for it in the second array is 3.
 * For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 *
 * Example 2:
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation:
 * For number 2 in the first array, the next greater number for it in the second array is 3.
 * For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 *
 * Constraints:
 * 1. 1 <= nums1.length <= nums2.length <= 1000
 * 2. 0 <= nums1[i], nums2[i] <= 104
 * 3. All integers in nums1 and nums2 are unique.
 * 4. All the integers of nums1 also appear in nums2.
 */
public class NextGreaterElementI {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4})));
    }

    /**
     * 单调栈
     * 遍历 nums2 数组，使用单调栈记录每个元素右侧第一个大于自己的元素，只允许更小的元素入栈
     * 对于遍历到的当前元素 num，如果大于栈顶，则说明栈顶元素右侧第一个大于自己的元素是 num，出栈，将结果记录在 map 中
     * 遍历结束后，依次从 map 中获取结果赋值给最后的结果集即可
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }
}
