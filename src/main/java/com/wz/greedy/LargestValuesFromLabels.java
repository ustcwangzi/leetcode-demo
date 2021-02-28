package com.wz.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * We have a set of items: the i-th item has value values[i] and label labels[i].
 * Then, we choose a subset S of these items, such that:
 * 1. |S| <= num_wanted
 * 2. For every label L, the number of items in S with label L is <= use_limit.
 * Return the largest possible sum of the subset S.
 *
 * Example 1:
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * Output: 9
 * Explanation: The subset chosen is the first, third, and fifth item.
 * Example 2:
 *
 * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * Output: 12
 * Explanation: The subset chosen is the first, second, and third item.
 *
 * Note:
 * 1. 1 <= values.length == labels.length <= 20000
 * 2. 0 <= values[i], labels[i] <= 20000
 * 3. 1 <= num_wanted, use_limit <= values.length
 */
public class LargestValuesFromLabels {
    public static void main(String[] args) {
        System.out.println(largestValsFromLabels(new int[]{5, 4, 3, 2, 1}, new int[]{1, 1, 2, 2, 3}, 3, 1));
    }

    /**
     * 大根堆
     * 选取一个子集，子集中元素总个数不大于 num_wanted，对于 label 值相同的元素，选取的个数不能大于 use_limit，求子集总和的最大值
     * 使用大根堆保存 values 的下标，按照 value 进行排序，当堆不空并且 num_wanted>0 时进行循环
     * 弹出堆顶，获取当前最大 value 的其对应的 label，若使用次数小于 use_limit，则选择 value，同时将 num_wanted 减一
     */
    public static int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> values[o2] - values[o1]);
        for (int i = 0; i < values.length; i++) {
            queue.offer(i);
        }

        // 记录每个 label 的使用次数
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        while (!queue.isEmpty() && num_wanted > 0) {
            int index = queue.poll();
            int value = values[index], label = labels[index];
            if (map.getOrDefault(label, 0) < use_limit) {
                result += value;
                num_wanted--;
                map.put(label, map.getOrDefault(label, 0) + 1);
            }
        }
        return result;
    }
}
