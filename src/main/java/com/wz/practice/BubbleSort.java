package com.wz.practice;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{0, -1, 0, 5, 3, 10, 2};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array) {
        if (array == null || array.length == 1) {
            return;
        }

        boolean swap = true;
        for (int i = 0; i < array.length - 1; i++) {
            // 如果在一次循环中没有发生过数据交换，说明已有序，直接结束
            if (!swap) {
                break;
            }

            swap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap = true;
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
}
