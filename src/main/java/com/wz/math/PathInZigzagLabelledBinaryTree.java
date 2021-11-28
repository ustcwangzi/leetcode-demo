package com.wz.math;

import java.util.LinkedList;
import java.util.List;

/**
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right,
 * while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
 * @link ../../../../resource/PathInZigzagLabelledBinaryTree.jpg
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.
 *
 * Example 1:
 * Input: label = 14
 * Output: [1,3,4,14]
 *
 * Example 2:
 * Input: label = 26
 * Output: [1,2,6,10,26]
 */
public class PathInZigzagLabelledBinaryTree {
    public static void main(String[] args) {
        System.out.println(pathInZigZagTree(14));
        System.out.println(pathInZigZagTree(26));
    }

    /**
     * 1. 先找出label所在的树的层数n和对应所在数组的下标index（其实树和数组都不需要真实的构造出来，只是分析解题时借助一下就行），
     *    创建存放路径path的list
     * 2. 判断n的奇偶性，如果是偶数，index+1即是所找的节点元素值，把它放入list的头部；
     *    如果是奇数，所找的节点元素值为：(2^n) * 3 - index - 2(这是由解题思路中index 和 label之间的关系得来的，
     *    此时要注意：这里的label指的是路径path中每个节点的值，不仅仅是题目所要找的label）
     * 3. n--更新n的值，往上递推，同时也更新index的值，index = (index - 1)/2
     * 4. 循环执行步骤2和3，直到n<0
     */
    public static List<Integer> pathInZigZagTree(int label) {
        int n = 0;
        while (Math.pow(2, n) < label) {
            if (Math.pow(2, n + 1) > label) {
                break;
            } else {
                n++;
            }
        }

        int indexOfLabel;
        if (n % 2 == 0) {
            indexOfLabel = label - 1;
        } else {
            indexOfLabel = (int) (Math.pow(2, n) * 3 - label - 2);
        }

        List<Integer> list = new LinkedList<>();
        while (n >= 0) {
            if (n % 2 == 0) {
                list.add(0, indexOfLabel + 1);
            } else {
                list.add(0, (int) (Math.pow(2, n) * 3 - indexOfLabel - 2));
            }
            indexOfLabel = (indexOfLabel - 1) / 2;
            n--;
        }

        return list;
    }
}
