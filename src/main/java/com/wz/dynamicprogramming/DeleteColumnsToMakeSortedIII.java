package com.wz.dynamicprogramming;

public class DeleteColumnsToMakeSortedIII {
    public static void main(String[] args) {

    }

    public static int minDeletionSize(String[] A) {
        int m = A.length, n = A[0].length(), result = n-1, k = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                for (k = 0; k < m;k++) {
                    if (A[k].charAt(j) > A[k].charAt(i)) {
                        break;
                    }
                }
                if (k==m && dp[j] > dp[i]){
                    dp[i] = dp[j] + 1;
                }
            }
            result = Math.min(result, n - dp[i]);
        }
        return result;
    }
}
