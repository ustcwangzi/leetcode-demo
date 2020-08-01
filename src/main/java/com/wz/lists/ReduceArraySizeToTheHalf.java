package com.wz.lists;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 *
 * Example 1:
 * Input: arr = [3,3,3,3,5,5,5,2,2,7]
 * Output: 2
 * Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
 * Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.
 *
 * Example 2:
 * Input: arr = [7,7,7,7,7,7]
 * Output: 1
 * Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 *
 * Example 3:
 * Input: arr = [1,9]
 * Output: 1
 *
 * Example 4:
 * Input: arr = [1000,1000,3,7]
 * Output: 1
 *
 * Example 5:
 * Input: arr = [1,2,3,4,5,6,7,8,9,10]
 * Output: 5
 *
 * Constraints:
 * 1 <= arr.length <= 10^5
 * arr.length is even.
 * 1 <= arr[i] <= 10^5
 */
public class ReduceArraySizeToTheHalf {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
        System.out.println(minSetSize(arr));

        arr = new int[]{7, 7, 7, 7, 7, 7};
        System.out.println(minSetSize(arr));

        arr = new int[]{1, 9};
        System.out.println(minSetSize(arr));

        arr = new int[]{1000, 1000, 3, 7};
        System.out.println(minSetSize(arr));

        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(minSetSize(arr));
    }

    /**
     * 求至少拿出几个元素，使得它们出现的次数之和，>= 总元素个数的一半
     * 先用 countMap 统计每个元素的出现次数，然后用 queue 对 countMap 每个 entry 进行排序
     * 最后依次取出 queue 中的元素，直到它们的出现次数之和满足条件
     */
    public static int minSetSize(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 按照出现次数降序排序
        PriorityQueue<Map.Entry<Integer, Integer>> queue =
                new PriorityQueue<>((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        queue.addAll(countMap.entrySet());

        int result = 0, sum = 0, target = arr.length / 2;
        while (sum < target) {
            sum += queue.poll().getValue();
            result++;
        }
        return result;
    }
}
