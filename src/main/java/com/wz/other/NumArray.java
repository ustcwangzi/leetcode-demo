package com.wz.other;

/**
 * Given an integer array nums, handle multiple queries of the following types:
 * 1. Update the value of an element in nums.
 * 2. Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * 1. NumArray(int[] nums) Initializes the object with the integer array nums.
 * 2. void update(int index, int val) Updates the value of nums[index] to be val.
 * 3. int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 * Example 1:
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 *
 * Constraints:
 * 1. 1 <= nums.length <= 3 * 10^4
 * 2. -100 <= nums[i] <= 100
 * 3. 0 <= index < nums.length
 * 4. -100 <= val <= 100
 * 5. 0 <= left <= right < nums.length
 * 6. At most 3 * 10^4 calls will be made to update and sumRange.
 */
public class NumArray {
    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }

    private final int[] nums;
    private final SegmentTreeNode root;

    /**
     * Segment Tree 线段树
     * 线段树就是一棵加了些额外信息的满二叉树，比如可以加子树的节点和、最大值、最小值等
     * 当某个节点的值发生变化时，只需要更新一下其所有祖先节点的信息，而并不需要更新整棵树，这样就极大的提高了效率
     * 比如对于 [1 3 5 7] 这个数组，可以根据其坐标划分，组成一个线段树：
     *            [0, 3]
     *              16
     *        /            \
     *     [0, 1]        [2, 3]
     *       4             12
     *    /     \       /     \
     * [0, 0] [1, 1] [2, 2] [3, 3]
     *    1      3      5      7
     */
    public NumArray(int[] nums) {
        this.nums = nums;
        this.root = buildTree(0, nums.length - 1);
    }

    public void update(int index, int val) {
        update(root, index, val);
    }

    private void update(SegmentTreeNode node, int index, int value) {
        if (node == null) {
            return;
        }
        if (node.start == index && node.end == index) {
            node.value = value;
            nums[index] = value;
            return;
        }

        int mid = node.start + (node.end - node.start) / 2;
        if (index <= mid) {
            update(node.left, index, value);
        } else {
            update(node.right, index, value);
        }
        node.value = node.left.value + node.right.value;
    }

    public int sumRange(int left, int right) {
        return sumRange(root, left, right);
    }

    private int sumRange(SegmentTreeNode node, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (node.start == start && node.end == end) {
            return node.value;
        }

        int mid = node.start + (node.end - node.start) / 2;
        if (end <= mid) {
            return sumRange(node.left, start, end);
        } else if (start > mid) {
            return sumRange(node.right, start, end);
        } else {
            return sumRange(node.left, start, mid) + sumRange(node.right, mid + 1, end);
        }
    }

    private SegmentTreeNode buildTree(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        if (start == end) {
            node.value = nums[start];
            return node;
        }
        int mid = (start + end) / 2;
        node.left = buildTree(start, mid);
        node.right = buildTree(mid + 1, end);
        node.value = node.left.value + node.right.value;
        return node;
    }

    private static class SegmentTreeNode {
        int start, end, value;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.value = 0;
        }
    }
}
