package com.wz.practice;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = new int[]{0, -1, 0, 5, 3, 10, 2};
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array, int left, int right) {
        if (left < right) {
            // 找到分割点
            int index = getIndex(array, left, right);
            // 对分割点两侧继续排序
            sort(array, left, index - 1);
            sort(array, index + 1, right);
        }
    }

    private static int getIndex(int[] array, int left, int right) {
        int tmp = array[left];
        while (left < right) {
            while (left < right && tmp <= array[right]) {
                right--;
            }
            array[left] = array[right];
            while (left < right && tmp >= array[left]) {
                left++;
            }
            array[right] = array[left];
        }
        array[left] = tmp;
        return left;
    }
}
