package com.wz.practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述
 * 给出一组区间，请合并所有重叠的区间。
 * 请保证合并后的区间按区间起点升序排列。
 *
 * 示例1
 * 输入：
 * [[10,30],[20,60],[80,100],[150,180]]
 * 返回值：
 * [[10,60],[80,100],[150,180]]
 */
public class MergeIntervals {
    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(0, 2));
        intervals.add(new Interval(3, 5));
        System.out.println(merge(intervals));
    }

    /**
     * 排序 + 贪心
     * 先对 intervals 按照 start 进行排序，然后将第一个区间放入结果中，从第二个区间开始遍历
     * 把当前区间和结果中的最后一个区间进行比较，如有重叠进行合并，同时删除结果中的最后一个区间，否则直接将当前区间加入结果中
     */
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() == 0) {
            return intervals;
        }
        intervals.sort(Comparator.comparingInt(o -> o.start));
        List<Interval> result = new LinkedList<>();
        result.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            // 比较当前区间和结果中的最后一个区间
            Interval pre = result.get(result.size() - 1), cur = intervals.get(i);
            // 产生重叠
            if (cur.start <= pre.end) {
                result.remove(result.size() - 1);
                result.add(new Interval(pre.start, Math.max(pre.end, cur.end)));
            } else {
                result.add(cur);
            }
        }
        return result;
    }

    static class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "{" + start + "," + end + "}";
        }
    }
}
