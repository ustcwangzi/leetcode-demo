package com.wz.array;

/**
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 * How many total friend requests are made?
 *
 * Example 1:
 * Input: [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 *
 * Example 2:
 * Input: [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 *
 * Example 3:
 * Input: [20,30,100,110,120]
 * Output: 3
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 *
 * Notes:
 * 1 <= ages.length <= 20000.
 * 1 <= ages[i] <= 120.
 */
public class FriendsOfAppropriateAges {
    public static void main(String[] args) {
        int[] ages = new int[]{16, 16};
        System.out.println(numFriendRequests(ages));
        System.out.println(numFriendRequests2(ages));

        ages = new int[]{16, 17, 18};
        System.out.println(numFriendRequests(ages));
        System.out.println(numFriendRequests2(ages));

        ages = new int[]{20, 30, 100, 110, 120};
        System.out.println(numFriendRequests(ages));
        System.out.println(numFriendRequests2(ages));
    }

    /**
     * 由于年龄的大小在 1～120 之间, 因此可以用数组保存各个年龄的人数, 然后过滤不满足条件的交友关系, 另外注意不能和自己交友.
     * 另外，age[B]>age[A] 与 age[B]>100 && age[A]<100 这两个条件重复，因此不用考虑
     */
    public static int numFriendRequests(int[] ages) {
        // 各个年龄的人数
        int[] ageCount = new int[121];
        for (int age : ages) {
            ageCount[age]++;
        }

        int result = 0;
        for (int i = 1; i <= 120; i++) {
            for (int j = 1; j <= 120; j++) {
                if (j <= 0.5 * i + 7 || j > i) {
                    continue;
                }
                result += ageCount[i] * ageCount[j];
                if (i == j) {
                    // 减去自身
                    result -= ageCount[i];
                }
            }
        }

        return result;
    }

    /**
     * 题目给的三个条件可以归纳成一个条件，若A想加B的好友，那么B的年龄必须在 (A*0.5+7, A] 这个范围内，
     * 由于区间要有意义的话，A*0.5+7 < A 必须成立，解出 A > 14，那么A最小就只能取15了。
     * 对于每一个年龄，只要求出上面那个区间范围内的个数，就是符合题意的。那么既然是求区域和，建立累加数组就是一个很好的选择了，
     * 首先建立一个统计数组 ageCount，用来统计在各个年龄上的人数，然后建立累加和数组 ageSum，用来记录 [1...j] 年龄段的人数。
     * 从15开始遍历，如果某个年龄上没有人，直接跳过，然后就是统计出 (A*0.5+7, A] 这个范围内有多少人，可以通过累计和数组来快速求出，
     * 由于当前年龄点的人可以跟这个区间内的所有发好友申请，而当前时间点可能还有多人，所以二者相乘，
     * 但由于目前区间里还包括但当前年龄点本身，所以还要减去当前年龄上的人数
     */
    public static int numFriendRequests2(int[] ages) {
        // 各个年龄的人数
        int[] ageCount = new int[121];
        for (int age : ages) {
            ageCount[age]++;
        }

        // [1...i]年龄段的人数
        int[] ageSum = new int[121];
        for (int i = 1; i <= 120; i++) {
            ageSum[i] = ageCount[i] + ageSum[i - 1];
        }

        int result = 0;
        for (int i = 15; i <= 120; ++i) {
            if (ageCount[i] == 0) {
                continue;
            }
            int j = (int) (i * 0.5 + 7);
            int count = ageSum[i] - ageSum[j];
            // 需要减去当前年龄的人数
            result += (count - 1) * ageCount[i];
        }

        return result;
    }
}
