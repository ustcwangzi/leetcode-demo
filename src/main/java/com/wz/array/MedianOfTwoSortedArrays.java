package com.wz.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * <p>
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        System.out.println(findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{1, 2};
        nums2 = new int[]{};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    /**
     * 大根堆存储较小的一半元素，小根堆存储较大的一半元素
     * 则中间元素位于大根堆和小根堆的堆顶
     * 需要始终保持两个堆的大小之差不超过1
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return (nums2.length & 1) == 0 ? (double) (nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2 :
                    nums2[nums2.length / 2];
        }
        if (nums2.length == 0) {
            return (nums1.length & 1) == 0 ? (double) (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2 :
                    nums1[nums1.length / 2];
        }
        int capacity = (nums1.length + nums2.length) / 2;
        // 大根堆，存储全部元素排序后的前半部分
        PriorityQueue<Integer> bigHeap = new PriorityQueue<>(capacity, Comparator.reverseOrder());
        // 小根堆，存储全部元素排序后的后半部分
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>(capacity);

        saveToHeap(bigHeap, smallHeap, nums1);
        saveToHeap(bigHeap, smallHeap, nums2);

        return calMedian(bigHeap, smallHeap);
    }

    private static void saveToHeap(PriorityQueue<Integer> bigHeap, PriorityQueue<Integer> smallHeap, int[] nums) {
        for (int num : nums) {
            if (bigHeap.size() == 0 || num <= bigHeap.peek()) {
                bigHeap.add(num);
            } else {
                smallHeap.add(num);
            }
            adjustHeap(bigHeap, smallHeap);
        }
    }

    private static void adjustHeap(PriorityQueue<Integer> bigHeap, PriorityQueue<Integer> smallHeap) {
        int bigHeapSize = bigHeap.size();
        int smallHeapSize = smallHeap.size();
        if (bigHeapSize == smallHeapSize || Math.abs(bigHeapSize - smallHeapSize) <= 1) {
            return;
        }
        if (bigHeapSize > smallHeapSize) {
            smallHeap.add(bigHeap.poll());
        } else {
            bigHeap.add(smallHeap.poll());
        }
    }

    private static double calMedian(PriorityQueue<Integer> bigHeap, PriorityQueue<Integer> smallHeap) {
        int bigHeapSize = bigHeap.size();
        int smallHeapSize = smallHeap.size();
        if (bigHeapSize == smallHeapSize) {
            return (double) (bigHeap.peek() + smallHeap.peek()) / 2;
        }
        return bigHeapSize > smallHeapSize ? bigHeap.peek() : smallHeap.peek();
    }
}
