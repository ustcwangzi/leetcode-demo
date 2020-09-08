package com.wz.math;

/**
 * There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water.
 * They all look identical. If a pig drinks the poison it will die within 15 minutes.
 * What is the minimum amount of pigs you need to figure out which bucket is poisonous within one hour?
 * Answer this question, and write an algorithm for the general case.
 * General case:
 * If there are n buckets and a pig drinking poison will die within m minutes,
 * how many pigs (x) you need to figure out the poisonous bucket within p minutes? There is exactly one bucket with poison.
 *
 * Note:
 * A pig can be allowed to drink simultaneously on as many buckets as one would like, and the feeding takes no time.
 * After a pig has instantly finished drinking buckets, there has to be a cool down time of m minutes.
 * During this time, only observation is allowed and no feedings at all.
 * Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).
 */
public class PoorPigs {
    public static void main(String[] args) {
        System.out.println(poorPigs(1000, 15, 60));
    }

    /**
     * 有n桶水，猪喝了带毒的水会在m分钟内死亡，在p分钟内使用多少只猪（x）找出“毒药”桶？只有一个带毒药的桶。
     *
     * 情况1：测试次数为1次，有2只小猪，最多可以测试多少个水桶？
     * 我们将4个水桶标记为1,2,3,4，小猪a喝1和2，小猪b喝2和3。15分钟后，如果小猪a死了，说明水桶1有毒；如果小猪b死了，说明水桶3有毒；
     * 如果小猪a和b都死了，说明第2桶水有毒；如果两只小猪都没有死，说明水桶4有毒。测试次数为1次，有两只小猪，可以测试4个水桶。
     * 情况2：测试次数为2次，有2只小猪，最多可以测试多少个水桶？
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 使用数字1到9来标记水桶，第一只猪a第一次喝第1,2,3桶水，第二次喝4,5,6,；第二只猪b第一次喝1,4,7，第二次喝2,5,8。
     * 如果在第一次，a和b都死了，说明1有毒；如果a第一次死了，b在第二次死了，说明2有毒。如果继续往下观察，可能有毒的水桶其实就是一个坐标，也可以理解为一个二维数组。
     * 如果继续往上延伸，测试次数为2次，3只猪时，最多可以测27桶水。
     * 因此，三者之间的关系满足m的x次方等于能测的桶数。而m则是可以测试的次数加1。反过来，就可以通过取对数获得x的值。
     */
    public static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int count = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(count));
    }
}
