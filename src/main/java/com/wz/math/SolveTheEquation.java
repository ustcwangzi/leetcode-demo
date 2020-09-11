package com.wz.math;

/**
 * Solve a given equation and return the value of x in the form of string "x=#value".
 * The equation contains only '+', '-' operation, the variable x and its coefficient.
 * If there is no solution for the equation, return "No solution".
 * If there are infinite solutions for the equation, return "Infinite solutions".
 * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
 *
 * Example 1:
 * Input: "x+5-3+x=6+x-2"
 * Output: "x=2"
 *
 * Example 2:
 * Input: "x=x"
 * Output: "Infinite solutions"
 *
 * Example 3:
 * Input: "x=x+2"
 * Output: "No solution"
 */
public class SolveTheEquation {
    public static void main(String[] args) {
        System.out.println(solveEquation("x+5-3+x=6+x-2"));
        System.out.println(solveEquation("x=x"));
        System.out.println(solveEquation("x=x+2"));
    }


    public static String solveEquation(String equation) {
        String[] parts = equation.split("=");
        String leftPart = parts[0];
        String rightPart = parts[1];
        int[] leftVals = evaluate(leftPart);
        int[] rightVals = evaluate(rightPart);
        int cntX = leftVals[0] - rightVals[0];
        int cntNum = leftVals[1] - rightVals[1];
        if (cntX == 0) {
            if (cntNum != 0) {
                return "No solution";
            }
            return "Infinite solutions";
        }
        int valX = (-cntNum) / cntX;
        return "x=" + valX;
    }

    private static int[] evaluate(String exp) {
        int[] result = new int[2];
        String[] expElements = exp.split("(?=[-+])");

        for (String ele : expElements) {
            if (ele.equals("+x") || ele.equals("x")) {
                result[0]++;
            } else if (ele.equals("-x")) {
                result[0]--;
            } else if (ele.contains("x")) {
                result[0] += Integer.parseInt(ele.substring(0, ele.indexOf("x")));
            } else {
                result[1] += Integer.parseInt(ele);
            }
        }
        return result;
    }

}
