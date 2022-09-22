package com.wz.other;

/**
 * Given a (0-indexed) integer array nums and two integers low and high, return the number of nice pairs.
 * A nice pair is a pair (i, j) where 0 <= i < j < nums.length and low <= (nums[i] XOR nums[j]) <= high.
 *
 * Example 1:
 * Input: nums = [1,4,2,7], low = 2, high = 6
 * Output: 6
 * Explanation: All nice pairs (i, j) are as follows:
 *     - (0, 1): nums[0] XOR nums[1] = 5
 *     - (0, 2): nums[0] XOR nums[2] = 3
 *     - (0, 3): nums[0] XOR nums[3] = 6
 *     - (1, 2): nums[1] XOR nums[2] = 6
 *     - (1, 3): nums[1] XOR nums[3] = 3
 *     - (2, 3): nums[2] XOR nums[3] = 5
 *
 * Example 2:
 * Input: nums = [9,8,4,2,1], low = 5, high = 14
 * Output: 8
 * Explanation: All nice pairs (i, j) are as follows:
 *     - (0, 2): nums[0] XOR nums[2] = 13
 *     - (0, 3): nums[0] XOR nums[3] = 11
 *     - (0, 4): nums[0] XOR nums[4] = 8
 *     - (1, 2): nums[1] XOR nums[2] = 12
 *     - (1, 3): nums[1] XOR nums[3] = 10
 *     - (1, 4): nums[1] XOR nums[4] = 9
 *     - (2, 3): nums[2] XOR nums[3] = 6
 *     - (2, 4): nums[2] XOR nums[4] = 5
 *
 * Constraints:
 * 1. 1 <= nums.length <= 2 * 10^4
 * 2. 1 <= nums[i] <= 2 * 10^4
 * 3. 1 <= low <= high <= 2 * 10^4
 */
public class CountPairsWithXORInRange {
    public static void main(String[] args) {
        System.out.println(countPairs(new int[]{1, 4, 2, 7}, 2, 6));
        System.out.println(countPairs(new int[]{9, 8, 4, 2, 1}, 5, 14));
    }

    /**
     * num^num2=i 等效于 num^i=num2，结合字典树方法快速统计，在依次将 nums 中数字加入字典树的同时
     * 搜索和该数字异或值在 [0, high] 和 [0, low - 1] 范围内数字 num2 的个数并相减，就是符合异或值为 [low, high] 区间内的数字个数
     */
    public static int countPairs(int[] nums, int low, int high) {
        // 2^15=32768，15位二进制足够计算
        int result = 0, maxDigit = 14;
        TrieNode root = new TrieNode();
        for (int num : nums) {
            result += search(root, maxDigit, num, high) - search(root, maxDigit, num, low - 1);
            TrieNode node = root;
            // 添加num进字典树
            for (int i = maxDigit; i >= 0; i--) {
                int numMask = (num >> i) & 1;
                if (node.child[numMask] == null) {
                    node.child[numMask] = new TrieNode();
                }
                node = node.child[numMask];
                node.count++;
            }
        }
        return result;
    }

    /**
     * 搜索和 num 异或值在 [0,max] 范围内的数字 num2 的个数
     */
    private static int search(TrieNode node, int digit, int num, int max) {
        if (node == null) {
            return 0;
        }
        if (digit < 0) {
            return node.count;
        }

        int numMask = (num >> digit) & 1, maxMask = (max >> digit) & 1;
        // max 在该位为 1
        if (maxMask == 1) {
            if (numMask == 0) {
                // num 在该位为 0，num2 该位为 0 的部分全部满足，为 1 的部分继续判断
                return (node.child[0] == null ? 0 : node.child[0].count) + search(node.child[1], digit - 1, num, max);
            } else {
                // num 在该位为 1，num2 该位为 1 的部分全部满足，为 0 的部分继续判断
                return (node.child[1] == null ? 0 : node.child[1].count) + search(node.child[0], digit - 1, num, max);
            }
        }
        if (numMask == 0) {
            // max 在该位为 0，num2 该位必须和 num 一致
            return search(node.child[0], digit - 1, num, max);
        }
        return search(node.child[1], digit - 1, num, max);
    }

    private static class TrieNode {
        int count;
        TrieNode[] child;

        public TrieNode() {
            this.count = 0;
            // 两个子节点对应 0 和 1
            this.child = new TrieNode[2];
        }
    }
}
