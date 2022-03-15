package com.wz.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a 0-indexed integer array mapping which represents the mapping rule of a shuffled decimal system.
 * mapping[i] = j means digit i should be mapped to digit j in this system.
 * The mapped value of an integer is the new integer obtained by replacing each occurrence of digit i in the integer with mapping[i] for all 0 <= i <= 9.
 * You are also given another integer array nums. Return the array nums sorted in non-decreasing order based on the mapped values of its elements.
 * Notes:
 * - Elements with the same mapped values should appear in the same relative order as in the input.
 * - The elements of nums should only be sorted based on their mapped values and not be replaced by them.
 *
 * Example 1:
 * Input: mapping = [8,9,4,0,2,1,3,5,7,6], nums = [991,338,38]
 * Output: [338,38,991]
 * Explanation:
 * Map the number 991 as follows:
 * 1. mapping[9] = 6, so all occurrences of the digit 9 will become 6.
 * 2. mapping[1] = 9, so all occurrences of the digit 1 will become 9.
 * Therefore, the mapped value of 991 is 669.
 * 338 maps to 007, or 7 after removing the leading zeros.
 * 38 maps to 07, which is also 7 after removing leading zeros.
 * Since 338 and 38 share the same mapped value, they should remain in the same relative order, so 338 comes before 38.
 * Thus, the sorted array is [338,38,991].
 *
 * Example 2:
 * Input: mapping = [0,1,2,3,4,5,6,7,8,9], nums = [789,456,123]
 * Output: [123,456,789]
 * Explanation: 789 maps to 789, 456 maps to 456, and 123 maps to 123. Thus, the sorted array is [123,456,789].
 *
 * Constraints:
 * 1. mapping.length == 10
 * 2. 0 <= mapping[i] <= 9
 * 3. All the values of mapping[i] are unique.
 * 4. 1 <= nums.length <= 3 * 10^4
 * 5. 0 <= nums[i] < 10^9
 */
public class SortTheJumbledNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortJumbled(new int[]{8, 9, 4, 0, 2, 1, 3, 5, 7, 6}, new int[]{991, 338, 38})));
    }

    /**
     * 重写 Comparator
     * 将 nums[] 通过 mapping[] 映射到新的值，同时记录原索引位置，将新值、原索引位置保存在 list 中
     * 然后对 list 排序，重写 Comparator：若映射的值不同则比较映射的值，否则比较索引位置，最后将排序的 list 转换为数组即可
     */
    public static int[] sortJumbled(int[] mapping, int[] nums) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (char cur : String.valueOf(nums[i]).toCharArray()) {
                builder.append(mapping[cur - '0']);
            }
            list.add(new int[]{Integer.parseInt(builder.toString()), i});
        }
        list.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });

        int[] result = new int[nums.length];
        int index = 0;
        for (int[] array : list) {
            result[index++] = nums[array[1]];
        }
        return result;
    }
}
