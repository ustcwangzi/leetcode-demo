package com.wz.lists;

import java.util.List;

public class SubsetsII {
    public static void main(String[] args) {

    }

    private static void dfs(int[] nums, int index, List<Integer> group, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(group);
            return;
        }
    }
}
