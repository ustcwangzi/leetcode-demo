package com.wz.array;

/**
 * Given an array of unique integers salary where salary[i] is the salary of the employee i.
 * Return the average salary of employees excluding the minimum and maximum salary.
 *
 * Example 1:
 * Input: salary = [4000,3000,1000,2000]
 * Output: 2500.00000
 * Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
 * Average salary excluding minimum and maximum salary is (2000+3000)/2= 2500
 *
 * Example 2:
 * Input: salary = [1000,2000,3000]
 * Output: 2000.00000
 * Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
 * Average salary excluding minimum and maximum salary is (2000)/1= 2000
 */
public class AverageSalaryExcludingMinimumAndMaximumSalary {
    public static void main(String[] args) {
        int[] salary = new int[]{4000, 3000, 1000, 2000};
        System.out.println(average(salary));

        salary = new int[]{1000, 2000, 3000};
        System.out.println(average(salary));
    }

    /**
     * 将每个元素除以 n-2 之后的结果相加，然后再减去最大值除以 n-2，减去最小值除以 n-2，就是最后的结果
     */
    public static double average(int[] salary) {
        int min = salary[0], max = salary[0];
        int n = salary.length - 2;
        double result = 0;
        for (int cur : salary) {
            min = Math.min(min, cur);
            max = Math.max(max, cur);
            result += (double) cur / n;
        }
        result -= (double) min / n;
        result -= (double) max / n;
        return result;
    }
}
